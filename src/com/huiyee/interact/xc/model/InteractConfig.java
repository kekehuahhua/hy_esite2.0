
package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class InteractConfig implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 88555223496207420L;
	private String atype = "Q";// �Ƿ��޶�ǩ���� Q:�޶���ǩ���߿����� C:�����˿ɲ��뻥�� N:���޶�

	public InteractConfig()
	{
		this.atype = "Q";
	}

	public InteractConfig(String str)
	{
		this.atype = str;
	}

	public String getAtype()
	{
		return atype;
	}

	public void setAtype(String atype)
	{
		this.atype = atype;
	}

}
