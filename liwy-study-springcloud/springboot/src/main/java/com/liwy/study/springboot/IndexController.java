package com.liwy.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/28 14:28 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @RequestMapping("/first")
    public String index(Model model) {
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
        return "indexFM";
    }
}
