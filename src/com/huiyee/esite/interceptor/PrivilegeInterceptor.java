
package com.huiyee.esite.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.OwnerPrivilege;
import com.huiyee.esite.service.Permission;
import com.huiyee.esite.util.HyConfig;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1723632795257410745L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception
	{
	    if( !HyConfig.isRun() || validate(invocation)){
	    	return invocation.invoke();
	    }else{
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	HttpServletResponse response = ServletActionContext.getResponse();
	    	Account account = (Account) request.getSession().getAttribute("account");
	    	String url = "/"+account.getOwner().getEntity()+"/page/app_out_of_time.action";
	    	request.getRequestDispatcher(url).forward(request, response);
			return null;
	    }
	    
	}
	
	private boolean validate(ActionInvocation invocation) throws SecurityException, NoSuchMethodException {
		String  methodName=invocation.getProxy().getMethod();
		Method currentMethod = invocation.getAction().getClass().getMethod(methodName);
		if(currentMethod != null && currentMethod.isAnnotationPresent(Permission.class)){
			//�õ������ϵ�ע��
			Permission permission = currentMethod.getAnnotation(Permission.class);  
			//�õ���ǰ��¼���û�
			Account account = (Account)ServletActionContext.getRequest().getSession().getAttribute("account");
			//������ǰ�û��µ����е�Ȩ����  
			if(account.getOwner().getPrivileges() != null ){
				for(OwnerPrivilege op : account.getOwner().getPrivileges()){
					//�����Ȩ�����°�����Ҫ��Ȩ��
					String module = permission.module();
					if(IPrivilegeConstants.MODULE_APP_�̳�.equals(module)){
						//΢���̡������̳� ���⴦��
						String subtype = ServletActionContext.getRequest().getParameter("subtype");
						if("W".equalsIgnoreCase(subtype)){
							module = IPrivilegeConstants.MODULE_APP_΢����;
						}else if("J".equalsIgnoreCase(subtype)){
							module = IPrivilegeConstants.MODULE_APP_�����̳�;
						}
					}
					if(module.equalsIgnoreCase(op.getModule())){
						if(IPrivilegeConstants.OWNER_PERMISSION_TRY.equalsIgnoreCase(op.getStatus())){
							//���ý׶�
							long time = System.currentTimeMillis() - op.getStarttime().getTime();
							if(time > 0 && time <= 15 * 24 * 3600 * 1000){
								//���� 15��
								return true;
							}
						}else if(IPrivilegeConstants.OWNER_PERMISSION_NOR.equalsIgnoreCase(op.getStatus())){
							//��ʽʹ��
							long time = System.currentTimeMillis() - op.getEndtime().getTime();
							if(permission.privilege() <= op.getLevel() && time < 0 ){
								// Ȩ�޵ȼ���  && δ����
								return true;
							}
						}else{
							//�Ƿ�����
							return false;
						}
					}  
				}  
			}
            //˵�������ĸ��û����е�Ȩ���飬û�з��ָ�Ȩ�ޣ�˵��û�и�Ȩ��  
            return false;  
		}
		//û�б�עע�⣬��ʾ˭�����Ե��ø÷���
		return true;
	}

}
