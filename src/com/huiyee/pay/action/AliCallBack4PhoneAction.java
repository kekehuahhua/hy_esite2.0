package com.huiyee.pay.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.pay.util.AlipayNotify4phone;
import com.opensymphony.xwork2.ActionSupport;

public class AliCallBack4PhoneAction extends ActionSupport
{

	private static final long serialVersionUID = 3887688790629684079L;
    private String out_trade_no;//�̻�������
    private String trade_no;//֧�������׺�
    private String result;//����״̬
	private long ownerid;
	private String username;
	private String token;
    private String orderNo;
	
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

		//��ȡ֧����GET����������Ϣ
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
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//����ó�֪ͨ��֤���
		boolean verify_result = AlipayNotify4phone.verifyReturn(params);
		if(verify_result){//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������
				
			
			return SUCCESS;
			//��ҳ�����ҳ�������༭
//			out.println("��֤�ɹ�<br />");
			//�������������ҵ���߼�����д�������ϴ�������ο�������

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{
			//��ҳ�����ҳ�������༭
//			out.println("��֤ʧ��");
			return "fail";
		}
		/*
		out.print("");
		out.flush();
		out.close();
		return null;*/
	}
	

	public void setOut_trade_no(String out_trade_no)
	{
		this.out_trade_no = out_trade_no;
	}

	public void setTrade_no(String trade_no)
	{
		this.trade_no = trade_no;
	}
	public void setResult(String result)
	{
		this.result = result;
	}

	public long getOwnerid()
	{
		return ownerid;
	}

	public void setOwnerid(long ownerid)
	{
		this.ownerid = ownerid;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

}
