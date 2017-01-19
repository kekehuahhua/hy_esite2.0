package com.huiyee.esite.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.dto.FeatureMgrDto;
import com.huiyee.esite.dto.IDto;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.util.HyConfig;

public class FeatureMgrAction extends AbstractAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1351684611487140331L;
	private IFeatureCompose featureCompose;
	private FeatureMgrDto mgrDto;
	private long featureid;
	private long fid;
	private IDto dto;
	private String result;
	private long pageid;
	private long pfid;//es_page_feature id
	private long siteId;
	private long relationid;
	private String type;
	private String source;
	
	public FeatureMgrAction() {
		 if (dto == null) {
				String featureid = ServletActionContext.getRequest().getParameter(
						"featureid");
				String className = "com.huiyee.esite.dto.Feature" + featureid
						+ "Dto";
				try {
					dto = (IDto) Class.forName(className).newInstance();
				} catch (InstantiationException e) {
//					e.printStackTrace();
				} catch (IllegalAccessException e) {
//					e.printStackTrace();
				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
				}
			}
	}
	
	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	//����
	public String config() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = featureCompose.config(featureid, fid,account);
		return SUCCESS;
	}
	
	public String configNew() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		dto = featureCompose.configNew(featureid, fid,account,relationid);
		return SUCCESS;
	}
	
	//��������
	public String configSub() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		result = featureCompose.configSub(featureid,dto,account);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	public String configSubNew() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		result = featureCompose.configSubNew(featureid,dto,account);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	//ɾ��feature
	public String delPageFeature() throws Exception{
		int result = featureCompose.delete(pfid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	//��ʾ���ӹ���
	public String add() throws Exception{
		Account account = (Account) ServletActionContext.getRequest().getSession().getAttribute("account");
		mgrDto = (FeatureMgrDto) featureCompose.add(pageid,account.getOwner().getId());
		return SUCCESS;
	}
	
	//�༭Դ�ļ�
	public String edit_page_source() throws Exception{
		Page p = pageCompose.findPageByid(pageid);
		File file = new File(HyConfig.getShowPagePath()+"/"+p.getJspname());// ָ��Ҫ��ȡ���ļ�  
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));// ��ȡ���ļ���������  
        char[] bb = new char[1024];// ��������ÿ�ζ�ȡ�����ַ�  
        String str = "";// ������ÿ�ζ�ȡ�����ַ�ƴ��
        int n;// ÿ�ζ�ȡ�����ַ�����  
        while ((n = br.read(bb)) != -1) {  
            str += new String(bb, 0, n);  
        }  
        source = StringEscapeUtils.escapeHtml4(str);
        br.close();// �ر����������ͷ�����  
		return SUCCESS;
	}
	
	public String save_page_source() throws Exception{
	    HttpServletResponse response = ServletActionContext.getResponse();
	    response.setHeader("pragma", "no-cache");
	    response.setHeader("cache-control", "no-cache");
	    response.setContentType("text/html; charset=utf-8");
		PrintWriter pw = response.getWriter();
		Page p = pageCompose.findPageByid(pageid);
		File file = new File(HyConfig.getShowPagePath()+"/"+p.getJspname());// Ҫд����ı��ļ�  
        if (!file.exists()) {// ����ļ������ڣ��򴴽����ļ�
        	pw.print("���ļ�������");
        } else{
        	
        	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));// ��ȡ���ļ��������  
        	bw.write(source);// д����  
        	bw.flush();// ��ջ�������������������������д���ļ���  
        	bw.close();// �ر��������ʩ����Դ 
        	pw.print(1);
        }
		pw.flush();
		pw.close();
		return null;
	}
	
	//�������ӹ���
	public String addSub() throws Exception{
		featureCompose.addSub(pageid, mgrDto);
		result = "Y";
		return SUCCESS;
	}
	
	//����
	public String up() throws Exception{
		int result = featureCompose.up(pfid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	//����
	public String down() throws Exception{
		int result = featureCompose.down(pfid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	
	//ҳ��
	public String pub() throws Exception{
		int result = featureCompose.pub(pageid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
		return null;
	}
	//ҳ��
    public String pubPages() throws Exception{
        int result = featureCompose.pubBySiteId(siteId);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out = response.getWriter();
        out.print(result);
        out.flush();
        out.close();
        return null;
    }
	
	public IDto getDto() {
		return dto;
	}

	public void setDto(IDto dto) {
		this.dto = dto;
	}

	public String getResult() {
		return result;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public FeatureMgrDto getMgrDto() {
		return mgrDto;
	}

	public void setMgrDto(FeatureMgrDto mgrDto) {
		this.mgrDto = mgrDto;
	}

	public long getPfid() {
		return pfid;
	}

	public void setPfid(long pfid) {
		this.pfid = pfid;
	}

    public long getSiteId() {
        return siteId;
    }

    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

	public long getRelationid()
	{
		return relationid;
	}

	public void setRelationid(long relationid)
	{
		this.relationid = relationid;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
