package com.huiyee.esite.model;

import java.io.Serializable;

public class OwnerSetting implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long ownerid;
	private String odomain;
	private String wxappid;//΢��Ӧ�õ�id������̼����������̼��Լ���΢��Ӧ��
	private String wxsecret;//΢��Ӧ�õ���Կ

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public String getOdomain()
	{
		return odomain;
	}

	public void setOdomain(String odomain)
	{
		this.odomain = odomain;
	}

	public String getWxappid()
	{
		return wxappid;
	}

	public void setWxappid(String wxappid)
	{
		this.wxappid = wxappid;
	}

	public String getWxsecret()
	{
		return wxsecret;
	}

	public void setWxsecret(String wxsecret)
	{
		this.wxsecret = wxsecret;
	}
}
