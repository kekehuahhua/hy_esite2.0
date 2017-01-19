package com.huiyee.esite.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.huiyee.esite.constants.IPageConstants;
import com.huiyee.esite.constants.IPrivilegeConstants;
import com.huiyee.esite.dto.VisitLogDto;
import com.huiyee.esite.dto.VisitPageTime;
import com.huiyee.esite.model.Account;
import com.huiyee.esite.model.CookieUser;
import com.huiyee.esite.model.HyUser;
import com.huiyee.esite.model.OwnerPrivilege;
import com.huiyee.esite.model.VisitUser;
import com.huiyee.esite.model.WxUser;
import com.huiyee.esite.util.CacheUtil;
import com.huiyee.esite.util.ClientUserIp;
import com.huiyee.esite.util.HyConfig;
import com.huiyee.esite.util.ImageGenerate;
import com.huiyee.interact.bbs.model.BBSUser;

public class LoginFilter implements Filter
{
	private static final String LOGIN_URI = "login_uri";
	private static final String LOGIN_ACTION_URI = "login_action_uri";
	private String login_page;
	private String login_action;

	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("UTF-8");
		HttpSession session = httpRequest.getSession();
		String servletPath = httpRequest.getServletPath();
		String ticket = httpRequest.getParameter(IPageConstants.TICKET);// ticket
		if(servletPath.contains("/user/visitpagetime"))
		{
			String tid = httpRequest.getParameter("tid");
			String pageid = httpRequest.getParameter("pageid");
			VisitPageTime vpt=new VisitPageTime();
			vpt.setCutid(System.currentTimeMillis());
			try
			{
				vpt.setPageid(Long.valueOf(pageid));
				vpt.setTid(Long.valueOf(tid));
			} catch (NumberFormatException e)
			{
				BufferedImage image = ImageGenerate.generate2();
				ImageIO.write(image,"PNG",response.getOutputStream());
				return;
			}
			Cookie ck = getCookieByName(httpRequest, "hy_user_cookie");
			if(ck!=null)
			{
				vpt.setCookie(ck.getValue());
			}
			CacheUtil.addvpt(vpt);
			BufferedImage image = ImageGenerate.generate2();
			ImageIO.write(image,"PNG",response.getOutputStream());
			return;
		}
//		 if (session.getAttribute("account") == null || true)
//		 {
//		 Account account = new Account();
//		 account.setId(100);
//		 account.getOwner().setId(38);
//		 account.getOwner().setEntity("yibosales");
//		 account.getOwner().setEndtime(new Date(1863123123123L));
//		 account.getOwner().setSup("Y");
//		 account.setUsername("�����û�");
//		 account.getOwner().setSup("Y");
//		 session.setAttribute("account", account);
//		session.setAttribute("accountType", "YYR");
//		 List<OwnerPrivilege> list = new ArrayList<OwnerPrivilege>();
//	 	list.add(new OwnerPrivilege(1,IPrivilegeConstants.MODULE_APP_�ϻ���,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(2,IPrivilegeConstants.MODULE_APP_΢Ͷ��,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(3,IPrivilegeConstants.MODULE_APP_΢����,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(4,IPrivilegeConstants.MODULE_APP_΢����,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(5,IPrivilegeConstants.MODULE_APP_΢����,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(6,IPrivilegeConstants.MODULE_APP_΢ԤԼ,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(7,IPrivilegeConstants.MODULE_APP_Ǳ�͹���,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(8,IPrivilegeConstants.MODULE_APP_�û�����,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(9,IPrivilegeConstants.MODULE_APP_�����̳�,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(10,IPrivilegeConstants.MODULE_APP_վ������,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(11,IPrivilegeConstants.MODULE_APP_����ǩ��,2,"NOR",new Date(1863123123123L)));
//	 	list.add(new OwnerPrivilege(12,IPrivilegeConstants.MODULE_APP_��������,2,"NOR",new Date(1863123123123L)));
//	 	account.getOwner().setPrivileges(list);
//		 
//		 }
//		 if (session.getAttribute("visitUser") == null || true)
//		 {
//		 VisitUser u = new VisitUser();
//		 HyUser hyuser = new HyUser();
//		 hyuser.setId(155);
//		 u.setHyUser(hyuser);
//		 u.setWxuid(28023);
//		 u.setCd(1);
//		 WxUser wx = new WxUser();
//		 wx.setMpid(44);
//		 wx.setHeadimgurl("http://wx.qlogo.cn/mmopen/tkbXqxVjvjFATcccppXfoAOysAy6wEibW0zXCOx2n3sGADoVRuiaroc6fXvXq6sx1DpVv73F0icmD3hFfDTRPt9JJoMvI1VkUm7/0");
//		 u.setWxUser(wx);
//		 u.setKv("ctt-hy-630");
//		 session.setAttribute("visitUser", u);
//		 BBSUser bbsUser = new BBSUser();
//		 bbsUser.setId(88);
//		 bbsUser.setImg("http://tp4.sinaimg.cn/2126195363/180/5707565628/1");
//		 u.setBbsUser(bbsUser);
//		 }
		
