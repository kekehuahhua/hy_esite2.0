package com.huiyee.esite.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.dto.BalancePageDto;
import com.huiyee.esite.dto.GrCenterDto;
import com.huiyee.esite.mgr.IGrCenterMgr;
import com.huiyee.esite.model.BalancePay;
import com.huiyee.esite.model.BalanceRule;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionContext;

public class GrCenterAction extends AbstractAction{
	
	private static final long serialVersionUID = -7627892742816913877L;
	private IGrCenterMgr grCenterMgr;
	private GrCenterDto dto;
	private BalanceRule rule;
	private int price;
	private long payid;
	private long ruleid;
	private String url;
	private String code;
	
	public void setGrCenterMgr(IGrCenterMgr grCenterMgr) {
		this.grCenterMgr = grCenterMgr;
	}
	
	/**
	 * �û�����
	 */
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new GrCenterDto();
		if(vu != null){
			dto = (GrCenterDto) grCenterMgr.findGrCenterData(vu.getHyUserId(),this.getOwner(),vu.getHyUser());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * �ҵĴ�ֵ
	 * @throws Exception
	 */
	public void findMyBalanceByHyUid() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new GrCenterDto();
		if(vu != null){
			dto = (GrCenterDto) grCenterMgr.findMyBalanceByHyUid(vu.getHyUserId(),this.getOwner());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
	}
	
	public String saveBalancePay() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser() != null){
			payid = grCenterMgr.saveBalancePay(ip,vu.getHyUserId(),this.getOwner(),ruleid);//�����¼
		}
		return SUCCESS;
	}
	
	
	/**
	 * ֧��ҳ�棨������ֵ��
	 * @return
	 * @throws Exception
	 */
	public String findBalanceRuleById() throws Exception{
		rule = grCenterMgr.findBalanceRuleById(ruleid,this.getOwner());//��ѯ֧�����
		return SUCCESS;
	}
	
	public String payBalanceCallBack() throws Exception{
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		String id = null;String type = null;//type  S:΢�̳ǳ�ֵ H:����ǩ�� ɨ���ά��ǩ��
		if(vu != null){
			 id = vu.getSkey();
			 type = vu.getSvalue();
			 grCenterMgr.updateHyUserLevel(this.getOwner(), vu.getHyUserId(), price,vu);
		}
		if("S".equals(type) || "H".equals(type)){ //��Ҫ��ת��֧������
			url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/ownerPayBanlance.action?id="+id +"&type="+type;
		}else{
			BalancePageDto d = grCenterMgr.findBalanceSetByOwner(this.getOwner());
			if(d != null){
				url = HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshow/" +d.getRmyid() +"/wxn.html";
			}
		}
		return SUCCESS;
	}
	
	/**
	 * ֧����ɲ�ѯ ״̬
	 * @throws Exception
	 */
	public void findBalancePayById() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		BalancePay balance = grCenterMgr.findBalancePayById(payid);
		Gson gson = new Gson();
		out.print(gson.toJson(balance));
		out.flush();
		out.close();
	}
	
	public void updateSession() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if(vu != null && vu.getHyUser() != null){
			res = grCenterMgr.updateSession(vu);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
	}
	
	/**
	 * ͨ����֤��������Ա�ȼ�
	 * @return
	 */
	public void updateLevelBycheckCode() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		int res = 0;
		if(vu != null && vu.getHyUser() != null){
			res = grCenterMgr.updateLevelBycheckCode(this.getOwner(),vu.getHyUserId(),code,vu);
		}
		Gson gson = new Gson();
		out.print(gson.toJson(res));
		out.flush();
		out.close();
	}
	
	/**
	 * ��Ա��֤
	 */
	public void memberConfirm() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new GrCenterDto();
		if(vu != null){
			dto = (GrCenterDto) grCenterMgr.findMemberData(this.getOwner(),vu.getHyUser());
		}
		Gson gson = new Gson();
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
	}
	
	
	public GrCenterDto getDto() {
		return dto;
	}

	public void setDto(GrCenterDto dto) {
		this.dto = dto;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getPayid() {
		return payid;
	}

	public void setPayid(long payid) {
		this.payid = payid;
	}

	public BalanceRule getRule() {
		return rule;
	}

	public void setRule(BalanceRule rule) {
		this.rule = rule;
	}

	public long getRuleid() {
		return ruleid;
	}

	public void setRuleid(long ruleid) {
		this.ruleid = ruleid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
