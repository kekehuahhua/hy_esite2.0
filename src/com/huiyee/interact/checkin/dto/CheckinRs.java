package com.huiyee.interact.checkin.dto;

public class CheckinRs
{
	private int status;// 0-����ʧ��,1-���ĳɹ�
	private String hydesc;
	private long jf; //ǩ������
	private int daynum;//����ǩ������
	
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

	
	public long getJf()
	{
		return jf;
	}

	
	public void setJf(long jf)
	{
		this.jf = jf;
	}

	
	public int getDaynum()
	{
		return daynum;
	}

	
	public void setDaynum(int daynum)
	{
		this.daynum = daynum;
	}
	
	
}
