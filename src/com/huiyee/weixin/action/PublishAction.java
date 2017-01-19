package com.huiyee.weixin.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.huiyee.esite.action.AbstractAction;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.mgr.IPageManager;
import com.huiyee.esite.mgr.ISiteManager;
import com.huiyee.esite.mgr.IWeiXinMgr;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.Page;
import com.huiyee.esite.model.PageAddress;
import com.huiyee.esite.model.Site;
import com.huiyee.esite.model.Sitegroup;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.weixin.dto.PublishDto;
import com.huiyee.weixin.mgr.IWxPageShowMgr;
import com.huiyee.weixin.model.WxMp;
import com.huiyee.weixin.model.WxPageShow;
import com.opensymphony.xwork2.ActionContext;

public class PublishAction extends AbstractAction {

	private static final long serialVersionUID = 2340967161567499879L;
	private static String suffix = "JPG,JPEG,BMP,PNG";

	private long siteid;
	private File img;
	private String imgFileName;
	private String imgContentType;

	private PublishDto pdto;
	private long mpid;
	private long pageid;
	private String infoed;
	private String pic;
	private String title;
	private String desc;
	private long spageid;

	private long shareid;
	private int updateseconds;

	private IPageManager pageManager;
	private IWxPageShowMgr wxPageShowMgr;
	private ISiteManager siteManager;
	private List<Long> pids;
	private IWeiXinMgr weiXinMgr;

	private int bindType;// Ĭ��ΪN Y����΢�ź���ת����
	private long xcid;// Ϊ��΢�ֳ���ֵ

	public long getSpageid() {
		return spageid;
	}

	public void setSpageid(long spageid) {
		this.spageid = spageid;
	}

	public void setSiteManager(ISiteManager siteManager) {
		this.siteManager = siteManager;
	}

	public String execute() throws Exception {
		pdto = new PublishDto();
		WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (mp != null) {
			pdto.setWxMp(mp);
			Site site = siteManager.findSiteByID(siteid);
			if (site != null) {
				pdto.setPageList(pageManager.findQQPublishPageBySiteid(siteid));// ��������ҳ��
				Sitegroup sg = siteManager.findSitegroupByid(site.getSitegroupid());
				if (IPageConstants.SITEGROUP_TYPE_EVENT.equals(sg.getStype())) {
					// ΢� ��sitegroupid�ѿ���
					pdto.setWxPageShowlist(wxPageShowMgr.findWxPageListByGroupid(site.getSitegroupid()));// ���п���
					return "event";
				} else if (IPageConstants.SITEGROUP_TYPE_WEBSITE.equals(sg.getStype())) {
					// ΢��վ ��sitegroupid�ѿ���
					pdto.setWxPageShowlist(wxPageShowMgr.findWxPageListByGroupid(site.getSitegroupid()));// ���п���
					return "website";
				} else if (IPageConstants.SITEGROUP_TYPE_XIANCHANG.equals(sg.getStype())) {
					// ΢�ֳ� ��siteid�ѿ���
					pdto.setWxPageShowlist(wxPageShowMgr.findWxPageListBySiteid(siteid));// ���п���
					return "xianchang";
				} else {
					// Ӧ��վ�㰴sitegroupid�ѿ���
					pdto.setWxPageShowlist(wxPageShowMgr.findWxPageListByGroupid(site.getSitegroupid()));// ���п���
					return "website";
				}
			}
			return SUCCESS;
		} else {
			return "to_auth";
		}
	}

