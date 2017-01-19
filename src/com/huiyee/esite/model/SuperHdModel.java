
package com.huiyee.esite.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class SuperHdModel implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected long id;
	protected long ownerid;
	protected String title;//����
	protected String content;//����
	protected Date createtime;//����ʱ��
	protected String status;//״̬ ɾ��,�༭��
	protected long featureid;
	protected Date starttime;//���ʼʱ��
	protected Date endtime;//�����ʱ��
	protected String startnote;// ��ʼ��ʾ��
	protected String endnote;// ������ʾ��
	protected int totallimit = -1;// ÿ���ܴ��� -1��ʾû���ô˲���
	protected int daylimit = -1;// ÿ��ÿ����� -1��ʾû���ô˲���
	protected int balance;// ���ӵĻ���
	protected String islottery;// �Ƿ�����齱
	protected String lotterytype;//�齱����
	protected long lotteryid;// �����齱��id
	protected int lotterychance;// ���ӵĳ齱����
	protected int maxlotterychance;// ������ӵĴ���
	protected long omid;//������

	
	public long getOmid()
	{
		return omid;
	}

	
	public void setOmid(long omid)
	{
		this.omid = omid;
	}

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttimeDate(Date starttime)
	{

		this.starttime = starttime;
	}

	public void setStarttime(String starttime)
	{
		if (StringUtils.isNotBlank(starttime))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date start = sdf.parse(starttime);
				this.starttime = start;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtimeDate(Date endtime)
	{

		this.endtime = endtime;
	}

	public void setEndtime(String endtime)
	{
		if (StringUtils.isNotBlank(endtime))
		{
			try
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date end = sdf.parse(endtime);
				this.endtime = end;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public String getStartnote()
	{
		return startnote;
	}

	public void setStartnote(String startnote)
	{
		this.startnote = startnote;
	}

	public String getEndnote()
	{
		return endnote;
	}

	public void setEndnote(String endnote)
	{
		this.endnote = endnote;
	}

	public int getTotallimit()
	{
		return totallimit;
	}

	public void setTotallimit(int totallimit)
	{
		this.totallimit = totallimit;
	}

	public int getDaylimit()
	{
		return daylimit;
	}

	public void setDaylimit(int daylimit)
	{
		this.daylimit = daylimit;
	}

	public long getFeatureid()
	{
		return featureid;
	}

	public void setFeatureid(long featureid)
	{
		this.featureid = featureid;
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	public String getIslottery()
	{
		return islottery;
	}

	public void setIslottery(String islottery)
	{
		this.islottery = islottery;
	}

	public long getLotteryid()
	{
		return lotteryid;
	}

	public void setLotteryid(long lotteryid)
	{
		this.lotteryid = lotteryid;
	}

	public int getLotterychance()
	{
		return lotterychance;
	}

	public void setLotterychance(int lotterychance)
	{
		this.lotterychance = lotterychance;
	}

	public int getMaxlotterychance()
	{
		return maxlotterychance;
	}

	public void setMaxlotterychance(int maxlotterychance)
	{
		this.maxlotterychance = maxlotterychance;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getLotterytype()
	{
		return lotterytype;
	}

	public void setLotterytype(String lotterytype)
	{
		this.lotterytype = lotterytype;
	}

}
