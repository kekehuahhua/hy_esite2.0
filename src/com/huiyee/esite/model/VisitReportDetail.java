package com.huiyee.esite.model;

import java.io.Serializable;

public class VisitReportDetail implements Serializable{

	//������
	private int visitnum;
	//����ʱ��
	private String visittime;
	//�����ն�
	private String terminal;
	//΢���ǳ�
	private String nickname;
	private long uid;
	
	public int getVisitnum() {
		return visitnum;
	}
	public void setVisitnum(int visitnum) {
		this.visitnum = visitnum;
	}
	public String getVisittime() {
		return visittime;
	}
	public void setVisittime(String visittime) {
		this.visittime = visittime;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
}
