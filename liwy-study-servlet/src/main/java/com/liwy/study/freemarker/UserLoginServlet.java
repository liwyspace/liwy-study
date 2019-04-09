package com.liwy.study.freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class UserLoginServlet extends HttpServlet {

	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		System.out.println(username+"######"+password);
		
		HttpSession session = request.getSession();
		
		//dao-判断登录用户合法性
		if("123".equals(password)) {
//			request.getRequestDispatcher(path).forward(request, response);
			response.sendRedirect(path);
			session.setAttribute("username", username);
		} else {
			System.out.println(path+"/login");
//			request.getRequestDispatcher(path+"/login").forward(request, response);
			response.sendRedirect(path+"/login");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void destroy() {
		super.destroy();
	}
}
