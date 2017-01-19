package com.huiyee.pay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.huiyee.pay.config.AlipayConfig4Phone;
import com.huiyee.pay.util.AlipayNotify4phone;
import com.opensymphony.xwork2.ActionSupport;

public class AliPush4PhoneAction extends ActionSupport
{

	private static final long serialVersionUID = 3887688790629684079L;
	public String execute() throws Exception
	{
		return ActionSupport.SUCCESS;
	}
	
	public String asub() throws Exception
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		
		//��ȡ֧����POST����������Ϣ
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}

		//RSAǩ������
	   	if(AlipayConfig4Phone.sign_type.equals("0001")) {
	   		params = AlipayNotify4phone.decrypt(params);
	   	}
		//XML����notify_data����
		Document doc_notify_data = DocumentHelper.parseText(params.get("notify_data"));
		
		//�̻�������
		String out_trade_no = doc_notify_data.selectSingleNode( "//notify/out_trade_no" ).getText();

		//֧�������׺�
		String trade_no = doc_notify_data.selectSingleNode( "//notify/trade_no" ).getText();

		//����״̬
		String trade_status = doc_notify_data.selectSingleNode( "//notify/trade_status" ).getText();


		if(AlipayNotify4phone.verifyNotify(params)){//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������

			//�������������ҵ���߼�����д�������´�������ο�������
			
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
					//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
					//���������������ִ���̻���ҵ�����
				System.out.println("ִ����pushAction");
			}
			

			//�������������ҵ���߼�����д�������ϴ�������ο�������
				
			out.println("success");	//�벻Ҫ�޸Ļ�ɾ��

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			out.println("fail");
		}
		
		out.print("");
		out.flush();
		out.close();
		return null;
	}

}
