package com.huiyee.weixin.model;

/**
 * ֧���ɹ� ģ����Ϣ
 * @author Administrator
 *
 */
public class PayOrderTemplate
{
	private long id;
    private String openid;
	private String name;
	
	public long getId()
	{
		return id;
	}
	
	public String getOpenid()
	{
		return openid;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public void setOpenid(String openid)
	{
		this.openid = openid;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

}
