
package com.huiyee.esite.mgr.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huiyee.esite.dao.IDynamicActionRecordDao;
import com.huiyee.esite.dao.IHdUserDataDao;
import com.huiyee.esite.dao.ILotteryUserChanceRecordDao;
import com.huiyee.esite.dao.IOwnerBalanceDao;
import com.huiyee.esite.dao.IOwnerBalanceRecordDao;
import com.huiyee.esite.dao.IOwnerBalanceSetDao;
import com.huiyee.esite.dao.IOwnerLotteryUserDao;
import com.huiyee.esite.dao.IPageDao;
import com.huiyee.esite.model.OwnerBalanceSet;
import com.huiyee.esite.model.SuperHdModel;
import com.huiyee.esite.ws.WeiBoService;
import com.huiyee.interact.setting.dao.IHyUserDzDao;
import com.huiyee.interact.setting.model.HyUserDz;
import com.huiyee.interact.template.dao.IWxTemplateDao;
import com.huiyee.interact.template.dao.IWxTemplateJobDao;
import com.huiyee.interact.template.model.WxTemplateJob;
import com.huiyee.interact.template.model.WxTemplate;

public abstract class AbstractMgr {

	protected IOwnerBalanceDao ownerBalanceDao;
	protected IOwnerBalanceRecordDao ownerBalanceRecordDao;
	protected IOwnerBalanceSetDao ownerBalanceSetDao;
	protected IOwnerLotteryUserDao ownerLotteryUserDao;
	protected IPageDao pageDao;
	protected ILotteryUserChanceRecordDao userChanceRecordDao;
	protected IHdUserDataDao hdUserDataDao;
	static Map<Long, OwnerBalanceSet> obss = new HashMap<Long, OwnerBalanceSet>();

	protected WeiBoService longWS;// 3600000
	protected WeiBoService shortWS;// 60000
	protected WeiBoService mustShortWS;// ����ʱ��2000
	protected WeiBoService justWS;// ����ʱ��0

	protected IWxTemplateJobDao wxTemplateJobDao;
	protected IWxTemplateDao wxTemplateDao;
	protected IDynamicActionRecordDao dynamicActionRecordDao;//����ip����
	protected IHyUserDzDao hyUserDzDao;
	
	public void setHyUserDzDao(IHyUserDzDao hyUserDzDao)
	{
		this.hyUserDzDao = hyUserDzDao;
	}

	public void setDynamicActionRecordDao(IDynamicActionRecordDao dynamicActionRecordDao)
	{
		this.dynamicActionRecordDao = dynamicActionRecordDao;
	}

	/**
	 * �鿴�û��Ĵ�ֵ�˻����
	 * @param uid
	 * @return
	 */
	public int findRmb(long uid)
	{
		return ownerBalanceDao.findRmbByUser(uid);
	}
	
	/**
	 * �鿴�û��Ļ���
	 * 
	 * @param uid
	 * @return
	 */
	public int findJFen(long uid) 
	{
		return ownerBalanceDao.findJFByUser(uid);
	}
	
	/**
	 * ����ip����
	 * @param ip
	 * @return
	 */
	public String findAreaByIp(String ip)
	{
		return dynamicActionRecordDao.findAreaByIp(ip);
	}
	
	public List<HyUserDz> findDz(long owner,String type)
	{
		return hyUserDzDao.findDzList(owner, type, 0,100);
	}
	
	public boolean isDz(long owner, String type, long hyuid)
	{
		int rs=hyUserDzDao.findDzByHyuid(owner, type, hyuid);
		if(rs>0)
		{
			return true;
		}
		return false;
	}
	public OwnerBalanceSet findOBS(long owner) {
		if (owner == 0) {
			return null;
		}
		OwnerBalanceSet obs = obss.get(owner);
		long ctime = System.currentTimeMillis();
		if (obs == null || obs.getCachetime() < ctime - 3600 * 1000) {
			obs = ownerBalanceSetDao.findRuleById(owner);
			if (obs != null) {
				obs.setCachetime(ctime);
				obss.put(owner, obs);
			}
		}
		return obs;
	}

	/**
	 * ͳһAPI,�����������л������齱�Ĺ�ϵ
	 * 
	 * @param hd
	 *            ������model
	 * @param uid
	 *            user��id������΢��������΢��
	 * @paramu type ����΢��΢��,0-΢��,1-΢��
	 * @param cid
	 *            �����΢������,��ʱ�ķ��ʵĹ�΢��id
	 */
	public void updateInteractAction(SuperHdModel hd, long uid, int utype) {

		hdUserDataDao.addUserData(uid, utype, hd.getId(), hd.getFeatureid());
		// ��ó齱����
		if (hd.getLotterychance() > 0 && hd.getIslottery().equalsIgnoreCase("Y") && hd.getLotteryid() > 0) {
			int chancetotal = this.findUserChanceTotal(hd.getId(), hd.getFeatureid(), uid, utype);// �Ѿ����ӵĻ���
			int maxlotterychance = hd.getMaxlotterychance();
			int addnum = 0;
			if (maxlotterychance - chancetotal > hd.getLotterychance()) {
				addnum = hd.getLotterychance();
			} else {
				addnum = maxlotterychance - chancetotal;
			}
			if (addnum > 0) {
				this.addLotteryChanceRecord(hd.getId(), hd.getFeatureid(), uid, utype, addnum, hd.getLotteryid());
				this.addLotteryUserTotal(uid, utype, hd.getLotteryid(), addnum);
			}
		}
	}

