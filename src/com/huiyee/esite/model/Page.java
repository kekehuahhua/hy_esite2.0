package com.huiyee.esite.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String jspname;
	private String jspstyle;
	private long siteid;
	private Date createtime;
	private Date updatetime;
	private String status;
	private String type;
	private String stype;
	private String isonline;
	private String wol;
	private Long relationid;
	private Long contextid;
	private String bctype;// �����жϳɹ���ʧ��
	private String subsina;
	private String subweixin;

	private List<PageCard> pageCards;
	private long pageid;
	private String bg;
	private String pic;
	private String title;
	private long entityid;

	private long pv;
	private long uv;

	private List<WeiXinPage> list;

	private String paramValue;

	// NOR-��ͨ;SHQ-����;
	// CBS-�ϻ�������;CBR-��������;CBN-��������;CBY-����������
	// ADD-���Ͷ��ҳ��;
	// WSD-΢�̳����õ���ҳ;WSH-΢�̳���ҳ;WSU-΢�̳Ǹ�������ҳ;KYZ-��ȯ��֤;KYS-��ȯ��֤�ɹ�;KYF-��ȯ��֤ʧ��;WSL-΢�̳��б�ҳ
	// OCS-����ǩ���ɹ�ҳ;OCF-����ǩ��ҳ;OCZ-��ӪԱҳ��;OCD-���ɶ�̬��ά���ҳ��;OCJ-���ѻ�û��ֳɹ���ҳ��
	// YYS-ԤԼ�ɹ�ҳ;YYX-ԤԼ����ҳ;YYD-ԤԼ���õ���ҳ;YYQ-����ȷ��ԤԼҳ��;YYZ-ԤԼ��ȷ��ҳ��;FWL-�����б�FZL-�������б�FWX-�������飻FZX-����������
	// ZSJ-վ���������ҳ;
	// PJT-���������ύҳ;PJX-��������ҳ;PJR-�����ظ�����ҳ;PJS-������Ա�б�ҳ;PJC-�����б�ҳ
	// RMX-�ҵĻ�Ա����RMY-�ҵĴ�ֵ��RMJ-�ҵĻ��֣�RMK-�ҵĿ�ȯ��RMC-��Ϣ��д�ɹ���ҳ�棻RMZ-�ҵ��˵�
	private String apptype;
	private String sitename;

	public static String APPTYPE_ADD = "ADD";

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getBctype() {
		return bctype;
	}

	public void setBctype(String bctype) {
		this.bctype = bctype;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public List<PageCard> getPageCards() {
		return pageCards;
	}

	public void setPageCards(List<PageCard> pageCards) {
		this.pageCards = pageCards;
	}

	public Long getRelationid() {
		return relationid;
	}

	public void setRelationid(Long relationid) {
		this.relationid = relationid;
	}

	public Long getContextid() {
		return contextid;
	}

	public void setContextid(Long contextid) {
		this.contextid = contextid;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}

	public String getWol() {
		return wol;
	}

	public void setWol(String wol) {
		this.wol = wol;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJspname() {
		return jspname;
	}

	public void setJspname(String jspname) {
		this.jspname = jspname;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSubsina() {
		return subsina;
	}

	public void setSubsina(String subsina) {
		this.subsina = subsina;
	}

	public String getSubweixin() {
		return subweixin;
	}

	public void setSubweixin(String subweixin) {
		this.subweixin = subweixin;
	}

	public String getBg() {
		return bg;
	}

	public void setBg(String bg) {
		this.bg = bg;
	}

	public JSONObject getBgJson() {
		if (!StringUtils.isEmpty(bg)) {
			return JSONObject.fromObject(bg);
		}
		return null;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getJspstyle() {
		return jspstyle;
	}

	public void setJspstyle(String jspstyle) {
		this.jspstyle = jspstyle;
	}

	public List<WeiXinPage> getList() {
		return list;
	}

	public void setList(List<WeiXinPage> list) {
		this.list = list;
	}

	public long getEntityid() {
		return entityid;
	}

	public void setEntityid(long entityid) {
		this.entityid = entityid;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public JSONObject getParam() {
		if (paramValue != null) {
			return JSONObject.fromObject(paramValue);
		}
		return null;
	}

	public long getPv() {
		return pv;
	}

	public void setPv(long pv) {
		this.pv = pv;
	}

	public long getUv() {
		return uv;
	}

	public void setUv(long uv) {
		this.uv = uv;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApptype() {
		return apptype;
	}

	public void setApptype(String apptype) {
		this.apptype = apptype;
	}

}
