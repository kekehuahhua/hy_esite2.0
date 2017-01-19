package com.huiyee.esite.dto;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.AreaAnalysis;

public class VisiterDto implements IDto {

	//����
	private List<AreaAnalysis>  areas;
	
	//����
	private long num;
	//���һ�η���ʱ��
	private Date nearDate;
	
	private Pager pager;
	
	
	public List<AreaAnalysis> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaAnalysis> areas) {
		this.areas = areas;
	}
	public long getNum() {
		return num;
	}
	public void setNum(long num) {
		this.num = num;
	}
	public Date getNearDate() {
		return nearDate;
	}
	public void setNearDate(Date nearDate) {
		this.nearDate = nearDate;
	}
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	
	
}
