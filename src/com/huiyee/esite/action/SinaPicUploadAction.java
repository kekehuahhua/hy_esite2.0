package com.huiyee.esite.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import sun.misc.BASE64Decoder;

import com.huiyee.esite.compose.IFeatureCompose;
import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.model.ContentPicture;
import com.huiyee.esite.service.FileUploadService;
import com.huiyee.esite.util.HyConfig;

public class SinaPicUploadAction extends AbstractAction {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3129236258833270292L;
	private IFeatureCompose featureCompose;
	private long featureid;
	private long fid;
	private long pageid;
	private String feature;
	
	private String pic;
	private String pic1;
	private String pic2;
	private String pic3;
	private String petname;
	
	private File img;
	private String imgFileName;
	private String imgContentType;
	
	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}
	
	public void setFeatureCompose(IFeatureCompose featureCompose) {
		this.featureCompose = featureCompose;
	}

	public long getFeatureid() {
		return featureid;
	}

	public void setFeatureid(long featureid) {
		this.featureid = featureid;
	}

	public long getPageid() {
		return pageid;
	}

	public void setPageid(long pageid) {
		this.pageid = pageid;
	}
	
	@Override
	public String execute() throws Exception{
		System.out.println("��ʼ������!!!");
		if(feature!= null){
			String[] params = feature.split("-");
			featureid = Long.parseLong(params[0]);
			pageid = Long.parseLong(params[1]);
//			fid = Long.parseLong(params[2]);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		long ownerid = pageCompose.findOwnerByPageid(pageid);//ownerid

		String path = HyConfig.getRootPath()+FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"")+"/";//·��
		System.out.println("pathΪ"+path);
		File d = new File(path);
		if(!d.exists()){
			d.mkdirs();
		}

		long savePicName = new Date().getTime();
		String name1=FileUploadService.createFileName(savePicName+"_b.jpg");//������ͼ����
		String path1=FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, ownerid, IPageConstants.CONTENT_PICTURE_FILEFATH+"")+"/"+name1;
		String name2=FileUploadService.createFileName(savePicName+"_m.jpg");//��ͼ����
		String path2=FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, ownerid, IPageConstants.CONTENT_PICTURE_FILEFATH+"")+"/"+name2;
		String name3=FileUploadService.createFileName(savePicName+"_s.jpg");//Сͼ����
		String path3=FileUploadService.getFilePath(IPageConstants.TYPE_CONTENT, ownerid, IPageConstants.CONTENT_PICTURE_FILEFATH+"")+"/"+name3;
		
		String file_src = path + savePicName + "_src.jpg"; // ����ԭͼ
		String filename162 = path + savePicName + "_b.jpg"; // ����162
		String filename48 = path + savePicName + "_m.jpg"; // ����48
		String filename20 = path + savePicName + "_s.jpg"; // ����20
		
		ContentPicture picture = new ContentPicture();

		if(pic!=null&&!pic.equals("")){
			//ԭͼ
			File file = new File(file_src);
			FileOutputStream fout = null;
			fout = new FileOutputStream(file);
			fout.write(new BASE64Decoder().decodeBuffer(pic));
			fout.close();
		}
        System.out.println("ԭͼ����ɹ�");
		if(pic1!=null && !pic1.equals("")){
			//ͼ1
			File file1 = new File(filename162);
			FileOutputStream fout1 = null;
			fout1 = new FileOutputStream(file1);
			fout1.write(new BASE64Decoder().decodeBuffer(pic1));
			fout1.close();
//			picture.setCatid(2);
//			picture.setTitle(name1);
//			picture.setImgurl(path1);
//			picture.setOwnerid(ownerid);
//			pageCompose.savePicture(picture);
		}
        System.out.println("ͼ2�ϴ��ɹ�");
		if(pic2!=null && !pic2.equals("")){
			//ͼ2
			File file2 = new File(filename48);
			FileOutputStream fout2 = null;
			fout2 = new FileOutputStream(file2);
			fout2.write(new BASE64Decoder().decodeBuffer(pic2));
			fout2.close();
//			picture.setCatid(2);
//			picture.setTitle(name2);
//			picture.setImgurl(path2);
//			picture.setOwnerid(ownerid);
//			pageCompose.savePicture(picture);
		}

		if(pic3!=null && !pic3.equals("")){
			//ͼ3
			File file3 = new File(filename20);
			FileOutputStream fout3 = null;
			fout3 = new FileOutputStream(file3);
			fout3.write(new BASE64Decoder().decodeBuffer(pic3));
			fout3.close();
//			picture.setCatid(2);
//			picture.setTitle(name3);
//			picture.setImgurl(path3);
//			picture.setOwnerid(ownerid);
//			pageCompose.savePicture(picture);
		}
		
		String picUrl = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"")+"/"+savePicName;
		out.print("{\"status\":1,\"picUrl\":\""+picUrl+"\"}");
		out.flush();
		out.close();
		return null;
	}
	
	public String picSubByPhone() throws Exception{
		String fileSuffix = FileUploadService.getFileSuffix(imgFileName);
		if(img.length() > (5*1024*1024)){
			System.out.println("ͼƬ̫��");
			return null;
		}
		if(suffixMap.get(fileSuffix.toUpperCase()) == null){
			System.out.println("��֧�ָ�ʽ");
			return null;
		}
		long ownerid = pageCompose.findOwnerByPageid(pageid);//ownerid
		String path = FileUploadService.getFilePath(IPageConstants.TYPE_FEATURE, ownerid, featureid+"")+"/";//·��
		long savePicName = new Date().getTime();
		String saveResult = FileUploadService.saveFile(img,path,savePicName+"_src.jpg");
		System.out.println("��ͼ��"+saveResult);
//		saveResult = FileUploadService.saveFile(img,path,savePicName+"_m.jpg");
//		System.out.println("��ͼ��"+saveResult);
//		saveResult = FileUploadService.saveFile(img,path,savePicName+"_s.jpg");
//		System.out.println("Сͼ��"+saveResult);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("html/text;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.print(path+savePicName);
		System.out.println("�ֻ����ϴ�ͼƬ����ֵ:"+path+savePicName);
		out.flush();
		out.close();
		return null;
	}
	
	private Map<String,String> suffixMap = new HashMap<String, String>();
	
	public void init() throws Exception{
		suffixMap.put("JPG", "");
		suffixMap.put("JPEG", "");
		suffixMap.put("BMP", "");
		suffixMap.put("PNG", "");
	}
	
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	public String getPetname() {
		return petname;
	}

	public void setPetname(String petname) {
		this.petname = petname;
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

}
