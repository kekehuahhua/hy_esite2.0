package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class CommentConfig implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3507332163630957433L;
	private String note;
	private String atype = "N";// �Ƿ��޶������� Q:�޶���ǩ���߿����� N:���޶�
	private int maxnum;//ÿ��������۵Ĵ���
	private String checked = "N";// �Ƿ���� Y:�� N:��

	public String getChecked()
	{
		return checked;
	}

	public void setChecked(String checked)
	{
		this.checked = checked;
	}

	public String getAtype()
	{
		return atype;
	}

	public void setAtype(String atype)
	{
		this.atype = atype;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public int getMaxnum()
	{
		return maxnum;
	}

	public void setMaxnum(int maxnum)
	{
		this.maxnum = maxnum;
	}
}