	/**
	 * ���ۺͷ�����������͵Ļ���
	 * ȫ�����õĻ��֣�ͨ���˷���ֱ�����Ӽ��ٻ���
	 * @param hyuid
	 * @param stype
	 * @param owner
	 */
	public OwnerBalanceSet updateHyUserBalance(long hyuid, String stype, long owner, long enid) {
		OwnerBalanceSet obs = findOBS(owner);
		if (obs == null) {
			return null;
		}
		if (stype.equals("TPC")) {
			updateBalance(hyuid, obs.getTopicnum(), "������", "BBS", stype, enid);
		} else if (stype.equals("CMT")) {
			updateBalance(hyuid, obs.getComnum(), "��������", "BBS", stype, enid);
		} else if (stype.equals("TOP")) {
			updateBalance(hyuid, obs.getTopnum(), "���ⱻ�ö�", "BBS", stype, enid);
		} else if (stype.equals("UPP")) {
			updateBalance(hyuid, obs.getUpnum(), "��̳��", "BBS", stype, enid);
		} else if (stype.equals("DOW")) {
			updateBalance(hyuid, obs.getDownum(), "��̳��", "BBS", stype, enid);
		}else if (stype.equals("OFC")) {
			updateBalance(hyuid, obs.getOcnum(), "����ǩ��", "OFC", stype, enid);
		} else if (stype.equals("YYU")) {
			updateBalance(hyuid, obs.getYynum(), "΢ԤԼ", "YYU", stype, enid);
		}else if (stype.equals("FWP")) {
			updateBalance(hyuid, obs.getYypjnum(), "��������", "FWP", stype, enid);
		}
		return obs;
	}

	/**
	 * ���ֲ���,�ͻ����������л��ּӼ�
	 * 
	 * @param hyuid
	 *            �û�hyuser ��id
	 * @param balance
	 *            ���� ����Ϊ�ӻ��� ����Ϊ������
	 * @param desc
	 *            ����
	 * @param type
	 *            ��ķ���:SHR-�����࣬BBS-�����࣬HUD-�����࣬NEW-�����࣬CHB-�ϻ��ˣ�CHR-�ϻ���RMB, JFS-�����̳�,JFD-���ֵֿ�,OFC-����ǩ��,YYU-ԤԼ,TAK-����
	 *            RMB-��ֵ�˻�
	 *            ��Ŀ��
	 *            �ʵ���CDD-�ʵ���Ŀ
	 * @param stype
	 *            �����ࣺAPT-���룻LOT-�齱��RSH-���У�SPR-��ɢ��VOT-ͶƱ
	 *            �����ࣺSRR-��������SSH-��������ķ���SGZ-��������Ĺ�ע��SCL-��������ĵ��
	 *            �����ࣺTPC-�����⣻CMT-�������ۣ�TOP-�����ö���UPP-����DOW-��
	 *            �����ࣺNGZ-�¹�ע��CHK-ǩ��;CHZ-��ֵ����
	 *            �ϻ��ˣ�SHA-ת����CLK-�����GZH-��ע��HUD-������WBU-�ⲿ 
	 *            �����̳ǣ�DHS-�һ���Ʒ��FHS-�������ڷ���
	 *            ���ֵֿۣ�DKP-�ֿ��ֽ�
	 *            ΢ԤԼ��YYU-ԤԼ�ɹ���û��֣�FWP-��������
	 *            ����ǩ����OFC-����ǩ����û��֣�XFJ-���ѻ���
	 *            ��ֵ�˻���RCG-��ֵ��XXX-�������ѣ�XSX-��������;SMQ-ɨ��ǹ����
	 *            ��Ŀ��
	 *            �ʵ���CDD-��Ҷһ�  CDP-�ֻ���
	 */
	public void updateBalance(long hyuid, int balance, String desc, String type, String stype, long enid) {
		if (hyuid > 0) {
			if (balance > 0) 
			{
				ownerBalanceDao.addMoreBalance(hyuid, balance);
				ownerBalanceRecordDao.addRecord(hyuid, balance, desc, type, stype, enid);
			} else if (balance < 0) 
			{
				ownerBalanceDao.addLessBalance(hyuid, -balance);
				ownerBalanceRecordDao.addRecord(hyuid, balance, desc, type, stype, enid);
			}
		}
	}
	public long updateRmbBalance(long hyuid, int balance, String desc, String stype, long enid) {
		long rs=0;
		if (hyuid > 0) {
			if (balance > 0) 
			{
				ownerBalanceDao.addMoreRmbBalance(hyuid, balance);
			rs=	ownerBalanceRecordDao.addRmbRecord(hyuid, balance, desc, "RMB", stype, enid);
			} else if (balance < 0) 
			{
				ownerBalanceDao.addLessRmbBalance(hyuid, -balance);
			rs=	ownerBalanceRecordDao.addRmbRecord(hyuid, balance, desc, "RMB", stype, enid);
			}
		}
		return rs;
	}


