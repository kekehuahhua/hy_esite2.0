
package com.huiyee.weixin.dto;

public class WkqGetRs
{

	private int status;//-1��û��������֤��ҳ�棻-2�����������ڣ�-3����ȯ��û��֧���ɹ���1����ȡ�ɹ�
	private String hydesc;
	private String url;

	public int getStatus()
	{
		return status;
	}

	public String getHydesc()
	{
		return hydesc;
	}

	public String getUrl()
	{
		return url;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public void setHydesc(String hydesc)
	{
		this.hydesc = hydesc;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
