package com.huiyee.interact.lottery.dto;

import java.util.List;

public class LotteryJoin
{
	private int joinnum;// �齱����
	private int winnum;// �н�����
	private int failnum;// ʧ�ܴ���
	private List<String> lpname;// ��Ʒ����

	public int getJoinnum()
	{
		return joinnum;
	}

	public void setJoinnum(int joinnum)
	{
		this.joinnum = joinnum;
	}

	public int getWinnum()
	{
		return winnum;
	}

	public void setWinnum(int winnum)
	{
		this.winnum = winnum;
	}

	public int getFailnum()
	{
		return failnum;
	}

	public void setFailnum(int failnum)
	{
		this.failnum = failnum;
	}

	public List<String> getLpname()
	{
		return lpname;
	}

	public void setLpname(List<String> lpname)
	{
		this.lpname = lpname;
	}
}
