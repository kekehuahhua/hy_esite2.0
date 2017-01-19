package com.huiyee.esite.mgr.imp;

import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.huiyee.esite.dao.IJfDesignDao;
import com.huiyee.esite.dto.BalancePageDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.dto.OwnerBanlanceDto;
import com.huiyee.esite.mgr.IOwnerBalanceMgr;
import com.huiyee.esite.model.BalanceSet;
import com.huiyee.esite.model.PayRecord;
import com.huiyee.esite.util.Arith;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.JsonStringUtil;
import com.huiyee.interact.offcheck.dao.IOffCheckDzWayDao;
import com.huiyee.interact.offcheck.model.OffCheckDzWay;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.model.WxTemplate;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.weixin.dao.IWxBuyProductDao;
import com.huiyee.weixin.model.PayOrder;
import com.huiyee.weixin.model.PayOrderTemplate;
import com.huiyee.weixin.model.template.DDZFCG;
import com.huiyee.weixin.model.template.GRXXTZ;
import com.huiyee.weixin.model.template.HYCZCGTZ;
import com.huiyee.weixin.model.template.HYXFTX;

public class OwnerBalanceMgrImpl extends AbstractMgr implements IOwnerBalanceMgr {
	private IWxBuyProductDao wxBuyProductDao;
	private IJfDesignDao jfDesignDao;
	private IOffCheckDzWayDao offCheckDzWayDao;
	public void setWxBuyProductDao(IWxBuyProductDao wxBuyProductDao) {
		this.wxBuyProductDao = wxBuyProductDao;
	}

	public void setJfDesignDao(IJfDesignDao jfDesignDao) {
		this.jfDesignDao = jfDesignDao;
	}

	public void setOffCheckDzWayDao(IOffCheckDzWayDao offCheckDzWayDao) {
		this.offCheckDzWayDao = offCheckDzWayDao;
	}

	@Override
	public IDto findPayTypeInfo(long hyuid,long id,String type,long owner) {
		OwnerBanlanceDto dto = new OwnerBanlanceDto();
		int rmb = this.findRmb(hyuid);//��Ա�����
		if("H".equals(type)){//����ǩ��ɨ���ά��  ���ҵ������ý��
			OffCheckDzWay dzway=offCheckDzWayDao.findDzWay(id);
			if(dzway != null){
				dto.setRealprice(dzway.getRmb());
			}
		}else{//��ѯ����֧�����
			PayOrder payOrder = wxBuyProductDao.findPayOrderById(id);
			if(payOrder != null){
				dto.setRealprice(payOrder.getRealprice());
			}
		}
		BalanceSet balanceSet = jfDesignDao.findBalanceSetByOwner(owner);
		if(balanceSet != null){
			String str = balanceSet.getPageset();
			Gson gson = new Gson();
			BalancePageDto pagedto = gson.fromJson(str, BalancePageDto.class);
			dto.setPagedto(pagedto);
		}
		dto.setRmb(rmb);
		return dto;
	}

	@Override
	public int updateBanlance(long hyuid, long owner, long id,String ip,String type,String url) {
		String rr="";
		if("S".equals(type)){
			PayOrder p = wxBuyProductDao.findPayOrderById(id);
			if(p != null&&p.getStatus().equals("DFK")){
				rr=Arith.div(p.getRealprice(), 100, 2) + "Ԫ";
				this.updateRmbBalance(hyuid, -p.getRealprice(), "����"+ rr, "XSX", 0); //��������
				if(p.getGoodscount() > 0){
					wxBuyProductDao.updatepayOrderStatus(id, null, "DFH");//����������
				}else{
					wxBuyProductDao.updatepayOrderStatus(id, null, "CMP");//����������
				}
				wxBuyProductDao.updatePayorderStatusByFid(id,"CMP");//�����Ӷ���
				PayRecord payRecord = new PayRecord(); 
				payRecord.setIp(ip);
				payRecord.setMediaorder("��Ա��֧��");
				payRecord.setHyuid(hyuid);
				payRecord.setOrderid(id);
				payRecord.setPrice(p.getRealprice());
				wxBuyProductDao.savePayRecord(payRecord);//����֧����¼
				
				if (owner > 0)
				{
					List<WxTemplate> wts = this.findWxTemplate(owner,"WDS",0);
					if (wts != null&&wts.size()>0)
					{
						for(WxTemplate wt:wts)
						{
						DDZFCG dd = (DDZFCG) JsonStringUtil.String2Obj(wt.getData(), DDZFCG.class);
						dd.setOrderMoneySum(rr);
						dd.setOrderProductName("��Ա��֧��");
						WxTemplateJob wj = new WxTemplateJob();
						wj.setMpid(wt.getMpid());
						wj.setType("WDS");
						wj.setEntityid(wt.getEntityid());
						wj.setRemark(wt.getRemark());
						wj.setTemplate_id(wt.getTemplate_id());
						wj.setHyuid(hyuid);
						wj.setData(dd.toData());
						wj.setUrl(url);
						this.addTmplMsg(wj);
						}
					}
				}
				
			}else{
				return 0;
			}
		}else if("H".equals(type)){
			OffCheckDzWay dzway=offCheckDzWayDao.findDzWay(id);
			if(dzway != null){
				rr=Arith.div(dzway.getRmb(), 100, 2) + "Ԫ";
				this.updateRmbBalance(hyuid, -dzway.getRmb(), "����"+ rr, "XXX", 0);//��������
				
				List<WxTemplate> wts2 = this.findWxTemplate(owner,"XFD",0);	
				if (wts2 != null&&wts2.size()>0)
				{
					for (WxTemplate wt : wts2)
					{
						HYXFTX dd = (HYXFTX) JsonStringUtil.String2Obj(wt.getData(), HYXFTX.class);
						dd.setKeyword1("NO."+hyuid);//��Ա����
						dd.setKeyword3(rr);//�����˶���Ǯ
						List<HyUserDz> dzs = hyUserDzDao.findDzList(owner, "t5", 0, 100);
						for (HyUserDz dz : dzs)
						{
							WxTemplateJob wj = new WxTemplateJob();
							wj.setMpid(wt.getMpid());
							wj.setType("XFD");
							wj.setEntityid(wt.getEntityid());
							wj.setRemark(wt.getRemark());
							wj.setTemplate_id(wt.getTemplate_id());
							wj.setTouser(dz.getOpenid());
							wj.setData(dd.toData());
							wj.setUrl(wt.getUrl());
							this.addTmplMsg(wj);
						}
					}
				}
			}
		}
		
		if(owner>0)
		{
			List<WxTemplate> wts = this.findWxTemplate(owner,"XFU",0);	
			if (wts != null&&wts.size()>0)
			{
				for(WxTemplate wt:wts)
				{
				HYXFTX dd = (HYXFTX) JsonStringUtil.String2Obj(wt.getData(), HYXFTX.class);
				dd.setKeyword1("NO."+hyuid);//��Ա����
				dd.setKeyword3(rr);//�����˶���Ǯ
				WxTemplateJob wj = new WxTemplateJob();
				wj.setMpid(wt.getMpid());
				wj.setType("XFU");
				wj.setEntityid(wt.getEntityid());
				wj.setRemark(wt.getRemark());
				wj.setTemplate_id(wt.getTemplate_id());
				wj.setHyuid(hyuid);
				wj.setData(dd.toData());
				wj.setUrl(wt.getUrl());
				this.addTmplMsg(wj);
				}
			}
		}
		
		return 1;
	}

	
}
