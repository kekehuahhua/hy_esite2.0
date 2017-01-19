package com.huiyee.pay.action;

import java.util.HashMap;
import java.util.Map;

import com.huiyee.esite.action.InteractModelAction;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.pay.config.AlipayConfig;
import com.huiyee.pay.mgr.IOwnerPayMgr;
import com.huiyee.pay.model.AliPay;
import com.huiyee.pay.util.AlipaySubmit;
import com.opensymphony.xwork2.ActionSupport;

public class AliPayAction extends InteractModelAction
{

	private static final long serialVersionUID = 3887688790629684079L;
	//֧������
	private	String payment_type = "1";
	//��������޸�
	
	//�������첽֪ͨҳ��·��
	private	String notify_url =  HyConfig.getPageyuming()+"/hy/pay/alps.action";
	//��http://��ʽ������·�������ܼ�?id=123�����Զ������

	//ҳ����תͬ��֪ͨҳ��·��
	private	String return_url = HyConfig.getPageyuming()+"/hy/pay/alcb.action";
	//��http://��ʽ������·�������ܼ�?id=123�����Զ������������д��http://localhost/

	//�̻�������
	private	String out_trade_no ;
	//�̻���վ����ϵͳ��Ψһ�����ţ�����

	//��������
	private	String subject ;
	//����

	//������
	private String total_fee ;
	//����

	//��������

	private String body ;
	//��Ʒչʾ��ַ
	private String show_url ;
	//����http://��ͷ������·�������磺http://www.�̻���ַ.com/myorder.html

	//������ʱ���
	private String anti_phishing_key = "";
	//��Ҫʹ����������ļ�submit�е�query_timestamp����

	//�ͻ��˵�IP��ַ
	private String exter_invoke_ip = "121.238.118.82";
	//�Ǿ�����������IP��ַ���磺221.0.0.1
	
	
	private String ff;
	private IOwnerPayMgr ownerPayMgr; 
	
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	
	public String asub() throws Exception
	{
		AliPay aliPay = ownerPayMgr.findAliPay("Z");
		if(aliPay != null){
			HyConfig.setAlipartner(aliPay.getAlipartner());
			HyConfig.setAliseller_email(aliPay.getAliseller_email());
			HyConfig.setAlikey(aliPay.getAlikey());
		}
		//������������������
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", HyConfig.alipartner);
        sParaTemp.put("seller_email", HyConfig.aliseller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", payment_type);
		notify_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/alps.action";
		return_url = HyConfig.getPageyuming()+"/" + this.getOname() +"/pay/alcb.action";
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("return_url", return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//��������
		ff = AlipaySubmit.buildRequest(sParaTemp,"get","ȷ��");
	
		return ActionSupport.SUCCESS;
	}

	public void setPayment_type(String payment_type)
	{
		this.payment_type = payment_type;
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

	public void setBody(String body)
	{
		this.body = body;
	}

	public void setAnti_phishing_key(String anti_phishing_key)
	{
		this.anti_phishing_key = anti_phishing_key;
	}

	public void setExter_invoke_ip(String exter_invoke_ip)
	{
		this.exter_invoke_ip = exter_invoke_ip;
	}

	public void setShow_url(String show_url)
	{
		this.show_url = show_url;
	}

	public String getFf()
	{
		return ff;
	}

	public void setReturn_url(String return_url)
	{
		this.return_url = return_url;
	}

	public void setOwnerPayMgr(IOwnerPayMgr ownerPayMgr)
	{
		this.ownerPayMgr = ownerPayMgr;
	}

	
}
