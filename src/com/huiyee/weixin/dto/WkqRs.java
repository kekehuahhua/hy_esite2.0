
package com.huiyee.weixin.dto;

public class WkqRs
{

	private int status;//-1�����������ڣ�-2����ȯ�����ڣ�-3����ȯ�Ѿ�ʹ�ã�-4�����ǵ�������ݣ�-5���벻Ҫ�۸Ķ�ά���ַ����1-��֤�ɹ���
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
