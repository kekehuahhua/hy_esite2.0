package com.huiyee.interact.bbs.action;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SMSSender {
	private static Logger logger = LoggerFactory.getLogger(SMSSender.class);
	
	
	
	//���ʹ��ݵĲ���
	//http://www.ztsms.cn:8800/sendXSms.do?username=�û���&password=����&mobile=�ֻ�����&content=����&dstime=&productid=��ƷID&xh=����
	private static String username;
	private static String password;
	private static String mobile;
	private static String content;
	private static String dstime;
	private static Integer productid;
	private static String xh;
	private static String sendurl;

	/**   
	 * ����һ���µ�ʵ�� SMSSender.   
	 *  ��ͨ�Ƽ��ṩ���û������ַ
	 */
	/****
	 * username	�û��������
	 * 	password	���루���
	 * 	mobile	�ֻ��ţ�����ֻ���Ϊ�ð�� , �ֿ�����13899999999,13688888888(���100��������)
	 * 	content	�������ݣ����
	 * 	dstime	��ʱʱ�䣬Ϊ��ʱ��ʾ�������ͣ�ѡ�
	 * 	productid	��Ʒid(����)
	 * 	xh	��չ��,����
	 * ***/
	
		
	public SMSSender() {
		username = "huiyee"; //�����û���
		password = "lzZCGpn8"; //����
		sendurl = "http://www.ztsms.cn:8800/"; //��ͨ�ṩ�ķ��͵�ַ
		productid = 676767; //��ƷID��
		xh = ""; //��չ������
	}
	
	public SMSSender(String name, String pwd,int id) {
		username = name; //�����û���
		password = pwd; //����
		sendurl = "http://www.ztsms.cn:8800/"; //��ͨ�ṩ�ķ��͵�ַ
		productid = id; //��ƷID��
		xh = ""; //��չ������
	}
	

	public String sendSms(String mobile, String content) {
		String sendUrl = null;
		try {// ���򷢵��ֻ�����
			sendUrl = sendurl + "sendXSms.do?username=" + username + "&password="+ password 
			+ "&mobile=" + mobile + "&content="+URLEncoder.encode(content, "UTF-8")
			+ "&productid=" + productid + "&xh="+xh;
		} catch (UnsupportedEncodingException uee) {
			logger.error(uee.toString());
		}

		logger.info("��������Ϊ------------->:" + content);
		return getUrl(sendUrl);
	}

	
	
	/** 
	 * @Title: getUrl 
	 * @Description: ��ȡ��ַ
	 * @param urlString
	 * @return    
	 */ 
		
	public String getUrl(String urlString) {
		StringBuffer sb = new StringBuffer();
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			conn.setReadTimeout(15000);
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			for (String line = null; (line = reader.readLine()) != null;) {
				sb.append(line + "\n");
			}
			reader.close();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		String result = "";
		try {
			result = URLDecoder.decode(sb.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	/***
	 * ���Ե�ַ
	 * 
	 * ***/
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//		String message = "���ã�Ϊ���������õĽ��ն��ţ���ͨ�������ķ��ʹ˶�����Ϊ����"+dateFormat.format(new Date());
		String message = "�𾴵��û������ע����֤����888888������գ������׿Ƽ���";
//		String phone = "13088888888,15288888888,13888888888,13788888888,15088888888";
		String phone = "15301536882";
		String r = new SMSSender().sendSms(phone, message);
		System.out.println(r);

		
	}

}
