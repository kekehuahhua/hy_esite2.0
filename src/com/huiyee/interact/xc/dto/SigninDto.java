package com.huiyee.interact.xc.dto;

import java.util.List;

import com.huiyee.interact.xc.model.XcCheckinRecord;


public class SigninDto
{

	private int count;//ǩ������
	private List<XcCheckinRecord> list; //ǩ����¼
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<XcCheckinRecord> getList() {
		return list;
	}
	public void setList(List<XcCheckinRecord> list) {
		this.list = list;
	}
}
