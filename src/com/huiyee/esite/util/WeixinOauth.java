package com.huiyee.esite.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONObject;

import com.huiyee.esite.model.WxUser;
import com.huiyee.weixin.model.WxMp;

public class WeixinOauth {

	public static WxUser wxOauth(WxMp wm, String code) {
		if (code == null || "authdeny".equals(code) || !wm.isWork()) {
			System.out.println("iswork:"+wm.isWork()+"wm����ʱ��:"+new Date(wm.getThird_expires_in()));
			return null;
		}
		try {
			String appid = wm.getAppid();
			String appsecret = wm.getAppsecret();

			// Ĭ�ϻ�ȡ�ӿ��û��ķ���
			if (StringUtils.isNotBlank(appsecret)) {
				String access_token = wm.getAccess_token();
				if (access_token != null) {
					return getUserInfoByCode(appid, appsecret, code, access_token);
				}
			} else {
				String component_appid = wm.getThird_appid();
				String authorizer_access_token = wm.getAu_access_token();
				String component_access_token = wm.getThird_access_token();
				if (component_appid != null && authorizer_access_token != null && component_access_token != null) {
					return getUserInfoByCode(appid, code, component_appid, authorizer_access_token, component_access_token);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static WxUser getUserInfoByCode(String appid, String appsecret, String code, String mp_access_token) throws Exception {
		WxUser wxuser = new WxUser();
		// ͨ��code��ȡ����ҳ��Ȩaccess_token
		String access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";

		JSONObject obj = httpsRequest(access_token_url, "GET");
		if (obj.containsKey("errcode")) {
			System.out.println("�Լ���ȡ�û�ʧ��"+obj.toString());
			return null;
		}
		String access_token = obj.getString("access_token");
		String openid = obj.getString("openid");
		String scope = obj.getString("scope");

		// ��ȡ�Ƿ��ע��Ϣ
		JSONObject subObj = getSubscriberInfo(mp_access_token, openid);
		int subscribe = 0;
		if (subObj.containsKey("subscribe")) {
			subscribe = subObj.getInt("subscribe");
		}
		if (subscribe > 0) {
			wxuser = new WxUser(subObj);
		}
		// ��ȡ��Ȩ�û���Ϣ
		else if ("snsapi_userinfo".equals(scope)) {
			JSONObject userinfo = getUserInfo(access_token, openid);
			wxuser = new WxUser(userinfo);
		}
		wxuser.setOpenid(openid);
		wxuser.setSubscribe(subscribe);

		return wxuser;
	}

	private static WxUser getUserInfoByCode(String appid, String code, String component_appid, String authorizer_access_token, String component_access_token) throws Exception {
		WxUser wxuser = new WxUser();
		// ͨ��code��ȡ����ҳ��Ȩaccess_token
		String access_token_url = "https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=" + appid + "&code=" + code + "&grant_type=authorization_code&component_appid=" + component_appid
				+ "&component_access_token=" + component_access_token;

		JSONObject obj = httpsRequest(access_token_url, "GET");
		if (obj.containsKey("errcode")) {
			System.out.println(component_access_token+"��ȡ�û�ʧ��"+obj.toString());
			return null;
		}
		String access_token = obj.getString("access_token");
		String openid = obj.getString("openid");
		String scope = obj.getString("scope");

		// ��ȡ�Ƿ��ע��Ϣ
		JSONObject subObj = getSubscriberInfo(authorizer_access_token, openid);
		int subscribe = 0;
		if (subObj.containsKey("subscribe")) {
			subscribe = subObj.getInt("subscribe");
		}
		if (subscribe > 0) {
			wxuser = new WxUser(subObj);
		}
		// ��ȡ��Ȩ�û���Ϣ
		else if ("snsapi_userinfo".equals(scope)) {
			JSONObject userinfo = getUserInfo(access_token, openid);
			wxuser = new WxUser(userinfo);
		}
		wxuser.setOpenid(openid);
		wxuser.setSubscribe(subscribe);

		return wxuser;
	}

	private static JSONObject getSubscriberInfo(String acccess_token, String openid) throws Exception {
		String subscriber_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + acccess_token + "&openid=" + openid + "&lang=zh_CN";
		JSONObject obj = httpsRequest(subscriber_url, "GET");
		if (obj.containsKey("errcode")) {
			// System.out.println("error: getSubscriberInfo-" + obj.toString());
		}
		return obj;
	}

	private static JSONObject getUserInfo(String access_token, String openid) throws Exception {
		// ��ȡ�û���Ϣ(��scopeΪ snsapi_userinfo)
		String user_url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
		JSONObject obj = httpsRequest(user_url, "GET");
		if (obj.containsKey("errcode")) {
			// System.out.println("error: getUserInfo-" + obj.toString());
		}
		return obj;
	}

	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) throws Exception {

		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// ������SSLContext�����еõ�SSLSocketFactory����
		SSLSocketFactory ssf = sslContext.getSocketFactory();

		URL url = new URL(requestUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
		httpUrlConn.setSSLSocketFactory(ssf);

		httpUrlConn.setDoOutput(true);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setUseCaches(false);
		// ��������ʽ��GET/POST��
		httpUrlConn.setRequestMethod(requestMethod);

		if ("GET".equalsIgnoreCase(requestMethod)) {
			httpUrlConn.connect();
		}

		// ����������Ҫ�ύʱ
		if (null != outputStr) {
			OutputStream outputStream = httpUrlConn.getOutputStream();
			// ע������ʽ����ֹ��������
			outputStream.write(outputStr.getBytes("UTF-8"));
			outputStream.close();
		}

		// �����ص�������ת�����ַ���
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
		bufferedReader.close();
		inputStreamReader.close();
		// �ͷ���Դ
		inputStream.close();
		inputStream = null;
		httpUrlConn.disconnect();
		jsonObject = JSONObject.fromObject(buffer.toString());
		return jsonObject;
	}

	public static JSONObject httpsRequest(String requestUrl, String requestMethod) throws Exception {
		return httpsRequest(requestUrl, requestMethod, null);
	}

}

class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