	public String picUpload() throws Exception {
		String result = "";

		String fileSuffix = FileUploadService.getFileSuffix(imgFileName);
		if (img.length() > (5 * 1024 * 1024)) {
			result = "The file size is larger than 5M!";
		} else if (!suffix.contains(fileSuffix.toUpperCase())) {
			result = "The file format is not supported!";
		} else {
			Account account = (Account) ActionContext.getContext().getSession().get("account");
			String filepath = FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, account.getOwner().getId(), IPageConstants.CONTENT_PICTURE_FILEFATH);
			if (img != null) {
				String filename = FileUploadService.createFileName(imgFileName);
				String newImg = FileUploadService.saveFile(img, filepath, filename);

				result = HyConfig.getImgDomain() + newImg;
			}
		}

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}

	public String savePublishPage() {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		Site site = siteManager.findSiteByID(siteid);
		WxPageShow wps = new WxPageShow();
		if (site != null) {
			wps.setSitegroupid(site.getSitegroupid());
		}
		wps.setOwnerid(account.getOwner().getId());
		wps.setPageid(pageid);
		wps.setInfoed(infoed);
		wps.setPic(pic);
		wps.setTitle(title);
		wps.setDescription(desc);
		int toSecond = updateseconds * 24 * 3600;
		wps.setUpdateseconds(toSecond);
		wps = wxPageShowMgr.saveWxPageShow(wps, pids);

		PageAddress pa = new PageAddress();
		pa.setPageid(pageid);
		pa.setName("΢��");
		pa.setSource("wxn");
		pa.setWeixin("N");
		pa.setAddress(HyConfig.getPageyuming(account.getOwner().getId()) + "/" + account.getOwner().getEntity() + "/user/wxshowpage/" + wps.getId() + "/wxn.html");
		pageManager.savePageAddress(pa);

		// ������ҳ��
		Set<Long> pageidSet = new HashSet<Long>();
		pageidSet.add(pageid);
		publishSubPage(pageidSet, wps);

		return null;
	}

	private void publishSubPage(Set<Long> pageidSet, WxPageShow wps) {
		List<Long> pageids = pageManager.findPageidByFapageid(wps.getPageid());
		for (Long pageid : pageids) {
			if (!pageidSet.contains(pageid)) {
				WxPageShow subWps = new WxPageShow();
				subWps.setSitegroupid(wps.getSitegroupid());
				subWps.setOwnerid(wps.getOwnerid());
				subWps.setPageid(pageid);
				subWps.setInfoed(infoed);
				subWps.setPic(pic);
				subWps.setTitle(title);
				subWps.setDescription(desc);
				int toSecond = updateseconds * 24 * 3600;
				subWps.setUpdateseconds(toSecond);
				subWps = wxPageShowMgr.saveWxPageShow(subWps, pids);

				PageAddress pa = new PageAddress();
				pa.setPageid(pageid);
				pa.setName("΢��");
				pa.setSource("wxn");
				pa.setWeixin("N");
				pa.setAddress(HyConfig.getPageyuming(this.getOwner()) + this.getOnameUrl() + "/user/wxshowpage/" + subWps.getId() + "/wxn.html");
				pageManager.savePageAddress(pa);

				pageidSet.add(pageid);
				publishSubPage(pageidSet, subWps);
			}
		}
	}

	public String addWxPageShow() throws Exception {
		pdto = new PublishDto();
		WxMp mp = weiXinMgr.updateFindWxMp(this.getOwner(), 0);
		if (mp != null) {
			List<Page> plist = pageManager.findQQPublishPageBySiteid(siteid);
			pdto.setPageList(plist);
		}
		return SUCCESS;
	}

	// �����
	public String editWxPageShow() throws Exception {
		pdto = new PublishDto();
		if (shareid > 0) {
			WxPageShow wxPageShow = wxPageShowMgr.findWxPageShowById(shareid);// ������Ϣ
			pdto.setWxPageShow(wxPageShow);
		}
		List<Page> plist = pageManager.findQQPublishPageBySiteid(siteid);// ��������ҳ��
		pdto.setPageList(plist);
		return SUCCESS;
	}

	// ��վ����
	public String editWxPageShowWb() throws Exception {
		pdto = new PublishDto();
		// ������Ϣ
		if (shareid > 0) {
			WxPageShow wxPageShow = wxPageShowMgr.findWxPageShowById(shareid);
			pdto.setWxPageShow(wxPageShow);
			pdto.setPpList(wxPageShowMgr.findWxPPById(shareid));
		}
		// ��������ҳ��
		List<Page> plist = pageManager.findQQPublishPageBySiteid(siteid);
		pdto.setPageList(plist);
		return SUCCESS;
	}

	// �����������
	public String updateWxPageShow() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		Site site = siteManager.findSiteByID(siteid);
		WxPageShow wps = new WxPageShow();
		if (site != null) {
			wps.setSitegroupid(site.getSitegroupid());
		}
		wps.setOwnerid(account.getOwner().getId());
		wps.setPageid(pageid);
		wps.setInfoed(infoed);
		wps.setPic(pic);
		wps.setTitle(title);
		wps.setDescription(desc);
		wps.setId(shareid);
		wps.setUpdateseconds(updateseconds * 24 * 3600);
		wps.setSpageid(spageid);
		wps.setMpid(mpid);
		int result = wxPageShowMgr.updateWxPageShow(wps, pids, siteid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}

	public String deleteWxPageShow() throws Exception {
		Account account = (Account) ActionContext.getContext().getSession().get("account");
		int result = 0;
		if (shareid > 0) {
			Site site = siteManager.findSiteByID(siteid);
			if (site != null) {
				Sitegroup sg = siteManager.findSitegroupByid(site.getSitegroupid());
				result = wxPageShowMgr.deleteWxPageShow(shareid, sg.getStype(), account.getOwner().getId());
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(new Gson().toJson(result));
		pw.flush();
		pw.close();
		return null;
	}

	@SuppressWarnings("unused")
	private long getExpireTime() {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_YEAR, -30);

		return ca.getTimeInMillis();
	}

	public void setPageManager(IPageManager pageManager) {
		this.pageManager = pageManager;
	}

	public void setWxPageShowMgr(IWxPageShowMgr wxPageShowMgr) {
		this.wxPageShowMgr = wxPageShowMgr;
	}

	public long getSiteid() {
		return siteid;
	}

	public void setSiteid(long siteid) {
		this.siteid = siteid;
	}

	public PublishDto getPdto() {
		return pdto;
	}

	public void setPdto(PublishDto pdto) {
		this.pdto = pdto;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgContentType() {
		return imgContentType;
	}

	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}

	public long getMpid() {
		return mpid;
	}

	public void setMpid(long mpid) {
		this.mpid = mpid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}

	public String getInfoed() {
		return infoed;
	}

	public void setInfoed(String infoed) {
		this.infoed = infoed;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getShareid() {
		return shareid;
	}

	public void setShareid(long shareid) {
		this.shareid = shareid;
	}

	public int getUpdateseconds() {
		return updateseconds;
	}

	public void setUpdateseconds(int updateseconds) {
		this.updateseconds = updateseconds;
	}

	public List<Long> getPids() {
		return pids;
	}

	public void setPids(List<Long> pids) {
		this.pids = pids;
	}

	public IWeiXinMgr getWeiXinMgr() {
		return weiXinMgr;
	}

	public void setWeiXinMgr(IWeiXinMgr weiXinMgr) {
		this.weiXinMgr = weiXinMgr;
	}

	public int getBindType() {
		return bindType;
	}

	public void setBindType(int bindType) {
		this.bindType = bindType;
	}

	public int getLightType() {
		return 2;
	}

	public long getXcid() {
		return xcid;
	}

	public void setXcid(long xcid) {
		this.xcid = xcid;
	}
}
