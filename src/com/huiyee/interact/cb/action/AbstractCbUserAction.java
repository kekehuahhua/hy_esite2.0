
package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.interact.cb.dto.CbRsDto;
import com.huiyee.interact.cb.mgr.ICbDataMgr;
import com.huiyee.interact.cb.mgr.IInteractCbMgr;
import com.huiyee.interact.cb.model.InteractCb;
import com.opensymphony.xwork2.ActionContext;

public abstract class AbstractCbUserAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ICbDataMgr cbDataMgr;
	private IInteractCbMgr interactCbMgr;
 
	public boolean beforeExcute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu==null||vu.getWxUser()==null)
		{
		    InteractCb icb=	interactCbMgr.findOnlyCbbyid(this.getOwner());
			CbRsDto cr=new CbRsDto();
			cr.setStatus(-2);
			cr.setRs(this.getOnameUrl()+"/user/show/"+icb.getSpageid()+".html");//��Ҫ�ܹ�������
			Gson gson = new Gson();
			out.print(gson.toJson(cr));
			out.flush();
			out.close();
			return false;
		}
		Integer cs = (Integer) ActionContext.getContext().getSession().get("cdsender");
		//΢�ƹ�ĵ�¼�㵱��ĳ��action�̳��˴��࣬���¼��
		if(cs==null)//��ʱδ��¼����Ҫ������ǰuser��û�������Ϊsender
		{
		    int crs = cbDataMgr.findSender(vu.getHyUserId());
			if (crs == 0)// ˵�������ƹ�Ա
			{
				InteractCb icb = interactCbMgr.findOnlyCbbyid(this.getOwner());
				CbRsDto cr = new CbRsDto();
				cr.setStatus(-1);
				cr.setRs(this.getOnameUrl() + "/user/show/" + icb.getSpageid() + ".html");// ��Ҫ�ܹ�������
				Gson gson = new Gson();
				out.print(gson.toJson(cr));
				out.flush();
				out.close();
				return false;
			} else
			{
				 ActionContext.getContext().getSession().put("cdsender", cs);
			}
    	}
		
		return true;
	}
	
	public void setCbDataMgr(ICbDataMgr cbDataMgr)
	{
		this.cbDataMgr = cbDataMgr;
	}

	
	public void setInteractCbMgr(IInteractCbMgr interactCbMgr)
	{
		this.interactCbMgr = interactCbMgr;
	}


}
