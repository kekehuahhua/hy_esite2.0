
package com.huiyee.interact.xc.model;

import java.io.Serializable;
import java.util.Date;

public class CheckinConfig implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2721175198584138223L;
	private String note;// ǩ���ɹ���ʾ��
	private String atype = "N";// �Ƿ��޶�ǩ���� Y:�޶��������˿�ǩ�� ;G:�޶���ע��;N:���޶�
	private long gzid;// wx_gz_event id
	private Date starttime = new Date();
	private Date endtime = new Date();

	public Date getStarttime()
	{
		return starttime;
	}

	public void setStarttime(Date starttime)
	{
		this.starttime = starttime;
	}

	public Date getEndtime()
	{
		return endtime;
	}

	public void setEndtime(Date endtime)
	{
		this.endtime = endtime;
	}

	public String getAtype()
	{
		return atype;
	}

	public void setAtype(String atype)
	{
		this.atype = atype;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public long getGzid()
	{
		return gzid;
	}

	public void setGzid(long gzid)
	{
		this.gzid = gzid;
	}

}
