package com.huiyee.interact.renqi.dto;

import java.io.Serializable;

public class RsRqDto implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;// 1:����ɹ�,-1:�Ѿ������,-2:fuid������
	private int jf;// ���������߻����˶�������
	private String hydesc;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public int getJf()
	{
		return jf;
	}

	public void setJf(int jf)
	{
		this.jf = jf;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}
}
