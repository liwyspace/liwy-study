package com.liwy.study.freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>开源小屋首页</TITLE></HEAD>");
		out.println("  <body>");
		out.println("  <h1>登陆</h2>");
		out.println("  <form action='"+path+"/userLogin' method='post'>");
		out.println("  用户名：<input name='username' type='text' /><br/>");
		out.println("  密码：<input name='password' type='text' /><br/>");
		out.println("  <input type='submit' value='登陆' /><br/>");
		out.println("  </form>");
		out.println("  </bory>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}
	public void destroy() {
		super.destroy();
	}

}
