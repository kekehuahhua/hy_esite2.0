package com.huiyee.esite.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.model.InteractModel;

public interface IInteractModelDao {
	
	/**
	 * ���л���ģ��
	 * @return
	 */
	public List<InteractModel> findInteractModel();

	/**
	 * �û�ѡ�е�չʾģ��
	 * @param owner
	 * @return
	 */
	public List<InteractModel> findInteractModel(long owner);

	public void removeInteract(long owner);

	/**
	 * �����û�ģ�� ������ɾ����
	 * @param owner
	 * @return
	 */
	public List<InteractModel> findAllInteractModel(long owner);

	/**
	 * ���ownerinteract
	 * @param title
	 * @param mid
	 * @param owner
	 * @return
	 */
	public int addOwnerInteract(String title, long mid, long owner);

	public int deleteOwnerInteract(long lid, long ownerid);

	public int updateOwnerInteract(long lid, String title, String status, long ownerid);

	public int findTotalByMid(long mid, long owner);

	public InteractModel findInteractModelById(long omid);

	public int updateOwnerInteractStatus(long lid, long owner, String status);

	public int updateOwnerInteractTitle(long lid, long owner, String title);

	public int findCountByTitle(String title, long owner);
	
}
