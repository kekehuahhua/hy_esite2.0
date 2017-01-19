package com.huiyee.pay.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.pay.config.AlipayConfig4Phone;
import com.huiyee.pay.mgr.IOwnerPayMgr;
import com.huiyee.pay.model.AliPay;
import com.huiyee.pay.util.AlipaySubmit4phone;
import com.huiyee.pay.util.UtilDate;
import com.opensymphony.xwork2.ActionSupport;

public class AliPay4PhoneAction extends InteractModelAction
{

	private static final long serialVersionUID = 3887688790629684079L;
	//֧�������ص�ַ
	private String ALIPAY_GATEWAY_NEW = "http://wappaygw.alipay.com/service/rest.htm?";

	////////////////////////////////////������Ȩ�ӿ�alipay.wap.trade.create.direct��ȡ��Ȩ��token//////////////////////////////////////
	
	//���ظ�ʽ
	private String format = "xml";
	//�������Ҫ�޸�
	
	//���ظ�ʽ
	private String v = "2.0";
	//�������Ҫ�޸�
	
	//�����
	private String req_id = UtilDate.getOrderNum();
	//����뱣֤ÿ��������Ψһ
	
	//req_data��ϸ��Ϣ
	
	//�������첽֪ͨҳ��·��
	private String notify_url =  HyConfig.getPageyuming()+"/hy/pay/alps4p.action";
	//��http://��ʽ������·�������ܼ�?id=123�����Զ������

	//ҳ����תͬ��֪ͨҳ��·��
	private String call_back_url = HyConfig.getPageyuming()+"/hy/pay/alcb4p";
	//��http://��ʽ������·�������ܼ�?id=123�����Զ������������д��http://localhost/

	//�����жϷ��ص�ַ
	private String merchant_url = HyConfig.getPageyuming()+"/hy/pay/almc4p.action";
	//�û�������;�˳������̻��ĵ�ַ����http://��ʽ������·�����������?id=123�����Զ������

	//�̻�������
	private String out_trade_no ;
	//�̻���վ����ϵͳ��Ψһ�����ţ�����

	//��������
	private String subject  ;
	//����

	//������
	private String total_fee ;
	//����
	
	private String ff;
	private IOwnerPayMgr ownerPayMgr; 
	
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	
	public String asub() throws Exception
	{
		notify_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/alps4p.action";
		call_back_url = HyConfig.getPageyuming()+"/" + this.getOname() + "/pay/alcb4p";
		merchant_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/almc4p.action";
		AliPay aliPay = ownerPayMgr.findAliPay("Z");
		if(aliPay != null){
			HyConfig.setAlipartner(aliPay.getAlipartner());
			HyConfig.setAliseller_email(aliPay.getAliseller_email());
			HyConfig.setAlikey(aliPay.getAlikey());
		}
		String orderNo = String.valueOf(new Date().getTime());
		String req_dataToken = "<direct_trade_create_req><notify_url>" + notify_url + "</notify_url><call_back_url>" + call_back_url +out_trade_no+ ".action</call_back_url><seller_account_name>" + HyConfig.aliseller_email + "</seller_account_name><out_trade_no>" + orderNo + "</out_trade_no><subject>" + subject + "</subject><total_fee>" + total_fee + "</total_fee><merchant_url>" + merchant_url + "</merchant_url></direct_trade_create_req>";
	
		//������������������
		Map<String, String> sParaTempToken = new HashMap<String, String>();
		sParaTempToken.put("service", "alipay.wap.trade.create.direct");
		sParaTempToken.put("partner",HyConfig.alipartner);
		sParaTempToken.put("_input_charset", AlipayConfig4Phone.input_charset);
		sParaTempToken.put("sec_id", AlipayConfig4Phone.sign_type);
		sParaTempToken.put("format", format);
		sParaTempToken.put("v", v);
		sParaTempToken.put("req_id", req_id);
		sParaTempToken.put("req_data", req_dataToken);
		
		//��������
		String sHtmlTextToken = AlipaySubmit4phone.buildRequest(ALIPAY_GATEWAY_NEW,"", "",sParaTempToken);
		//URLDECODE���ص���Ϣ
		sHtmlTextToken = URLDecoder.decode(sHtmlTextToken,AlipayConfig4Phone.input_charset);
		//��ȡtoken
		String request_token = AlipaySubmit4phone.getRequestToken(sHtmlTextToken);
		
		
		////////////////////////////////////������Ȩ��token���ý��׽ӿ�alipay.wap.auth.authAndExecute//////////////////////////////////////
		
		//ҵ����ϸ
		String req_data = "<auth_and_execute_req><request_token>" + request_token + "</request_token></auth_and_execute_req>";
		//����
		
		//������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.wap.auth.authAndExecute");
		sParaTemp.put("partner", HyConfig.alipartner);
		sParaTemp.put("_input_charset", AlipayConfig4Phone.input_charset);
		sParaTemp.put("sec_id", AlipayConfig4Phone.sign_type);
		sParaTemp.put("format", format);
		sParaTemp.put("v", v);
		sParaTemp.put("req_data", req_data);
		
		//��������
		ff = AlipaySubmit4phone.buildRequest(ALIPAY_GATEWAY_NEW, sParaTemp, "get", "ȷ��");
		return ActionSupport.SUCCESS;
	}

	public String getFf()
	{
		return ff;
	}

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setTotal_fee(String total_fee)
	{
		this.total_fee = total_fee;
	}

	
	public void setOwnerPayMgr(IOwnerPayMgr ownerPayMgr)
	{
		this.ownerPayMgr = ownerPayMgr;
	}

	
}
