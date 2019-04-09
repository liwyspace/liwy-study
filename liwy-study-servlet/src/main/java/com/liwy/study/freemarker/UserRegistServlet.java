package com.liwy.study.freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class UserRegistServlet extends HttpServlet {

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getContextPath();
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		String repassword = (String) request.getParameter("repassword");
		String email = (String) request.getParameter("email");
		System.out.println(username+"######"+password);
		System.out.println(repassword+"######"+email);
		
		//dao-新增用户
		
		HttpSession session = request.getSession();
		
		response.sendRedirect(path);
		session.setAttribute("username", username);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
