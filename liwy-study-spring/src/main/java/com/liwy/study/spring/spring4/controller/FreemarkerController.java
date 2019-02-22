package com.liwy.study.spring.spring4.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/20 18:32 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/free")
public class FreemarkerController {

    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        // 创建Configuration并设置模板目录
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setServletContextForTemplateLoading(request.getServletContext(), "/WEB-INF/freeMarker");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 创建数据模型
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        root.put("createTime", new Date());
        root.put("isTrue", true);
        root.put("list", Arrays.asList("111","2222","333"));

        //
        Template temp = cfg.getTemplate("indexFree.ftl");
        temp.process(root, new PrintWriter(System.out));
    }

    @RequestMapping("/html")
    public void index2(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        // 创建Configuration并设置模板目录
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setServletContextForTemplateLoading(request.getServletContext(), "/WEB-INF/freeMarker");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 创建数据模型
        Map root = new HashMap();
        root.put("user", "Big Joe");
        Map latest = new HashMap();
        root.put("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        root.put("createTime", new Date());
        root.put("isTrue", true);
        root.put("list", Arrays.asList("111","2222","333"));

        //
        Template temp = cfg.getTemplate("indexFree.ftl");
        temp.process(root, response.getWriter());
    }

    /**
     * <b>描述：</b> SpringMvc整合FreeMarker<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param model
     * @return java.lang.String
     */
    @RequestMapping("/index")
    public String index3(Model model) {
        System.out.println("生成页面逻辑----------------");
        // 创建数据模型
        model.addAttribute("user", "Big Joe");
        Map latest = new HashMap();
        model.addAttribute("latestProduct", latest);
        latest.put("url", "products/greenmouse.html");
        latest.put("name", "green mouse");
        model.addAttribute("createTime", new Date());
        model.addAttribute("isTrue", true);
        model.addAttribute("list", Arrays.asList("111","2222","333"));
        return "indexFree";
    }

    /**
     * <b>描述：</b> 页面静态化<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param
     * @return java.lang.String
     */
    @RequestMapping("/static")
    public String staticPage(HttpServletRequest request, HttpServletResponse response) throws IOException, TemplateException {
        //首页新闻列表路径
        String indexPath=request.getServletContext().getRealPath("/WEB-INF/html/indexFree.html");

        //文件是否存在
        File file=new File(indexPath);
        if(!file.exists()){
            //如果新闻列表不存在，生成新闻列表
            System.out.println("生成静态页面逻辑==================");

            // 创建Configuration并设置模板目录
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            cfg.setServletContextForTemplateLoading(request.getServletContext(), "/WEB-INF/freeMarker");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            // 创建数据模型
            Map root = new HashMap();
            root.put("user", "Big Joe");
            Map latest = new HashMap();
            root.put("latestProduct", latest);
            latest.put("url", "products/greenmouse.html");
            latest.put("name", "green mouse");
            root.put("createTime", new Date());
            root.put("isTrue", true);
            root.put("list", Arrays.asList("111","2222","333"));

            //合并模板和数据模型
            Template temp = cfg.getTemplate("indexFree.ftl");
            temp.process(root, new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        }

        response.setContentType("text/html; charset=UTF-8");
        return "forward:/html/indexFree.html";
    }
}
