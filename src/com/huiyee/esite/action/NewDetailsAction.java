package com.huiyee.esite.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.dto.NewDetailDto;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.opensymphony.xwork2.ActionContext;

public class NewDetailsAction extends AbstractAction{

	private static final long serialVersionUID = 3772715263942673122L;
	private NewDetailDto dto;
	private long relationid;
	private long nid;
	private long pcid;
	private String userAgent;
	private int cd;// ����ʶ��ǰ������;-1-Ĭ�ϻ���cookie,0-΢��,1-΢��,2-�û�������
	private Page page;
	private String type;
	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userA = request.getHeader("user-agent");
		userAgent = HttpRequestDeviceUtils.getMediaDevice(userA);
		VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(visit != null){
			cd = visit.getCd();
		}
		dto = (NewDetailDto)pageCompose.findNewDetails(relationid,nid,type);
		page = dto.getPage(); 
		if(dto.getTc() != null){
			pcid = dto.getTc().getId();
			return SUCCESS;
		}
		return "error";
	}
	
	public long getRelationid()
	{
		return relationid;
	}
	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}
	public long getNid()
	{
		return nid;
	}
	public void setNid(long nid)
	{
		this.nid = nid;
	}
	public long getPcid()
	{
		return pcid;
	}
	public void setPcid(long pcid)
	{
		this.pcid = pcid;
	}
	public NewDetailDto getDto() {
		return dto;
	}
	public String getUserAgent()
	{
		return userAgent;
	}
	public void setUserAgent(String userAgent)
	{
		this.userAgent = userAgent;
	}
	public int getCd()
	{
		return cd;
	}
	public void setCd(int cd)
	{
		this.cd = cd;
	}
	public Page getPage()
	{
		return page;
	}
	public void setPage(Page page)
	{
		this.page = page;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
