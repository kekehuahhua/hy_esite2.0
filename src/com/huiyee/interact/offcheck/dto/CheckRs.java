
package com.huiyee.interact.offcheck.dto;

public class CheckRs
{

	private int status;//-4��������ӪԱ��-3��û��ע���Ա��Ϣ��-2:�����˿�μӣ�-1���û������ڣ�0����Դ�����ڣ�1-ǩ���ɹ�
	private long pageid;
	private long dzway;
	private String url;
	private String hydesc;
	private int balance;//�䶯����
	private int rmb;
	
	public int getRmb()
	{
		return rmb;
	}

	public void setRmb(int rmb)
	{
		this.rmb = rmb;
	}

	public long getDzway()
	{
		return dzway;
	}

	public void setDzway(long dzway)
	{
		this.dzway = dzway;
	}

	public int getStatus()
	{
		return status;
	}

	public long getPageid()
	{
		return pageid;
	}

	public String getUrl()
	{
		return url;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
