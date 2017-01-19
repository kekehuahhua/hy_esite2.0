
package com.huiyee.interact.cb.dao;

import java.util.Map;

import com.huiyee.interact.cb.model.HbConfig;
import com.huiyee.interact.cb.model.InteractCb;

public interface IInteractCbDao
{

	public InteractCb findCbById(long cbid);

	public long saveInteractCb(InteractCb cb);

	public HbConfig findCbhbConfig(long cbid);

	public int updateHbConfig(long cbid, HbConfig hbc);

	public void updateApptype(long cbid, long id);

	public long findCbStatusPageid(long pageid);

	/**
	 * ���ºϻ����Ƿ���Ҫ��׼����
	 * @param cbid
	 * @param status
	 * @return
	 */
	public int updateAptAcpt(long cbid, int status);

	public InteractCb findCbByOwner(long owner);

	public int updateUsedSiteGroup(long owner, Map<String, Long> map);
	
}
