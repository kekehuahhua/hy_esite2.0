package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

public class OrderMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1178448164216475186L;
	private long id;
	//ԤԼ����
	private String title;
	//ԤԼ�绰
	private String phone;
	//�޶���
	private String limitnum;
	//�ܶ�������
	private String total;
	//��ʼʱ��
	private Date begintime;
	//����ʱ��
	private Date endtime;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLimitnum() {
		return limitnum;
	}
	public void setLimitnum(String limitnum) {
		this.limitnum = limitnum;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	

}
