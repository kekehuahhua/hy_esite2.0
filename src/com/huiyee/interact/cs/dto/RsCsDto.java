package com.huiyee.interact.cs.dto;

import java.io.Serializable;

public class RsCsDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;// 1:����ɹ�,-1:visituser������
	private long rid;//��¼��id
	private String hydesc;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}


	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public long getRid()
	{
		return rid;
	}

	public void setRid(long rid)
	{
		this.rid = rid;
	}
}
