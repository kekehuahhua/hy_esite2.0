package com.huiyee.esite.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.constants.IInteractConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.JfUserDto;
import com.huiyee.esite.dto.SolrDataDto;
import com.huiyee.esite.mgr.IJfUserMgr;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.UserBalance;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.solr.HyJfSolrServer;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.interact.checkin.dto.Pager;
import com.huiyee.weixin.model.WxMp;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;


public class JfUserAction extends AbstractAction
{
	private static final long serialVersionUID = 3005335019980974124L;
	private JfUserDto dto;
	private IJfUserMgr jfUserMgr;
	private IWeiXinMgr weiXinMgr;
	private int pageId = 1;
	private String tab = "U";
	private Date startTime;
	private Date endTime;
	private String startStr;
	private String endStr;
	private int type; //1:���ӻ����û� 2:���Ļ����û� 3:δ���ӻ����û� 4:δ���Ļ����û�
	private HyJfSolrServer hyJfSolrServer; 
	private long hyuid;
	private String solrtype;
	private String solrstype;
	private String nickname;
	public void setHyJfSolrServer(HyJfSolrServer hyJfSolrServer)
	{
		this.hyJfSolrServer = hyJfSolrServer;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	@Permission(module=IPrivilegeConstants.MODULE_APP_΢����,privilege=IPrivilegeConstants.PERMISSION_R)
	public String jfUser() throws Exception{
		long owner =this.getOwner();
		long mpid = 0;
		WxMp mp = weiXinMgr.updateFindWxMp(owner, 0);
		if(mp!=null){
			mpid = mp.getId();
		}
		if(StringUtils.isNotBlank(startStr)){
			startTime = DateUtil.getDateTime(startStr);
		}
		if(StringUtils.isNotBlank(endStr)){
			endTime = DateUtil.getDateTime(endStr);
		}
		dto = (JfUserDto) jfUserMgr.findUserBalance(owner,mpid,pageId,hyuid,nickname,startTime,endTime,type);
		setLightType(1);
		return SUCCESS;
	}
	
	public String userRmb() throws Exception{
		long owner =this.getOwner();
		long mpid = 0;
		WxMp mp = weiXinMgr.updateFindWxMp(owner, 0);
		if(mp!=null){
			mpid = mp.getId();
		}
		if(StringUtils.isNotBlank(startStr)){
			startTime = DateUtil.getDateTime(startStr);
		}
		if(StringUtils.isNotBlank(endStr)){
			endTime = DateUtil.getDateTime(endStr);
		}
		dto = (JfUserDto) jfUserMgr.findUserBalance(owner,mpid,pageId,hyuid,nickname,startTime,endTime,type);
		setLightType(2);
		return SUCCESS;
	}
	
	
	/**
	 * ��̨ ���ֱ䶯����
	 * @return
	 * @throws Exception
	 */
	public String jfUserDetail() throws Exception{
		SolrDataDto sdd = hyJfSolrServer.findUserJfDetail(hyuid, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		int total = sdd.getTotal();
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			String stypename = getStype(stype);
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
	        String createStr = DateUtil.getDateString(created);
			doc.addField("createStr", createStr);
			doc.addField("stypename", stypename);
		}
		
		dto = new JfUserDto();
		Pager pager = new Pager(pageId, total, IInteractConstants.JF_LIMIT);
		dto.setPager(pager);
		dto.setUlist(list);
		return SUCCESS;
	}
	
	/**
	 * ��̨չʾ  rmb�䶯   
	 * @return
	 * @throws Exception
	 */
	public String userRmbDetail() throws Exception{
		SolrDataDto sdd = hyJfSolrServer.findUserRmbDetail(hyuid,solrtype, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		int total = sdd.getTotal();
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			String stypename = getStype(stype);
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
	        String createStr = DateUtil.getDateString(created);
	        String hhmm = DateUtil.gerDateFormat7(created);
			doc.addField("createStr", createStr);
			doc.addField("hhmm", hhmm);
			doc.addField("stypename", stypename);
		}
		
		dto = new JfUserDto();
		Pager pager = new Pager(pageId, total, IInteractConstants.JF_LIMIT);
		dto.setPager(pager);
		dto.setUlist(list);
		return SUCCESS;
	}
	
	/**
	 * �������Ļ�����ϸ CHB  Ӷ����ϸCHR
	 * @return
	 * @throws Exception
	 */
	public String userSolrDetail() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		SolrDataDto sdd = hyJfSolrServer.userSolrDetail(hyuid,solrtype, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		
		List<SolrDocument> list = new ArrayList<SolrDocument>();
		GroupCommand gc = sdd.getGclist().get(0);
		for (Group g : gc.getValues()) {
			SolrDocument doc = g.getResult().get(0);
			Integer cday = (Integer) doc.get("cday");
			Date created = (Date) doc.get("created");
			Calendar ca = Calendar.getInstance();
			ca.setTime(created);
			ca.add(Calendar.HOUR_OF_DAY, -8);
			created = ca.getTime();
			String createStr = DateUtil.getDateString(created);
			doc.addField("createStr", createStr);
			
			SolrDataDto sdd1 = hyJfSolrServer.userSolrDetail(hyuid,solrtype,cday);
			SolrDocumentList list1 = sdd1.getDocumentList();
			for(int j=0;j<list1.size();j++){
				SolrDocument doc1 = list1.get(j);
				Date created1 = (Date) doc1.get("created");
				String stype = (String) doc1.get("stype");
				String stypename = getStype(stype);
				String openid = (String)doc1.get("m_s");
				WxUser wxUser = weiXinMgr.findWxUserByOpenid(openid);
				Calendar cal = Calendar.getInstance();
				cal.setTime(created1);
				cal.add(Calendar.HOUR_OF_DAY, -8);
				created1 = cal.getTime();
				doc1.setField("created", created1);
				String hhmm = DateUtil.gerDateFormat7(created1);
				doc1.addField("hhmm", hhmm);
				doc1.addField("stypename", stypename);
				doc1.addField("wxUser", wxUser);
			}
			doc.addField("nlist", list1);
			list.add(doc);
		}
		
		dto = new JfUserDto();
		dto.setUlist(list);
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	/**
	 * ɨ��ǹɨ���¼
	 * @return
	 */
	public String userBillRecord() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		SolrDataDto sdd = hyJfSolrServer.findUserBill(this.getOwner());
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			String stypename = getStype(stype);
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
			
			String createStr = DateUtil.getDateString(created);
			String hhmmss = DateUtil.getDateTimeString(created);
			doc.addField("createStr", createStr);
			doc.addField("hhmmss", hhmmss);
			doc.addField("stypename", stypename);
		}
		dto = new JfUserDto();
		dto.setUlist(list);
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	
	//������ϸ
	public String userJfDetail() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			hyuid = vu.getHyUserId();
		}
		SolrDataDto sdd = hyJfSolrServer.findUserJfDetail(hyuid, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			String stypename = getStype(stype);
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
			
			String createStr = DateUtil.getDateString(created);
			String hhmmss = DateUtil.getDateTimeString(created);
			doc.addField("createStr", createStr);
			doc.addField("hhmmss", hhmmss);
			doc.addField("stypename", stypename);
		}
		dto = new JfUserDto();
		dto.setUlist(list);
		UserBalance ub = jfUserMgr.findUserBalanceByHyUid(hyuid);
		if(ub != null){
			dto.setImg(ub.getImg());
			dto.setNickname(ub.getNickname());
			dto.setJf(ub.getTotal() - ub.getUsed());
		}
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String getRecordBySolr() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			hyuid = vu.getHyUserId();
		}
		SolrDataDto sdd = hyJfSolrServer.getRecordBySolr(hyuid,solrtype,solrstype, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			String stypename = getStype(stype);
			int score=(Integer) doc.get("score");
			doc.addField("scoreAbs", Math.abs(score));
			doc.addField("scoreYuan", (float)Math.abs(score)/100);
			if(score>0){
				doc.addField("mc", 1);
			}else{
				doc.addField("mc", 0);
			}
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
			
			String createStr = DateUtil.getDateString(created);
			String hhmmss = DateUtil.getDateTimeString(created);
			doc.addField("createStr", createStr);
			doc.addField("hhmmss", hhmmss);
			doc.addField("stypename", stypename);
		}
		JSONObject json = new JSONObject();
		json.put("list", list);
		pw.print(json);
		pw.flush();
		pw.close();
		return null;
	}
	
	
	//��������
	public String findUserJfAddDetail() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			hyuid = vu.getHyUserId();
		}
		SolrDataDto sdd = hyJfSolrServer.findUserJfAddDetail(hyuid, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
			
			String stypename = getStype(stype);
			String createStr = DateUtil.getDateString(created);
			doc.addField("createStr", createStr);
			doc.addField("stypename", stypename);
		}
		dto = new JfUserDto();
		dto.setUlist(list);
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	//����֧��
	public String findUserJfSubDetail() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		VisitUser vu = (VisitUser) ActionContext.getContext().getSession().get("visitUser");
		if(vu != null){
			hyuid = vu.getHyUserId();
		}
		SolrDataDto sdd = hyJfSolrServer.findUserJfSubDetail(hyuid, (pageId - 1) * IInteractConstants.JF_LIMIT, IInteractConstants.JF_LIMIT);
		SolrDocumentList list = sdd.getDocumentList();
		for(int i=0;i<list.size();i++){
			SolrDocument doc = list.get(i);
			Date created = (Date) doc.get("created");
			String stype = (String) doc.get("stype");
			Calendar cal = Calendar.getInstance();
	        cal.setTime(created);
	        cal.add(Calendar.HOUR_OF_DAY, -8);
	        created = cal.getTime();
	        doc.setField("created", created);
			String stypename = getStype(stype);
			String createStr = DateUtil.getDateString(created);
			doc.addField("createStr", createStr);
			doc.addField("stypename", stypename);
		}
		dto = new JfUserDto();
		dto.setUlist(list);
		pw.print(gson.toJson(dto));
		pw.flush();
		pw.close();
		return null;
	}
	
	public String getStype(String stype){
		String stypename = "";
		if("APT".equals(stype)){
			stypename = "����";
		}else if("LOT".equals(stype)){
			stypename = "�齱";
		}else if("RSH".equals(stype)){
			stypename = "����";
		}else if("SPR".equals(stype)){
			stypename = "��ɢ";
		}else if("VOT".equals(stype)){
			stypename = "ͶƱ";
		}else if("SRR".equals(stype)){
			stypename = "��������";
		}else if("SSH".equals(stype)){
			stypename = "��������ķ���";
		}else if("SGZ".equals(stype)){
			stypename = "��������Ĺ�ע";
		}else if("SCL".equals(stype)){
			stypename = "��������ĵ��";
		}else if("TPC".equals(stype)){
			stypename = "������";
		}else if("CMT".equals(stype)){
			stypename = "��������";
		}else if("TOP".equals(stype)){
			stypename = "�����ö�";
		}else if("UPP".equals(stype)){
			stypename = "��";
		}else if ("DOW".equals(stype)) {
			stypename = "��";
		}else if ("NGZ".equals(stype)) {
			stypename = "�¹�ע";
		}else if ("CHK".equals(stype)) {
			stypename = "ǩ��";
		}else if("CHZ".equals(stype)){
			stypename = "��ֵ����";
		}else if ("SHA".equals(stype)) {
			stypename = "ת��";
		}else if ("CLK".equals(stype)) {
			stypename = "���";
		}else if ("GZH".equals(stype)) {
			stypename = "��ע";
		}else if ("HUD".equals(stype)) {
			stypename = "����";
		}else if ("WBU".equals(stype)) {
			stypename = "�ⲿ";
		}else if("TDL".equals(stype)){
			stypename = "����";
		}else if("TQA".equals(stype)){
			stypename = "����";
		}else if("DHS".equals(stype)){
			stypename = "�һ�";
		}else if("FHS".equals(stype)){
			stypename = "��������";
		}else if("DKP".equals(stype)){
			stypename = "�ֿ��ֽ�";
		}else if("YYU".equals(stype)){
			stypename = "ԤԼ�ɹ�";
		}else if("FWP".equals(stype)){
			stypename = "��������";
		}else if("OFC".equals(stype)){
			stypename = "����ǩ��";
		}else if("XFJ".equals(stype)){
			stypename = "���ѻ���";
		}else if("CDD".equals(stype)){
			stypename = "����";
		}else if("CDP".equals(stype)){
			stypename = "���ֻ�";
		}else if("RCG".equals(stype)){
			stypename = "��ֵ";
		}else if("XXX".equals(stype)){
			stypename = "��������";
		}else if("XSX".equals(stype)){
			stypename = "��������";
		}else if("SMQ".equals(stype)){
			stypename = "ɨ��ǹ����";
		}
		return stypename;
	}
	
	public JfUserDto getDto()
	{
		return dto;
	}

	
	public void setDto(JfUserDto dto)
	{
		this.dto = dto;
	}

	
	public int getPageId()
	{
		return pageId;
	}

	
	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}

	
	public void setJfUserMgr(IJfUserMgr jfUserMgr)
	{
		this.jfUserMgr = jfUserMgr;
	}


	
	public String getTab()
	{
		return tab;
	}

	public void setTab(String tab)
	{
		this.tab = tab;
	}


	
	public Date getStartTime()
	{
		return startTime;
	}


	
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}


	
	public Date getEndTime()
	{
		return endTime;
	}


	
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}


	
	public int getType()
	{
		return type;
	}


	
	public void setType(int type)
	{
		this.type = type;
	}

	
	public long getHyuid()
	{
		return hyuid;
	}

	
	public void setHyuid(long hyuid)
	{
		this.hyuid = hyuid;
	}

	
	public String getStartStr()
	{
		return startStr;
	}

	
	public void setStartStr(String startStr)
	{
		this.startStr = startStr;
	}

	
	public String getEndStr()
	{
		return endStr;
	}

	
	public void setEndStr(String endStr)
	{
		this.endStr = endStr;
	}

	
	public String getSolrtype()
	{
		return solrtype;
	}

	
	public void setSolrtype(String solrtype)
	{
		this.solrtype = solrtype;
	}

	
	public String getSolrstype()
	{
		return solrstype;
	}

	
	public void setSolrstype(String solrstype)
	{
		this.solrstype = solrstype;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	
	
}
