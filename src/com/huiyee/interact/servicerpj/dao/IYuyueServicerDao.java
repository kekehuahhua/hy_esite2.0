package com.huiyee.interact.servicerpj.dao;

import java.util.List;
import java.util.Map;

import com.huiyee.interact.servicerpj.model.ServicerPjWd;
import com.huiyee.interact.yuyue.model.YuYueServicer;

/**
 * ���������õ�DAO �����index top��ָ�����ݿ��oidx otop�ֶ�
 * 
 * @author ldw
 *
 */
public interface IYuyueServicerDao {

	public int findServicerTotalByOwner(long owner);

	public List<YuYueServicer> findServicers(long owner, int start, int size);
	
	public List<ServicerPjWd> findServicerPjWdListBySerid(long serid);

	public int findServicerMaxIdx(long owner);

	public long saveYuYueServicer(YuYueServicer yuYueServicer);

	/**
	 * ������Աʱȥ��caid�µ�type=0����
	 * 
	 * @param yyid
	 * @param caid
	 * @param type
	 * @return
	 */
	public List<YuYueServicer> findServicerByCaid(long yyid, long caid, long type);

	public int updateServicerToPj(List<YuYueServicer> list);

	public YuYueServicer findServicerByid(long id);

	public int updateServicerBack(long owner, long id);

	public int delServicer(long owner, long id);

	public YuYueServicer findFrontServicer(long owner, int oidx, int otop);

	public YuYueServicer findNextServicer(long owner, int oidx, int otop);

	public int updateServicerOidx(YuYueServicer servicer);

	public int updateServicerTop(long id, long serid, int top);

	public int updateYuYueServicer(YuYueServicer yuYueServicer);

	public List<YuYueServicer> findServicers(long owner);

	public int updateServicerPage(long owner, int source, Map<String, Long> map);
}
