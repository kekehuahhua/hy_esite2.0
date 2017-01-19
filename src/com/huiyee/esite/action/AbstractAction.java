package com.huiyee.esite.action;

import java.util.Date;

import com.huiyee.esite.compose.IPageCompose;
import com.huiyee.esite.util.DateUtil;
import com.huiyee.esite.util.HyConfig;

public abstract class AbstractAction extends HyAbstractAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected IPageCompose pageCompose;
	/**
	 * �Ƿ���ajax����1����ajax����;0��Ĭ��ֵ������ajax����
	 */
	private int inajax;

	private int _hyrand;

	protected int lightType;
	
	private String random;
	
	private String pageDomain;
	
	public void setPageCompose(IPageCompose pageCompose) {
		this.pageCompose = pageCompose;
	}

	 public void beforeHudong()
	 {
		 if(pageCompose!=null&&this.getOwner()>0){
		 pageCompose.beforeHudong(this.getOwner());
		 }
	 }
	
	/**
	 * �Ƿ���ajax����1����ajax����;0��Ĭ��ֵ������ajax����
	 */
	public int getInajax() {
		return inajax;
	}

	public void setInajax(int inajax) {
		this.inajax = inajax;
	}

	public String getHyTitle() {
		return "΢վ�";
	}

	public String getHyDescription() {
		return "΢վ�";
	}

	public String getHyKeywords() {
		return "΢վ�";
	}

	public String getNow() {
		return DateUtil.getDateTimeString(new Date());
	}

	public long getNowSecond() {
		return new Date().getTime();
	}
	
	public int get_hyrand() {
		return _hyrand;
	}

	public void set_hyrand(int _hyrand) {
		this._hyrand = _hyrand;
	}

	public int getLightType() {
		return lightType;
	}

	public void setLightType(int lightType) {
		this.lightType = lightType;
	}
	
	public String getCloudDomain(){
		return HyConfig.getCloudDomain();
	}
	
	public String getYiboDomain(){
		return HyConfig.getYiboDomain();
	}
	
	public String getYiyouDomain(){
		return HyConfig.getYiyouDomain();
	}
	
	public String getImgDomain(){
		return HyConfig.getImgDomain();
	}
	public String getMcrmDomain(){
		return HyConfig.getMcrmDomain();
	}
	public String getCrmDomain(){
		return HyConfig.getCrmDomain();
	}
	
	public String getRootPath(){
		return HyConfig.getRootPath();
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}
	
	public String getSiteDomain(){
		return HyConfig.getYuming();
	}
	
	public Boolean getIsRun(){
		return HyConfig.isRun();
	}

	public String getPageDomain()
	{
		pageDomain = HyConfig.getPageyuming(this.getOwner());
		return pageDomain;
	}

	public void setPageDomain(String pageDomain)
	{
		this.pageDomain = HyConfig.getPageyuming(this.getOwner());
	}

}
