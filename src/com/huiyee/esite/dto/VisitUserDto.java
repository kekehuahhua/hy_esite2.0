package com.huiyee.esite.dto;

public class VisitUserDto implements IDto {

	//��ʼʱ��
	private String startdate;
	//����ʱ��
	private String enddate;
	//���ʴ���
	private String num;
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	
	
}
