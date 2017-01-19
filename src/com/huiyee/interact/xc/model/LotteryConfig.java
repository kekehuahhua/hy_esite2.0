package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class LotteryConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -821026699749790008L;
	private int started;
	private int startnum = 1;
	private int num;// ȡ������
	private String type = "J";// J:����齱 C:���벢������н��� P:����齱
	private String atype = "N";// ָ���齱 Q:ǩ�� N:����Ҫ
	private String unique = "N";//Y:�н�Ψһ, N:���ظ��н�

	public int getStarted() {
		return started;
	}

	public void setStarted(int started) {
		this.started = started;
	}

	public int getStartnum() {
		return startnum;
	}

	public void setStartnum(int startnum) {
		this.startnum = startnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getUnique() {
		return unique;
	}

	public void setUnique(String unique) {
		this.unique = unique;
	}

}
