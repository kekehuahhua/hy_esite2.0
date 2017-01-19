
package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.dto.SiteCountDto;
import com.huiyee.esite.dto.SiteIndexActionDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.PageFileUtil;

public class SiteIndexAction extends AbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2482243392464883601L;
	private int pageId = 1;
	private String type = IPageConstants.SITE_TYPE_W;
	private SiteIndexActionDto dto;
	private SiteCountDto siteCountDto;
	private String sitename;
	private ArrayList<Long> moduleList;
	private String result;
	private long siteid;
	private long groupid;
	private long appid;
	private String groupname;
	private String groupType;
	private String copyType;// ���Ƶ�վ������:W-΢�ֳ� J-������ N-��ͨ S-΢վ

	// site�б�
	@Override
	public String execute() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteIndexActionDto) pageCompose.composeWbPageIndexAction(account, type, pageId);
		return SUCCESS;
	}

	// ����site
	public String site_create() throws Exception
	{
		dto = (SiteIndexActionDto) pageCompose.composeCreateSite();
		return SUCCESS;
	}

	// ����site����
	public String site_create_sub() throws Exception
	{
		if (StringUtils.isEmpty(sitename))
		{
			result = "΢��PAGE����Ϊ��!";
			return SUCCESS;
		}
		// if (moduleList == null || moduleList.size() == 0)
		// {
		// result = "���δѡ!";
		// return SUCCESS;
		// }
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteIndexActionDto) pageCompose.composeCreateSiteSub(account, type, sitename, moduleList, groupid, appid);
		result = dto.getResult();
		return SUCCESS;
	}

	// �޸�site
	public String site_update() throws Exception
	{
		dto = (SiteIndexActionDto) pageCompose.composeUpdateSite(siteid);
		sitename = dto.getSite().getName();
		moduleList = (ArrayList<Long>) dto.getModuleArr();
		return SUCCESS;
	}

	// �޸�site����
	public String site_update_sub() throws Exception
	{
		if (StringUtils.isEmpty(sitename))
		{
			result = "΢��PAGE����Ϊ��!";
			return SUCCESS;
		}
		// if (moduleList == null || moduleList.size() == 0)
		// {
		// result = "���δѡ!";
		// return SUCCESS;
		// }
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = (SiteIndexActionDto) pageCompose.composeUpdateSiteSub(account, siteid, sitename, moduleList);
		result = dto.getResult();
		return SUCCESS;
	}

	// ɾ��site
	public String del_site() throws Exception
	{
		int rs = pageCompose.composeDelSite(siteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	// ɾ��sitegroup
	public String delSiteGroup() throws Exception
	{
		int rs = pageCompose.deleteSiteGroup(groupid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	// �޸�sitegroup
	public String updateSiteGroup() throws Exception
	{
		int rs = 0;
		if (groupname != null && !"".equals(groupname))
		{
			rs = pageCompose.updateSiteGroup(groupname, groupid);
		} else
		{
			rs = 2;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	// ����sitegroup
	public String sitegroup_create() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		int rs = pageCompose.composeCreateSitegroup(groupname, account, groupType);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String createIndex()
	{
		dto = (SiteIndexActionDto) pageCompose.findTaoZhuangSite();
		return SUCCESS;
	}

	public String siteCopy() throws Exception
	{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		long rs = 0;
		if (account != null && StringUtils.isNotEmpty(copyType))
		{
			// W-΢�ֳ� J-������ N-��ͨ S-΢�̳� F-�����̳� B-΢���� C-���� A-����ǩ��
			rs = pageCompose.siteCopy(account.getOwner(), sitename, copyType, groupid);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(rs);
		out.flush();
		out.close();
		return null;
	}

	public String upWholeSite() throws Exception
	{
		String jspPath = HyConfig.getShowPagePath() + "/" + siteid;
		String resPath = HyConfig.getRoot() + "/res/" + siteid;

		PageFileUtil.copyFolder(jspPath, HyConfig.getRoot() + "/page2c/" + siteid);// ����jsp
		PageFileUtil.copyFolder(resPath, HyConfig.getRoot() + "/res2c/" + siteid);// ����res

		long ctime = System.currentTimeMillis();
		PageFileUtil.copyFolder(jspPath, HyConfig.getRoot() + "/page2c/" + siteid + "/back." + ctime);// ����jsp
		PageFileUtil.copyFolder(resPath, HyConfig.getRoot() + "/res2c/" + siteid + "/back." + ctime);// ����res

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(1);
		out.flush();
		out.close();
		return null;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	public SiteIndexActionDto getDto()
	{
		return dto;
	}

	public void setDto(SiteIndexActionDto dto)
	{
		this.dto = dto;
	}

	public String getSitename()
	{
		return sitename;
	}

	public void setSitename(String sitename)
	{
		this.sitename = sitename;
	}

	public String getResult()
	{
		return result;
	}

	public void setResult(String result)
	{
		this.result = result;
	}

	public ArrayList<Long> getModuleList()
	{
		return moduleList;
	}

	public void setModuleList(ArrayList<Long> moduleList)
	{
		this.moduleList = moduleList;
	}

	public long getSiteid()
	{
		return siteid;
	}

	public void setSiteid(long siteid)
	{
		this.siteid = siteid;
	}

	public long getGroupid()
	{
		return groupid;
	}

	public void setGroupid(long groupid)
	{
		this.groupid = groupid;
	}

	public SiteCountDto getSiteCountDto()
	{
		return siteCountDto;
	}

	public long getAppid()
	{
		return appid;
	}

	public void setAppid(long appid)
	{
		this.appid = appid;
	}

	public String getGroupname()
	{
		return groupname;
	}

	public void setGroupname(String groupname)
	{
		this.groupname = groupname;
	}

	public String getGroupType()
	{
		return groupType;
	}

	public void setGroupType(String groupType)
	{
		this.groupType = groupType;
	}

	public String getCopyType()
	{
		return copyType;
	}

	public void setCopyType(String copyType)
	{
		this.copyType = copyType;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
