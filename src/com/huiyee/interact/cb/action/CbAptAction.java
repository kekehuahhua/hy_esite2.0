
package com.huiyee.interact.cb.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.dto.HdRsDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.AppointmentRecord;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.util.HttpRequestDeviceUtils;
import com.huiyee.interact.appointment.model.AppointmentModel;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.interact.cb.mgr.IActivityMatterMgr;
import com.huiyee.interact.cb.mgr.IInteractCbMgr;
import com.huiyee.interact.cb.model.CbSender;
import com.opensymphony.xwork2.ActionContext;

/**
 * �ϻ��˵�����
 * 
 * @author ldw
 * 
 */
public class CbAptAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7147696704553523952L;
	private String source;
	private AppointmentRecord aptRecord;
	private IActivityMatterMgr activityMatterMgr;
	private int status;//�ϻ����Ƿ���Ҫ�����׼ 0-��Ҫ 1-����Ҫ

	public void setActivityMatterMgr(IActivityMatterMgr activityMatterMgr)
	{
		this.activityMatterMgr = activityMatterMgr;
	}

	public void setInteractCbMgr(IInteractCbMgr interactCbMgr)
	{
		this.interactCbMgr = interactCbMgr;
	}

	private String result;
	private long cid;
	private long relationid;
	private long aptid;
	private int start;
	private int size;
	private int cansee;// 0-�ܿ��������˵�����,1-ֻ�ܿ����Լ���
	private IInteractCbMgr interactCbMgr;

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public long getAptid()
	{
		return aptid;
	}

	public void setAptid(long aptid)
	{
		this.aptid = aptid;
	}

	public long getPageid()
	{
		return pageid;
	}

	public void setPageid(long pageid)
	{
		this.pageid = pageid;
	}

	private long pageid;

	@Override
	public String execute() throws Exception
	{
		// ��֤�Ƿ��ܼ�������
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		AppointmentModel apt = interactCbMgr.findAppointById(aptRecord.getAptid());
		HdRsDto rs = pageCompose.ineractCanRun(apt);
		if (rs.getStatus() == 10000)
		{
			String userAgent = request.getHeader("user-agent");
			String ip = ClientUserIp.getIpAddr(request);
			VisitUser visit = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
			try
			{
				/**
				 * �ϻ��˵������¼�ύ
				 */
				if(visit.getWxUser()==null){
					rs.setStatus(-11000);
					rs.setHydesc("��Ҫ΢�Ż�����");
				}else{
					rs = interactCbMgr.saveCustomCommentRepotr(visit, aptRecord, ip, HttpRequestDeviceUtils.getMediaDevice(userAgent), source, relationid, apt, this.getOwner());
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				rs.setStatus(-11000);
				rs.setHydesc("�ύʧ�ܣ��������⣡");
			}

			pageCompose.canRunLog(apt.getId(), "f", request);
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;
	}

	/*
	 * �鿴�ϻ���״̬
	 */
	public String checkSenderStatus() throws Exception
	{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = ClientUserIp.getIpAddr(request);
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		CbSender rs = new CbSender();
		if (vu != null)
		{
			rs = activityMatterMgr.findSenderStatus(vu, this.getOwner());
		} else
		{
			rs.setReason("��̨����!");
		}
		out.print(new Gson().toJson(rs));
		out.flush();
		out.close();
		return null;

	}
	
	public String senderPizhun() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		int rs = interactCbMgr.updateAptAcpt(account, status);
		out.print(rs);
		out.flush();
		out.close();
		return null;
	
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public void setAptRecord(AppointmentRecord aptRecord)
	{
		this.aptRecord = aptRecord;
	}

	public AppointmentRecord getAptRecord()
	{
		return aptRecord;
	}

	public long getCid()
	{
		return cid;
	}

	public void setCid(long cid)
	{
		this.cid = cid;
	}

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public int getCansee()
	{
		return cansee;
	}

	public void setCansee(int cansee)
	{
		this.cansee = cansee;
	}

	
	public int getStatus()
	{
		return status;
	}

	
	public void setStatus(int status)
	{
		this.status = status;
	}
}
