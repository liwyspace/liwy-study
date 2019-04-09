package com.liwy.study.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwy on 2017/4/9.
 */
public class IndexServlet extends HttpServlet{
    private Template template;

    @Override
    public void init() throws ServletException {
        Configuration conf = new Configuration();
        conf.setServletContextForTemplateLoading(getServletContext(),"/WEB-INF/template");
        try {
            template = conf.getTemplate("article.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("你好！！！！");
//        resp.sendRedirect("index.jsp");

//        resp.setContentType("text/html;charset=utf-8");
//        PrintWriter out = resp.getWriter();
//        out.println("你好要李文姚！！！");
        //测试ftl,freemarker
        resp.setContentType("text/html;charset=utf-8");
        Writer out = resp.getWriter();
        Map map = new HashMap();
        map.put("webSite","开源小屋");
        map.put("username","");
        List list = new ArrayList();
        list.add("首页");
        list.add("文章");
        list.add("作品");
        map.put("navers",list);
        Map armap = new HashMap();
        armap.put("title","标题111111");
        armap.put("context","skdjfsjfksdjf上岛咖啡结束了登记方式离开的房间施蒂利克废旧塑料");
        map.put("article",armap);
        try {
            template.process(map,out);
        } catch (TemplateException e) {
            e.printStackTrace();
        }


    }
}
