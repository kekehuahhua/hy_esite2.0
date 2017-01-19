package com.huiyee.esite.ws;

import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

@WebService
public interface EsiteWebService {

	/**
	 * �����˺��Ƿ���� 
	 * @param ownerId
	 * @param accountId
	 * @param accountname
	 * @return 1��ʾ���� 0��ʾ������
	 */
//	public int findAccount(long ownerId,long accountId,String accountname);
	
	/**
	 * ͬһƽ̨�����˺�,����accountId
	 * @param ownerId
	 * @param accountId
	 * @param accountname
	 * @return
	 */
	public long addAccount(long ownerId,long accountId,String accountname,int dbId);
	
	/**
	 * ��������
	 * @param owner
	 * @param dbId
	 * @return
	 */
	public String findEsiteData(long owner,int dbId);
	
	
	/**
	 * ������վ es_owner_setting
	 * @param owner
	 * @param odomain
	 * @param wxappid
	 * @param wxsecret
	 * @return
	 */
	public int updateOwnerSetting(String json);
	
	/**
	 * ��ȡ��վowner_setting
	 * @param id
	 * @return json
	 */
	public String findEsiteOwnerSet(long id);
	
	/**
	 * ��ȡbbs_account��Ϣ
	 * @param owner
	 * @return
	 */
	public String findbbsAccount(long owner);
	
	/**
	 * ����bbs_account
	 * @param json
	 * @return
	 */
	public int updatebbsAccount(String json);
}
