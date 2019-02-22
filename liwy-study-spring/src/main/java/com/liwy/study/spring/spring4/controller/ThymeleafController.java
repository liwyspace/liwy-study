package com.liwy.study.spring.spring4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>名称：</b> Thymeleaf引擎<br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/22 9:56 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/thyme")
public class ThymeleafController {
    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = new TemplateEngine();
        // 创建视图解析器
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(request.getServletContext());
        resolver.setPrefix("/WEB-INF/thymeleaf/");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        engine.setTemplateResolver(resolver);

        // 创建数据模型
        WebContext webContext = new WebContext(request, response, request.getServletContext());
        webContext.setVariable("user", "Big Joe");
        Map latest = new HashMap();
        webContext.setVariable("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        webContext.setVariable("createTime", new Date());
        webContext.setVariable("isTrue", true);
        webContext.setVariable("list", Arrays.asList("111","2222","333"));

        response.setCharacterEncoding("utf-8");
        engine.process("indexThy.html", webContext, response.getWriter());
    }

    @RequestMapping("/spring")
    public String indexSpring(Model model) {
        model.addAttribute("user", "Big Joe");
        Map latest = new HashMap();
        model.addAttribute("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        model.addAttribute("createTime", new Date());
        model.addAttribute("isTrue", true);
        model.addAttribute("list", Arrays.asList("111","2222","333"));
        return "indexThy";
    }
}
