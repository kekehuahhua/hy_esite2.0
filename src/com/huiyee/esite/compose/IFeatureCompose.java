package com.huiyee.esite.compose;

import java.util.List;
import java.util.Map;

import com.huiyee.esite.dto.DynamicActionDto;
import com.huiyee.esite.dto.ExportDto;
import com.huiyee.esite.dto.FeatureMgrDto;
import com.huiyee.esite.dto.HdDetailDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.QueryObject;

public interface IFeatureCompose {
	
	/**
	 * ��ʾ����ҳ��
	 * @param featureid
	 * @param fid
	 * @param account
	 * @return
	 */
	public IDto config(long featureid, long fid,Account account);
	
	public IDto configNew(long featureid, long fid,Account account,long relationid);
	/**
	 * �����ύ
	 * @param featureid
	 * @param dto
	 * @param account
	 * @return
	 */
	public String configSub(long featureid,IDto dto, Account account);
	
	public String configSubNew(long featureid,IDto dto, Account account);
	
	/**
	 * ҳ������
	 * @param pageid
	 * @return
	 */
	public int pub(long pageid);
	
	/**
	 * ҳ��Ԥ��
	 * @param pageid
	 * @return
	 */
	public Map<String,Object> show(long pageid,String type,String source);
	
	/**
	 * ɾ��feature
	 * @param pfid es_page_feature ��� id
	 * @return
	 */
	public int delete(long pfid);
	
	/**
	 * ��ʾ����
	 * @param pageid
	 * @return
	 */
	public IDto add(long pageid,long ownerid);
	
	/**
	 * ���ӱ���
	 * @param pageid
	 * @param dto
	 * @return
	 */
	public int addSub(long pageid,FeatureMgrDto dto);
	
	/**
	 * ����
	 * @param pfid
	 * @return
	 */
	public int up(long pfid);
	
	/**
	 * ����
	 * @param pfid
	 * @return
	 */
	public int down(long pfid);
	
	/**
	 * �û�����
	 * @param featureid
	 * @param uid
	 * @param dto
	 * @return
	 */
	public String dynamicAction(long featureid,long uid,DynamicActionDto dto);
	
	/**
	 * ����pageid fid �ҵ���Ӧ��ownerid
	 * @param pageid
	 * @param fid
	 * @return
	 */
	public long findOwneridByPageidAndFid(long pageid,long fid);

	/**
	 * �������浼��
	 * @param featureid
	 * @param sitegroupid
	 * @param id
	 * @param exportDto 
	 * @return
	 */
	public List<String> export(long featureid, long sitegroupid, long id, ExportDto exportDto);
	
	public int pubBySiteId(long siteid);
	
	public IDto composeHdReport(long siteid,long ownerid);
	
	public IDto composeHdReportDetail(long sitegroupid,long hdid,long hdfid,int pageId);
	
	public IDto composeQueryHdReportDetail(long sitegroupid,long hdid,long hdfid,HdDetailDto dto,int pageId);
	
	public long composeCreatePage(String pagename, String jspname, long siteid,String stype);

	
}
