package com.huiyee.interact.lottery.dto;

import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.interact.lottery.model.LotteryPrize;
import com.huiyee.interact.lottery.model.LotteryPrizeCode;

public class LotteryRs extends HdRsDto
{
	//private int status;//-1-�������ﵽ,-2-û�г齱����,-3-û�д˳齱(��ʱ����),-4-����ָ���û���,-5-����΢�Ź�ע��,-6-�û�������(��ʱ�ò���),-7-���ֲ����齱,-8-ָ���û�����������, 0-δ�н�,1-���˻���,2-�����Ż݄�,3-����ʵ��
	private long lotteryUserRecordid;
	//private String hydesc;
	private LotteryPrize lotteryPrize;
	private LotteryPrizeCode lotteryPrizeCode;
	private int znum;//��ʣ�����н�����
	
	public int getZnum()
	{
		return znum;
	}
	
	public void setZnum(int znum)
	{
		this.znum = znum;
	}

	public LotteryPrize getLotteryPrize()
	{
		return lotteryPrize;
	}

	public void setLotteryPrize(LotteryPrize lotteryPrize)
	{
		this.lotteryPrize = lotteryPrize;
	}

	public LotteryPrizeCode getLotteryPrizeCode()
	{
		return lotteryPrizeCode;
	}

	public void setLotteryPrizeCode(LotteryPrizeCode lotteryPrizeCode)
	{
		this.lotteryPrizeCode = lotteryPrizeCode;
	}

	public String getResult(){
		switch (this.getStatus()) {
			case 0 :
				return "лл����!";
			case 1 :
				return "����"+lotteryPrize.getPrice()+"����";
			case 2 :
				return "����"+lotteryPrize.getName();
			case 3 :
				return "����"+lotteryPrize.getName();
			default :
				return "лл����!";
		}
	}

	public long getLotteryUserRecordid()
	{
		return lotteryUserRecordid;
	}

	public void setLotteryUserRecordid(long lotteryUserRecordid)
	{
		this.lotteryUserRecordid = lotteryUserRecordid;
	}
}
