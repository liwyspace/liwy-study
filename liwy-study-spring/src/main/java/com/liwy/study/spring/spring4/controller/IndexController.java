package com.liwy.study.spring.spring4.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView index(Model model, Map map, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        model.addAttribute("userName","liwey");
        map.put("password", "password123");
        modelMap.addAttribute("sex", "男");
        request.setAttribute("study","study112");
        session.setAttribute("auth","true333");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("classs", "first44");
        modelAndView.getModel().put("flass", "flass55");
        modelAndView.setViewName("index");

        return modelAndView;
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/redirectWel")
    public String redirect() {
        return "redirect:/welcome";
    }

    @RequestMapping("/forwardWel")
    public String forward() {
        return "forward:/welcome";
    }
}
