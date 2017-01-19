package com.huiyee.interact.offcheck.mgr;

import com.huiyee.esite.mgr.imp.AbstractMgr;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.offcheck.dao.IOffCheckDzWayDao;
import com.huiyee.interact.offcheck.dto.CheckRs;


public class OffCheckDzWayMgr extends AbstractMgr implements IOffCheckDzWayMgr
{
	private IOffCheckDzWayDao offCheckDzWayDao;
	
	public void setOffCheckDzWayDao(IOffCheckDzWayDao offCheckDzWayDao)
	{
		this.offCheckDzWayDao = offCheckDzWayDao;
	}

	@Override
	public CheckRs updateDzWay(VisitUser vu, int rmb, long owner, long csid)
	{
		CheckRs rs=new CheckRs();
		if(vu==null||vu.getWxUser()==null){
			rs.setStatus(-1);
			rs.setHydesc("�û������ڣ�");
			return rs;	
		}
		if(!this.isDz(owner, "t1", vu.getHyUserId()))
		{
			rs.setStatus(-10005);
			rs.setHydesc("û����ӪԱȨ�ޣ�");
			return rs;	
		}
		long id=offCheckDzWayDao.findDzWayId(vu.getWxuid(), csid, rmb);
		if(id==0)
		{
			offCheckDzWayDao.addDzWay(vu.getWxuid(), csid, rmb);
		}
		id=offCheckDzWayDao.findDzWayId(vu.getWxuid(), csid, rmb);
		rs.setStatus(1);
		rs.setDzway(id);
		return rs;
	}
}
