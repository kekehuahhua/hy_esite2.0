
package com.huiyee.interact.xc.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.Account;
import com.huiyee.interact.xc.mgr.IXcMgr;
import com.huiyee.interact.xc.mgr.IXccleanMgr;

/**
 * ����ֳ���������
 * 
 * @author ldw
 * 
 */
public class XccleanAction extends AbstractAction
{

	private IXccleanMgr xccleanMgr;
	private long xcid;
	private String inviteclean="Y";

	@Override
	public String execute() throws Exception
	{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		// -1-û�д˻��0-�δ��ʼ��1-���ʼ��2-��ѽ���
		int result = xccleanMgr.updateXcclean(account, xcid,inviteclean);
		out.print(new Gson().toJson(result));

		out.flush();
		out.close();
		return null;
	}

	public long getXcid()
	{
		return xcid;
	}

	public void setXcid(long xcid)
	{
		this.xcid = xcid;
	}

	public void setXccleanMgr(IXccleanMgr xccleanMgr)
	{
		this.xccleanMgr = xccleanMgr;
	}

	
	public String getInviteclean()
	{
		return inviteclean;
	}

	
	public void setInviteclean(String inviteclean)
	{
		this.inviteclean = inviteclean;
	}

}