	/**
	 * �ӳ齱����
	 * 
	 * @param entityid
	 *            �û�wbuid
	 * @param lid
	 *            �齱id
	 * @param num
	 *            ����
	 */
	public void addLotteryUserTotal(long entityid, int type, long lid, int num) {
		ownerLotteryUserDao.addLotteryUserTotal(entityid, type, lid, num);
	}

	/**
	 * 
	 * @param entityid
	 *            �������л�ͶƱ��ڱ���id
	 * @param featureid
	 *            118-���� 123-ͶƱ 124-���� 134-�ڱ�
	 * @param userid
	 *            wbuid��wxuid
	 * @param utype
	 *            0-sina 1-weixin
	 * @return ��õĳ齱����
	 */
	public int findUserChanceTotal(long entityid, long featureid, long userid, int utype) {
		return userChanceRecordDao.findUserChanceTotal(entityid, featureid, userid, utype);
	}

	/**
	 * 
	 * @param entityid
	 *            �������л�ͶƱ��ڱ���id
	 * @param featureid
	 *            118-���� 123-ͶƱ 124-���� 134-�ڱ�
	 * @param userid
	 *            wbuid��wxuid
	 * @param utype
	 *            0-sina 1-weixin
	 * @param num
	 *            ��ӳ齱����
	 * @return
	 */
	public void addLotteryChanceRecord(long entityid, long featureid, long userid, int utype, int num, long lid) {
		if (num > 0) {
			userChanceRecordDao.addLotteryChanceRecord(entityid, featureid, userid, utype, num, lid);
		}
	}

	/**
	 * 
	 * @param uid
	 * @param ownerwbuid
	 * @param utype
	 * @param preused
	 *            �����Ǹ���,�����ǽⶳ ����Ļ���
	 */
	public void updatePreUsedByUser(long uid, long ownerwbuid, int utype, int preused, String desc) {
		// ownerBalanceDao.updatePreUsedByUser(uid, ownerwbuid, utype, preused);
		// ownerBalanceRecordDao.addRecord(ownerwbuid, uid, -preused, desc,
		// utype);
	}

	/**
	 * ����ģ����Ϣ�����뷢������
	 * 
	 * @param job
	 * @return
	 */
	public int addTmplMsg(WxTemplateJob job) {
		return wxTemplateJobDao.addTemplateJob(job);
	}

	public List<WxTemplate> findWxTemplate(long owner, String type, long entityid) {
		if(entityid>0)
		return wxTemplateDao.getTemplateList(owner, type, entityid);
		else
		return wxTemplateDao.getTemplateList(owner, type);	
	}

	public String findTokenByPageidAndWbuid(long pageid, long wbuid) {
		return pageDao.findTokenByPageidAndWbuid(pageid, wbuid);
	}

	public void setOwnerBalanceSetDao(IOwnerBalanceSetDao ownerBalanceSetDao) {
		this.ownerBalanceSetDao = ownerBalanceSetDao;
	}

	public void setOwnerLotteryUserDao(IOwnerLotteryUserDao ownerLotteryUserDao) {
		this.ownerLotteryUserDao = ownerLotteryUserDao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	public WeiBoService getLongWS() {
		return longWS;
	}

	public void setLongWS(WeiBoService longWS) {
		this.longWS = longWS;
	}

	public WeiBoService getShortWS() {
		return shortWS;
	}

	public void setShortWS(WeiBoService shortWS) {
		this.shortWS = shortWS;
	}

	public WeiBoService getMustShortWS() {
		return mustShortWS;
	}

	public void setMustShortWS(WeiBoService mustShortWS) {
		this.mustShortWS = mustShortWS;
	}

	public WeiBoService getJustWS() {
		return justWS;
	}

	public void setJustWS(WeiBoService justWS) {
		this.justWS = justWS;
	}

	public void setUserChanceRecordDao(ILotteryUserChanceRecordDao userChanceRecordDao) {
		this.userChanceRecordDao = userChanceRecordDao;
	}

	public void setOwnerBalanceDao(IOwnerBalanceDao ownerBalanceDao) {
		this.ownerBalanceDao = ownerBalanceDao;
	}

	public void setOwnerBalanceRecordDao(IOwnerBalanceRecordDao ownerBalanceRecordDao) {
		this.ownerBalanceRecordDao = ownerBalanceRecordDao;
	}

	public void setHdUserDataDao(IHdUserDataDao hdUserDataDao) {
		this.hdUserDataDao = hdUserDataDao;
	}

	public void setWxTemplateJobDao(IWxTemplateJobDao wxTemplateJobDao) {
		this.wxTemplateJobDao = wxTemplateJobDao;
	}

	public void setWxTemplateDao(IWxTemplateDao wxTemplateDao) {
		this.wxTemplateDao = wxTemplateDao;
	}

}