		if (ticket != null)
		{
			if ("/user/unifylogin.action".equals(servletPath))
			{
				chain.doFilter(request, response);
				return;
			}
			String queryString = httpRequest.getQueryString();
			String reUrl = (queryString == null) ? servletPath : servletPath + "?" + queryString;
			// httpRequest.getSession().setAttribute(IPageConstants.TICKET,ticket);
			httpRequest.getSession().setAttribute(IPageConstants.REDIRECT_URL, reUrl);
			((HttpServletResponse) response).sendRedirect("/user/unifylogin.action?ticket=" + ticket);
			return;
		}
		// �Ѿ���¼��
		if (session.getAttribute("account") != null)
		// if(true)
		{
			chain.doFilter(request, response);
			return;
		}
		// δ��¼�ģ�������ʵ�¼ҳ����ύ��¼������doFilter()
		if (login_action.equals(servletPath) || login_page.equals(servletPath) || servletPath.contains("/user/")|| servletPath.contains("/member/") || servletPath.contains("/weixin/") || "/page/page.action".equals(servletPath))
		{
			if (!HyConfig.isRun())// ��̨���ܼ�¼��־
			{
				if(servletPath.contains("/user/vote.action"))//��ֹͶƱˢƱ
				{
					Cookie ck = getCookieByName(httpRequest, "hy_user_cookie");
					if(ck==null|| StringUtils.isBlank(ck.getValue())){
						return;
					}
				}
				if (servletPath.contains("/user/show.action") || servletPath.contains("/user/crmshow.action") || servletPath.contains("/member/"))
				{
					Cookie ck = getCookieByName(httpRequest, "hy_user_cookie");
					if (ck == null || StringUtils.isBlank(ck.getValue()))
					{
						UUID uuid = UUID.randomUUID();
						String str = uuid.toString();
						ck = new Cookie("hy_user_cookie", str);
						ck.setMaxAge(3600 * 24 * 365 * 10);
						ck.setPath("/");// ����·�������·�����ù����¶����Է��ʸ�cookie
						// ���������·������ôֻ�����ø�cookie·��������·�����Է���
						((HttpServletResponse) response).addCookie(ck);
					}
					String ip = ClientUserIp.getIpAddr(httpRequest);
					String uagent = httpRequest.getHeader("user-agent");
					VisitLogDto vl = new VisitLogDto();
					vl.setCreatetime(System.currentTimeMillis());
					vl.setIp(ip);
					vl.setUagent(uagent);
					vl.setUrl(servletPath + "?" + httpRequest.getQueryString());
					vl.setCookie(ck.getValue());
					if (servletPath.contains("/user/crmshow"))
					{
						String openid = httpRequest.getParameter("openid");
						if (StringUtils.isNotBlank(openid))
						{
							vl.setOpenid(openid);
						}
						vl.setMtype(3);
					}
					else
					{
						VisitUser vux = (VisitUser) session.getAttribute("visitUser");
						String source = httpRequest.getParameter("source");
						String kv = httpRequest.getParameter("kv");
						String pageid = httpRequest.getParameter("pageid");
						String[] strs=servletPath.split("/");
						String oname="";
						if(!strs[1].equals("user")&&!strs[1].equals("page")&&!strs[1].equals("interact")&&!strs[1].equals("weixin"))
						{
							oname=strs[1];
						}
						if( servletPath.contains("/member/"))//��ԱȨ�޿��ƣ��Ժ�϶�Ҫ����ʱ�򵥴���
						{
							if(vux==null||vux.getHyUser()==null)
							{
								((HttpServletResponse) response).sendRedirect("/"+oname+ "/user/hyuser/toLogin.action?pid="+pageid);
								return;
							}
						}
						if (vux != null)// ������״ο϶���΢��΢�ŷ���
						{
							if(vux.getOname()!=null&&!vux.getOname().equalsIgnoreCase(oname)){
								vux = new VisitUser();
								vux.setOname(oname);
								session.setAttribute("visitUser", vux);
							}
							if (vux.getCd() == 1)
							{
								if (vux.getWxUser() != null){
									vl.setOpenid(vux.getWxUser().getOpenid());
									vl.setWxuid(vux.getWxUser().getId());
									vl.setGz(vux.getWxUser().getSubscribe());
								}
							}
							else
							{
								vl.setWbuid(vux.getWbuid());// ��ʹ�Ƕ���վ���������Ҳ��û��ϵ��������0����
							}
							vl.setMtype(vux.getCd());
						}
						else
						// ����վ��
						{
							vux = new VisitUser();
							vux.setOname(oname);
							session.setAttribute("visitUser", vux);
						}
						if (StringUtils.isNotBlank(pageid))
						{
							vux.setSource(source, Long.valueOf(pageid));
							vux.setKv(kv);
							vl.setPageid(vux.getPageid());
							vl.setSource(vux.getSource());
							vl.setSkey(vux.getSkey());
							vl.setSvalue(vux.getSvalue());
							//ҳ�����ʱ������
							httpRequest.setAttribute("xtst", vl.getCreatetime());
						}
						if (vux.getCookieUser() == null)
						{
							CookieUser hy = new CookieUser();
							hy.setCookie(vl.getCookie());
							vux.setCookieUser(hy);
						}
						if(vux.getHyUser()!=null)
						{
							vl.setHyuid(vux.getHyUser().getId());
						}
					}
					CacheUtil.addVisitLog(vl);
				}
//				else if(!servletPath.contains("/user/wxoauth.action")&&!servletPath.contains("/user/wxshowpage.action")&&!servletPath.contains("/user/showpage")&&!servletPath.contains("/user/look")&&!servletPath.contains("/user/cc")&&!servletPath.contains("/user/xcSwitchAction")&&!servletPath.contains("/user/xcCacheAction")&&!servletPath.contains("/user/xcCacheAction"))
//				{
//					if(session.getAttribute("visitUser")==null)
//					{
//						System.out.println(servletPath+"���������ˣ�");
//						response.getWriter().print("000");
//						return;
//					}
//				}
			}
			chain.doFilter(request, response);
			return;
		}

