
package com.huiyee.interact.xc.model;

import java.io.Serializable;

public class XcInfo implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 33777415715305488L;

	private int invitedNum;// ����ɹ�����
	private int checkedNum;// ǩ������

	public int getInvitedNum()
	{
		return invitedNum;
	}

	public void setInvitedNum(int invitedNum)
	{
		this.invitedNum = invitedNum;
	}

	public int getCheckedNum()
	{
		return checkedNum;
	}

	public void setCheckedNum(int checkedNum)
	{
		this.checkedNum = checkedNum;
	}
}
