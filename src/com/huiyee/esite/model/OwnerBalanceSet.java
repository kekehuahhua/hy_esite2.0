package com.huiyee.esite.model;

public class OwnerBalanceSet 
{
	private long id;
	private long owner;
	private int sharenum;//����
	private int ssharenum;//��������ķ���
	private int sgznum;//��������Ĺ�ע
	private int newnum;//�¹�ע
	private int topicnum;//������
	private int comnum;//����
	private int topnum;//�����ö�
	private int upnum;//��
	private int downum;//��
	private int ocnum;//����ǩ��
	private int yynum;//΢ԤԼ
	private int yypjnum;//�����ṩ�����ۻ�ö��ٻ���
	private long cachetime;//����ʱ��
	private int ocxfnum;//�������Ѷ��ٿ���1����
	
	public int getOcxfnum()
	{
		return ocxfnum;
	}
	
	public void setOcxfnum(int ocxfnum)
	{
		this.ocxfnum = ocxfnum;
	}

	public int getYypjnum()
	{
		return yypjnum;
	}
	
	public void setYypjnum(int yypjnum)
	{
		this.yypjnum = yypjnum;
	}


	public int getOcnum()
	{
		return ocnum;
	}

	
	public int getYynum()
	{
		return yynum;
	}

	
	public void setOcnum(int ocnum)
	{
		this.ocnum = ocnum;
	}

	
	public void setYynum(int yynum)
	{
		this.yynum = yynum;
	}

	public long getCachetime()
	{
		return cachetime;
	}
	
	public void setCachetime(long cachetime)
	{
		this.cachetime = cachetime;
	}

	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getOwner()
	{
		return owner;
	}
	
	public void setOwner(long owner)
	{
		this.owner = owner;
	}
	
	public int getSharenum()
	{
		return sharenum;
	}
	
	public void setSharenum(int sharenum)
	{
		this.sharenum = sharenum;
	}
	
	public int getSsharenum()
	{
		return ssharenum;
	}
	
	public void setSsharenum(int ssharenum)
	{
		this.ssharenum = ssharenum;
	}
	
	public int getSgznum()
	{
		return sgznum;
	}
	
	public void setSgznum(int sgznum)
	{
		this.sgznum = sgznum;
	}
	
	public int getNewnum()
	{
		return newnum;
	}
	
	public void setNewnum(int newnum)
	{
		this.newnum = newnum;
	}
	
	public int getTopicnum()
	{
		return topicnum;
	}
	
	public void setTopicnum(int topicnum)
	{
		this.topicnum = topicnum;
	}
	
	public int getComnum()
	{
		return comnum;
	}
	
	public void setComnum(int comnum)
	{
		this.comnum = comnum;
	}
	
	public int getTopnum()
	{
		return topnum;
	}
	
	public void setTopnum(int topnum)
	{
		this.topnum = topnum;
	}
	
	public int getUpnum()
	{
		return upnum;
	}
	
	public void setUpnum(int upnum)
	{
		this.upnum = upnum;
	}
	
	public int getDownum()
	{
		return downum;
	}
	
	public void setDownum(int downum)
	{
		this.downum = downum;
	}
	
	
}
