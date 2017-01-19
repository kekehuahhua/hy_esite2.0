package com.huiyee.interact.renqi.service;

import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.renqi.dto.RsRqDto;
import com.huiyee.interact.renqi.mgr.IRenQiRecordMgr;
import com.huiyee.interact.renqi.model.FUidJf;
import com.huiyee.interact.renqi.model.RenQi;

public class RenQiDrawService implements IRenQiDrawService
{
	private IRenQiRecordMgr renQiRecordMgr;
	private static Map<String, String> uids = new HashMap<String, String>();

	@Override
	public RsRqDto bidOneRenQi(VisitUser vu, RenQi rq, String ip, String terminal, String source)
	{
		RsRqDto rs = new RsRqDto();
		long fuid = 0;
		try
		{
			fuid = Long.parseLong(vu.getSource());
		}
		catch (NumberFormatException e)
		{
			rs.setStatus(-2);
			rs.setHydesc("�����߲�����");
			return rs;
		}

		int rnmn = (int) (Math.random() * (rq.getMaxjf() - rq.getMinjf() + 1));
		int addjf = rq.getMinjf() + rnmn;
		long uid = vu.getUid();
		int utype = vu.getCd();
		int rc = renQiRecordMgr.findDuidDraw(rq.getId(), uid, fuid, utype);
		if (rc > 0)
		{
			rs.setStatus(-1);
			rs.setHydesc("�Ѿ�Ϊ�˷����߻���������");
			return rs;
		}
		synchronized (getUids(uid + "," + utype))
		{
			renQiRecordMgr.addRqDuidDraw(rq.getId(), uid, fuid, utype, addjf, ip, terminal, source);// ����draw��¼,ͬʱΪ���������ӻ���

			FUidJf fu = renQiRecordMgr.findFuidJf(rq.getId(), fuid, utype);
			if (fu.getLunum() < rq.getMaxlu() && (fu.getTotalnum() - fu.getUsednum()) >= rq.getCnum())// �Ƿ��û��齱����
			{
				renQiRecordMgr.updateJf2Lottery(rq.getId(), fuid, utype, rq.getCnum(), rq.getLotteryid());// ��ȥ����ͬʱ����lunum,���ӳ齱����
			}
			return rs;
		}
	}

	public void setRenQiRecordMgr(IRenQiRecordMgr renQiRecordMgr)
	{
		this.renQiRecordMgr = renQiRecordMgr;
	}

	@Override
	public void fuidShare(VisitUser vu, long rqid, String content, String ip, String terminal, String source)
	{
		long uid = vu.getUid();
		int utype = vu.getCd();
		renQiRecordMgr.addFuidShare(rqid, uid, utype, content, ip, terminal, source);
	}

	public static String getUids(String uid)
	{
		String str = uids.get(uid);
		if (str == null)
		{
			str = new String("");
			uids.put(uid, str);
		}
		return str;
	}

}
