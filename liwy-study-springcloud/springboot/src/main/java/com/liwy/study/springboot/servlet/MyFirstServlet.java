package com.liwy.study.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/1 18:14 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@WebServlet(name = "myFirstServlet", urlPatterns = "/myservlet")
public class MyFirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet 进来啦！！！");
        resp.setContentType("text/plain");
        resp.getWriter().append("Hello World");
    }
}
