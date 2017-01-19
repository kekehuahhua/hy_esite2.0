package com.huiyee.bdmap.dao;

import java.util.List;

import com.huiyee.bdmap.dto.BDLBSDto;
import com.huiyee.bdmap.dto.BDLBSRs;
import com.huiyee.bdmap.dto.BDPoint;

public interface IBDLBSDao
{
	public BDLBSRs saveLBS(BDPoint bdp,String tags,String key);

	public BDLBSRs updateLBS(long id, BDPoint bdp,String tags);
	
	/**
	 * 
	 * @param x ����
	 * @param y γ��
	 * @param tags ��ǩ
	 * @param ra  ��Բ�뾶����λ��
	 * @return
	 */
	public List<BDLBSDto> findLBS(String x,String y,String tags,int ra,int pagesize);
	
	
	public BDLBSRs deleteLBS(long id);
}
