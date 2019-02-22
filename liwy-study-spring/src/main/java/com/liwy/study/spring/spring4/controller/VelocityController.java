package com.liwy.study.spring.spring4.controller;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>名称：</b> Velocity模板引擎<br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/21 17:15 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/veloc")
public class VelocityController {
    @RequestMapping("/")
    public void index(HttpServletRequest request) {
        System.out.println(this.getClass().getResource("/").toString());
        System.out.println(request.getServletPath());
        VelocityEngine ve = new VelocityEngine();
//        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
//        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        // web项目中
        ve.setApplicationAttribute("javax.servlet.ServletContext", request.getServletContext());
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "webapp");
        ve.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.servlet.WebappLoader");
        ve.setProperty("webapp.resource.loader.path", "/WEB-INF/velocity/");
        ve.init();

        Template t = ve.getTemplate("indexVeloc.vm");
        VelocityContext ctx = new VelocityContext();

        ctx.put("user", "Big Joe");
        Map latest = new HashMap();
        ctx.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        ctx.put("createTime", new Date());
        ctx.put("isTrue", true);
        ctx.put("list", Arrays.asList("111", "2222", "333"));

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw.toString());
    }

    /**
     * <b>描述：</b> 与springmvc整合<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param model
     * @return java.lang.String
     */
    @RequestMapping("/spring")
    public String indexSpring(Model model) {
        System.out.println("生成页面逻辑----------------");
        // 创建数据模型
        model.addAttribute("user", "Big Joe");
        Map latest = new HashMap();
        model.addAttribute("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        model.addAttribute("createTime", new Date());
        model.addAttribute("isTrue", true);
        model.addAttribute("list", Arrays.asList("111", "2222", "333"));
        return "indexVeloc";
    }

    /**
     * <b>描述：</b> 页面静态化，与freemarker逻辑相同<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/static")
    public String indexStatic() {
        // 参考freemarker逻辑
        return "";
    }
}
