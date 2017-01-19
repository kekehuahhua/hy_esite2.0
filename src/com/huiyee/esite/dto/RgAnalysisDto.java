package com.huiyee.esite.dto;

import java.util.Date;
import java.util.List;

import com.huiyee.esite.model.AreaAnalysis;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;

public class RgAnalysisDto implements IDto {

	//����
	private List<AreaAnalysis>  area;
	
	private List<AreaAnalysis>  areas;
	//��������
	private long num;
	//���һ�εĻ���ʱ��
	private Date nearDate;
	//վ����
	private String siteName;
	
	private Pager pager;
	//��������
	private int visitnum;
	//��������
	private int hdnum;
	private Site site;
	
	public int getVisitnum() {
		return visitnum;
	}
	public void setVisitnum(int visitnum) {
		this.visitnum = visitnum;
	}
	public int getHdnum() {
		return hdnum;
	}
	public void setHdnum(int hdnum) {
		this.hdnum = hdnum;
	}

	public String getSiteName()
	{
		return siteName;
	}
	public void setSiteName(String siteName)
	{
		this.siteName = siteName;
	}
	public List<AreaAnalysis> getArea() {
		return area;
	}
	public void setArea(List<AreaAnalysis> area) {
		this.area = area;
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
	public List<AreaAnalysis> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaAnalysis> areas) {
		this.areas = areas;
	}
	public Site getSite()
	{
		return site;
	}
	public void setSite(Site site)
	{
		this.site = site;
	}

	
	
	
}
