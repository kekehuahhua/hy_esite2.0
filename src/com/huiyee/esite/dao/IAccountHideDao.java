package com.huiyee.esite.dao;

import java.util.List;

public interface IAccountHideDao {

	/**
	 * �鿴���[�ص�ģ�K
	 * @param ownerid
	 * @param accountid
	 * @param type L���Ҳ���Ŀ I:����  C:���ݷ���
	 * @return
	 */
	public List<Long> findHidIds(long ownerid,long accountid,String type);
	
}
