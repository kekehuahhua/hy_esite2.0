package com.huiyee.esite.dao;

public interface ILotteryUserChanceRecordDao
{
	/**
	 * 
	 * @param entityid �������л�ͶƱ��ڱ���id
	 * @param featureid 118-���� 123-ͶƱ 124-���� 134-�ڱ�
	 * @param userid wbuid��wxuid
	 * @param utype 0-sina 1-weixin
	 * @return ��õĳ齱����
	 */
	public int findUserChanceTotal(long entityid,long featureid,long userid,int utype);
	
	/**
	 * 
	 * @param entityid �������л�ͶƱ��ڱ���id
	 * @param featureid 118-���� 123-ͶƱ 124-���� 134-�ڱ�
	 * @param userid wbuid��wxuid
	 * @param utype 0-sina 1-weixin
	 * @param num ��ӳ齱����
	 * @return
	 */
	public void addLotteryChanceRecord(long entityid,long featureid,long userid,int utype,int num,long lid);
}
