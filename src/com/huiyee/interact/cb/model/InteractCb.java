
package com.huiyee.interact.cb.model;

import java.io.Serializable;
import java.util.Date;

import com.huiyee.interact.appointment.model.AppointmentModel;

public class InteractCb implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7621649214347624225L;
	private long id;
	private String title;
	private long owner;
	private long aptid;
	private long siteid;
	private long sitegroupid;
	private Date createtime;
	private String wishing;
	private String act_name;
	private String remark;
	private long spageid;//CBS�ϻ�������ҳ��
	private long npageid;//CBN��������ҳ
	private long rpageid;//CBR��������ҳ
	private long ypageid;//CBY��������ҳ
	private int senderAccept;//�ϻ����Ƿ���Ҫ��� 0-��Ҫ 1-����Ҫ
	private AppointmentModel apt;
	private int pizhun;
	
	public int getPizhun() {
		return pizhun;
	}

	public void setPizhun(int pizhun) {
		this.pizhun = pizhun;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public long getSpageid()
	{
		return spageid;
	}

	
	public void setSpageid(long spageid)
	{
		this.spageid = spageid;
	}

	public long getOwner()
	{
		return owner;
	}

	public void setOwner(long owner)
	{
		this.owner = owner;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getSitegroupid()
	{
		return sitegroupid;
	}

	public void setSitegroupid(long sitegroupid)
	{
		this.sitegroupid = sitegroupid;
	}

	public Date getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(Date createtime)
	{
		this.createtime = createtime;
	}

	public AppointmentModel getApt()
	{
		return apt;
	}

	public void setApt(AppointmentModel apt)
	{
		this.apt = apt;
	}

	
	public int getSenderAccept()
	{
		return senderAccept;
	}

	
	public void setSenderAccept(int senderAccept)
	{
		this.senderAccept = senderAccept;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getAct_name() {
		return act_name;
	}

	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getNpageid() {
		return npageid;
	}

	public void setNpageid(long npageid) {
		this.npageid = npageid;
	}

	public long getRpageid() {
		return rpageid;
	}

	public void setRpageid(long rpageid) {
		this.rpageid = rpageid;
	}

	public long getYpageid() {
		return ypageid;
	}

	public void setYpageid(long ypageid) {
		this.ypageid = ypageid;
	}

}
