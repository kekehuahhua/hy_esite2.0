package project.caidan.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import project.caidan.dto.CaiDanTeamDto;
import project.caidan.mgr.ICaiDanTeamMgr;
import project.caidan.model.CDAccountDl;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.model.VisitUser;
import com.opensymphony.xwork2.ActionContext;

/**
 * Ͷעվ  �Ŷ�action
 * @author Administrator
 *
 */
public class CaiDanTeamAction extends AbstractAction
{
	private static final long serialVersionUID = 5996384132146888582L;
	private ICaiDanTeamMgr cdTeamMgr;
	private CaiDanTeamDto dto = new CaiDanTeamDto();
	private long id;
	private long clid;
	private CDAccountDl dl;
	private String status = "N";
	
	public void setCdTeamMgr(ICaiDanTeamMgr cdTeamMgr)
	{
		this.cdTeamMgr = cdTeamMgr;
	}

	/**
	 * �Ŷӷ�չ
	 */
	@Override
	public String execute() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser() != null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.findUserIdentityByWxUid(wxuid,"PRZ",this.getOwner());//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}

	/**
	 * �Ŷ�ҵ��
	 * @return
	 * @throws Exception
	 */
	public String findTeamBusiness() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser() != null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.findTeamBusiness(wxuid,"PRZ",this.getOwner());//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * ��������
	 * @return
	 * @throws Exception
	 */
	public String channelCheck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser() != null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.channelCheck(wxuid,"PRZ",this.getOwner(),status);//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * ���
	 */
	public String doCheckPre() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser() != null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.doCheckPre(wxuid,"PRZ",this.getOwner(),id);//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
		
	}
	
	public String doCheck() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser() != null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.updatedoCheck(wxuid,"PRZ",this.getOwner(),id,clid,status);//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
		
	}
	
	/**
	 * ���ύ
	 * @return
	 */
	public String subForm() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null && vu.getWxUser()!= null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.saveForm(wxuid,"PRZ",dl);//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	/**
	 * res : 1: �Լ������Լ�
	 * @return
	 * @throws Exception
	 */
	public String findSubUser() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new CaiDanTeamDto();
		if(vu != null && vu.getWxUser()!= null){
			long wxuid = vu.getWxUser().getId();
			if(dl.getFawxuid() != wxuid){
				dto = (CaiDanTeamDto) cdTeamMgr.findSubUser(wxuid,"PRZ",dl);//PRZ-��������
			}else{
				dto.setRes(1);
			}
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	public String findSubUserInfo() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		dto = new CaiDanTeamDto();
		if(vu != null && vu.getWxUser()!= null){
			long wxuid = vu.getWxUser().getId();
			dto = (CaiDanTeamDto) cdTeamMgr.findSubUserInfo(wxuid,"PRZ",dl);//PRZ-��������
		}
		out.print(gson.toJson(dto));
		out.flush();
		out.close();
		return null;
	}
	
	
	public CaiDanTeamDto getDto()
	{
		return dto;
	}

	
	public void setDto(CaiDanTeamDto dto)
	{
		this.dto = dto;
	}

	
	public long getId()
	{
		return id;
	}

	
	public void setId(long id)
	{
		this.id = id;
	}

	
	public long getClid()
	{
		return clid;
	}

	
	public void setClid(long clid)
	{
		this.clid = clid;
	}

	
	public CDAccountDl getDl()
	{
		return dl;
	}

	
	public void setDl(CDAccountDl dl)
	{
		this.dl = dl;
	}

	
	public String getStatus()
	{
		return status;
	}

	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	
}
