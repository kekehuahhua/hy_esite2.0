package com.huiyee.esite.dto;

public class MoveToGroupDto implements IDto
{
	private long id; //�������id
	private String type;// * t:ͶƱ* d:����* f:��* l:�ҽ� * y:ҡһҡ * z:ת��
	private String area;
	private int fensi;
	private long asigngid;// Ŀ����
	private long xcid;
	
	public long getXcid()
	{
		return xcid;
	}

	
	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getArea()
	{
		return area;
	}
	
	public void setArea(String area)
	{
		this.area = area;
	}
	
	public int getFensi()
	{
		return fensi;
	}
	
	public void setFensi(int fensi)
	{
		this.fensi = fensi;
	}
	
	public long getAsigngid()
	{
		return asigngid;
	}
	
	public void setAsigngid(long asigngid)
	{
		this.asigngid = asigngid;
	}
	
	
}
