package com.liwy.study.freemarker;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class UserLogoutServlet extends HttpServlet {

	public void init() throws ServletException {
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
		}
		response.sendRedirect(path);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	public void destroy() {
		super.destroy();
	}
}
