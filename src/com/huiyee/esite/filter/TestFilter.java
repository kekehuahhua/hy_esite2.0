package com.huiyee.esite.filter;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestFilter implements Filter
{

	public void destroy()
	{
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request0, ServletResponse response0,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) request0;
		HttpServletResponse response = (HttpServletResponse) response0;
		request.setCharacterEncoding("UTF-8");
//		if (true)// ��ʱ���ù�����
//		{
//			chain.doFilter(request, response);
//			return;
//		}
		if (true)// ��ʱ���ù�����
		{
			// request.setCharacterEncoding("UTF-8");
			System.out
					.println("===================Start===========================");
			Enumeration enu = request.getHeaderNames();
			while (enu.hasMoreElements())
			{
				Object obj = enu.nextElement();
				String name = (String) obj;
				System.out.println("======" + name + ":"
						+ request.getHeader(name));
			}
			// System.out.println(request.getRemoteAddr());
			// String serverName = request.getServerName();
			// if(serverName.equals(""))
			// {
			// }
			// System.out.println(request.getServerName());

			if (request.getMethod().equals("POST"))
			{
//				/* �ļ��ϴ�������ȡ������ */
//				DiskFileItemFactory factory = new DiskFileItemFactory();
//				ServletFileUpload upload = new ServletFileUpload(factory);
//
//				try
//				{
//					List items = upload.parseRequest(request);
//					Iterator it = items.iterator();
//					while (it.hasNext())
//					{
//						FileItem item = (FileItem) it.next();
//						if (item.isFormField())
//						{
//							System.out.println("���Ĳ������ƣ�" + item.getFieldName()
//									+ ",��Ӧ�Ĳ���ֵ��" + item.getString("UTF-8"));
//						}
//						else
//						{
//							if (item.getName() != null
//									&& !item.getName().equals(""))
//							{
//								System.out.println("�ϴ��ļ��Ĳ�������"
//										+ item.getFieldName());
//								System.out.println("�ϴ��ļ��Ĵ�С��" + item.getSize());
//								System.out.println("�ϴ��ļ������ͣ�"
//										+ item.getContentType());
//								System.out.println("�ϴ��ļ������ƣ�" + item.getName());
//
//								// File tempFile = new File(item.getName());
//								// File file = new File(sc.getRealPath("/") +
//								// savePath,
//								// tempFile.getName());
//								// item.write(file);
//								// request.setAttribute("upload.message",
//								// "�ϴ��ļ��ɹ���");
//							}
//							else
//							{
//								// request.setAttribute("upload.message",
//								// "û��ѡ���ϴ��ļ���");
//								System.out.println("û��ѡ���ϴ��ļ���");
//							}
//						}
//					}
//				}
//				catch (Exception e)
//				{
//					e.printStackTrace();
//					// request.setAttribute("upload.message", "�ϴ��ļ����ɹ���");
//					System.out.println("�ϴ��ļ����ɹ���");
//				}

				Enumeration names = request.getParameterNames();
				while (names.hasMoreElements())
				{
					String name = (String) names.nextElement();
					String value = request.getParameter(name);
					System.out.println("------" + name + ":" + value);
				}
			}
			else
			{
				/* ��ͨ���ȡ������ */
				Enumeration names = request.getParameterNames();
				while (names.hasMoreElements())
				{
					String name = (String) names.nextElement();
					String value = request.getParameter(name);
					System.out.println("------" + name + ":" + value);
				}
			}
			// System.out.println(request.getParameter("oldurl"));
			System.out.println(new Date());
			System.out.println(request.getRequestURI());
			System.out.println(request.getRequestURL());
			System.out.println(request.getQueryString());
			System.out
					.println("===================End===========================");
			chain.doFilter(request, response);
			return;
		}

		HttpSession session = request.getSession();
		if (session.getAttribute("testUser") != null
				|| "/login.jsp".equals(request.getRequestURI()))
		{
			chain.doFilter(request, response);
			return;
		}
		else
		{
			String checkCode = request.getParameter("checkCode");
			String checkCodeInSession = (String) session
					.getAttribute("checkCode");
			if (checkCode != null
					&& checkCode.equalsIgnoreCase(checkCodeInSession))
			{
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if ("hy".equals(username) && "hyorg1".equals(password))
				{
					session.setAttribute("testUser", username);
					response.sendRedirect("/index.action");
					return;
				}
			}
		}
		response.sendRedirect("/login.jsp");
		if (true)
		{
			return;
		}

		System.out
				.println("===================Start===========================");
		Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements())
		{
			Object obj = enu.nextElement();
			String name = (String) obj;
			System.out.println("======" + name + ":" + request.getHeader(name));
		}
		System.out.println(request.getRemoteAddr());
		String serverName = request.getServerName();
		if (serverName.equals(""))
		{
		}
		System.out.println(request.getServerName());
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		System.out.println("===================End===========================");

		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}

}
