package com.huiyee.interact.template.service;

import com.huiyee.weixin.model.message.ErrMsg;

import net.sf.json.JSONObject;

public interface IWeixinTemplateService {

	// ����������ҵ
	public ErrMsg setIndustry(String access_token, long industry_id1, long industry_id2);

	// ��ȡ���õ���ҵ��Ϣ
	public JSONObject getIndustry(String access_token);

	// ���ģ��ID
	public ErrMsg addTemplate(String access_token, String template_id_short);

	// ��ȡģ���б�
	public JSONObject getTemplate(String access_token);

	// ɾ��ģ��
	public ErrMsg delTemplate(String access_token, String template_id);

	// ����ģ����Ϣ
	public ErrMsg send(String access_token, String json);

}
