package com.huiyee.esite.model;

import java.io.Serializable;

public class Redirect implements Serializable  {
	
	private static final long serialVersionUID = -7082317204699805996L;
	private String type;//��ת����
	private String url;//��ת��ַ
	private String urlShow;//��ʾ�ĵ�ַ ����http://
	private String urlPre;//urlǰ׺
	private String furl;//��ת��ַ
	private String furlShow;//��ʾ�ĵ�ַ ����http://
	private long toPageid;
	
	public long getToPageid()
	{
		return toPageid;
	}
	
	public void setToPageid(long toPageid)
	{
		this.toPageid = toPageid;
	}
	public String getUrlPre()
	{
		return urlPre;
	}
	public void setUrlPre(String urlPre)
	{
		this.urlPre = urlPre;
	}
	public String getUrlShow()
	{
		return urlShow;
	}
	public void setUrlShow(String urlShow)
	{
		this.urlShow = urlShow;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}

	
	public String getFurl()
	{
		return furl;
	}

	
	public void setFurl(String furl)
	{
		this.furl = furl;
	}

	
	public String getFurlShow()
	{
		return furlShow;
	}

	
	public void setFurlShow(String furlShow)
	{
		this.furlShow = furlShow;
	}

}