		if ("GET".equalsIgnoreCase(httpRequest.getMethod()))
		{
			// δ��¼��Ҳ���Ƿ��ʵ�¼ҳ����ύ��¼���󣬹�ת���¼ҳ��
			String queryString = httpRequest.getQueryString();
			String reUrl = (queryString == null) ? servletPath : servletPath + "?" + queryString;
			session.setAttribute(IPageConstants.REDIRECT_URL, reUrl);// ����ҳ��
		}

		if ("1".equals(httpRequest.getParameter("inajax")))
		{
			response.getWriter().print("000");
			return;
		}

		((HttpServletResponse) response).sendRedirect(login_page);
		return;
	}

	/**
	 * ����cookie
	 * 
	 * @param response
	 * @param name
	 *            cookie����
	 * @param value
	 *            cookieֵ
	 * @param maxAge
	 *            cookie�������� ����Ϊ��λ
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * �������ֻ�ȡcookie
	 * 
	 * @param request
	 * @param name
	 *            cookie����
	 * @return
	 */
	public static Cookie getCookieByName(HttpServletRequest request, String name)
	{
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name))
		{
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		}
		else
		{
			return null;
		}
	}

	/**
	 * ��cookie��װ��Map����
	 * 
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request)
	{
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies)
		{
			for (Cookie cookie : cookies)
			{
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}

	public void init(FilterConfig filterConfig) throws ServletException
	{
		login_action = filterConfig.getInitParameter(LOGIN_ACTION_URI);
		login_page = filterConfig.getInitParameter(LOGIN_URI);
	}

}
