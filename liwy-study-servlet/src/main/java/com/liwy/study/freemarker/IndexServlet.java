package com.liwy.study.freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class IndexServlet extends HttpServlet {
	
	public void init() throws ServletException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		
		//dao-获取文章列表
		
		
		String username = (String) request.getSession().getAttribute("username");
		
		
		response.setContentType("text/html;charset=utf8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>开源小屋首页</TITLE></HEAD>");
		out.println("  <body>");
		if(username!=null) {
			out.println("  欢迎"+username+"进入开源小屋网站!<a href=''>新增文章</a><a href='"+path+"/userLogout'>退出登陆</a>");
		} else {
			out.println("  欢迎进入开源小屋网站：<a href='"+path+"/login'>登陆</a>--<a href='"+path+"/regist'>注册</a>");
		}
		out.println("  <table><tr><td>最新文章</td></tr>");
		for(int i=0;i<12;i++) {
			out.println("  <tr><td>文章"+i+"  "+(username!=null?"<a href=''>[删除]</a>":"")+"</td></tr>");
		}
		out.println("  </table>");
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
