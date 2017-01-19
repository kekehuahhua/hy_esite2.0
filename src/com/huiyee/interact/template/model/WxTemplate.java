package com.huiyee.interact.template.model;

import java.util.Date;

/**
 * @author xn
 *
 */
public class WxTemplate {

	private long id;
	private long owner;

	// WDS-΢���̹���ɹ�,WDC-΢���̽������
	// JFS-�����̳ǹ���ɹ�,JFC-�����̳ǽ������
	// OCF-����ǩ����������֪ͨ;OCT-����ǩ����������֪ͨ;XFJ-���ѻ�û���
	// YYU-ԤԼ(�û�);YYD-ԤԼ(����)
	// FWP-��������֪ͨ;FWR-�����ظ�֪ͨ
	// CZU-��Ա��ֵ�ɹ�֪ͨ;CZD-��ֵ֪ͨ�̼�;XFU-��Ա��������;XFD-����֪ͨ�̼�;SQD-�������Ա
	private String type;
	private long entityid;
	private String remark;

	private long store_id;
	private long mpid;
	private String template_id;
	private String data;
	private String url;
	private Integer delay;
	private String sendtime;
	private Date updatetime;

	private WxTemplateStore store;
	private boolean showRemark;
	private boolean showUrl;
	private boolean showSend;
	private int dtype;
	
	public int getDtype() {
		return dtype;
	}

	public void setDtype(int dtype) {
		this.dtype = dtype;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOwner() {
		return owner;
	}

	public void setOwner(long owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getEntityid() {
		return entityid;
	}

	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getStore_id() {
		return store_id;
	}

	public void setStore_id(long store_id) {
		this.store_id = store_id;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public WxTemplateStore getStore() {
		return store;
	}

	public void setStore(WxTemplateStore store) {
		this.store = store;
	}

	public boolean isShowRemark() {
		return showRemark;
	}

	public void setShowRemark(boolean showRemark) {
		this.showRemark = showRemark;
	}

	public boolean isShowUrl() {
		return showUrl;
	}

	public void setShowUrl(boolean showUrl) {
		this.showUrl = showUrl;
	}

	public boolean isShowSend() {
		return showSend;
	}

	public void setShowSend(boolean showSend) {
		this.showSend = showSend;
	}

}