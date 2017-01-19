package com.huiyee.esite.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.huiyee.esite.util.BASE64Encoder;

public class Oauth {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7003420545330439247L;
	// ----------------------------���վ��Ӧ�ô���SignedRequest��ȡaccesstoken----------------------------------------
	public String access_token;
	public String user_id;
	public String expires;
	public String ouid;
	public String user;

	public String getToken() {
		return access_token;
	}
	
	public static void main(String[] args) {
		String str = "CY4I2g3N6fiVi2rTS1KgstJ-mLdtxjQGXTds7sJ1Gz0.eyJ1c2VyIjp7ImNvdW50cnkiOiJjbiIsImxvY2FsZSI6IiIsInZlcnNpb24iOjV9LCJhbGdvcml0aG0iOiJITUFDLVNIQTI1NiIsImlzc3VlZF9hdCI6MTQxMDUxMjk2NSwiZXhwaXJlcyI6NjQwMjM1LCJvYXV0aF90b2tlbiI6IjIuMDBiWFN0X0MxenUyc0M1YTZmMThjN2NkODFUNlZFIiwidXNlcl9pZCI6MjEyNjE5NTM2MywicmVmZXJlciI6Imh0dHA6XC9cL3dlaWJvLmNvbVwvbWpuY2hpbmEiLCJzY29wZSI6IiIsImV4dF9kYXRhIjoiIiwib3VpZCI6IjI0MTYwODAxNTciLCJvcmlnaW4iOiJwcmV2aWV3In0";
		try {
			Oauth o = new Oauth();
			o.parseSignedRequest(str, "f1b93c9da2770b2d1207929e56372ac8");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * ����վ��Ӧ��post��SignedRequest splitΪpart1��part2������
	 */
	public String parseSignedRequest(String signed_request,String secrect) throws IOException,
			InvalidKeyException, NoSuchAlgorithmException {
		Date d1 = new Date();
		String[] t = signed_request.split("\\.", 2);
		// Ϊ�˺� url encode/decode ����ͻ��base64url ���뷽ʽ�Ὣ
		// '+'��'/'ת����'-'��'_'������ȥ����β��'='�� ��˽���֮ǰ��Ҫ��ԭ��Ĭ�ϵ�base64���룬��β��'='�����������㷨��ԭ
		int padding = (4 - t[0].length() % 4);
		for (int i = 0; i < padding; i++)
			t[0] += "=";
		String part1 = t[0].replace("-", "+").replace("_", "/");

		SecretKey key = new SecretKeySpec(secrect.getBytes(), "hmacSHA256");
		Mac m;
		m = Mac.getInstance("hmacSHA256");
		m.init(key);
		m.update(t[1].getBytes());
		String part1Expect = BASE64Encoder.encode(m.doFinal());

		sun.misc.BASE64Decoder decode = new sun.misc.BASE64Decoder();
		String s = new String(decode.decodeBuffer(t[1]));
		Date d2 = new Date();
		System.out.println(d2.getTime()-d1.getTime());
		if (part1.equals(part1Expect)) {
			return ts(s);
		} else {
			return null;
		}
	}

	/*
	 * ����������json����
	 */
	public String ts(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			access_token = jsonObject.getString("oauth_token");
			user_id = jsonObject.getString("user_id");
			expires = jsonObject.getString("expires");
			ouid = jsonObject.getString("ouid");
			user = jsonObject.getString("user");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return access_token;

	}

}
