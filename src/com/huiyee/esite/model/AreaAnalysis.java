package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;

/**
 * �������model
 * @author hy
 *
 */
public class AreaAnalysis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6643825604017702751L;
	//����
	private String area;
	//ip��ַ
	private String ip;
	//������
	private int num;
	//���Ļ���ʱ��
	private String lasttime;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getLasttime() {
		return lasttime;
	}
	public void setLasttime(String string) {
		this.lasttime = string;
	}
	
	
}
