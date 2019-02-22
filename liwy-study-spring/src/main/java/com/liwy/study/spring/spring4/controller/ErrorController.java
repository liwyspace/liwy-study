package com.liwy.study.spring.spring4.controller;

import com.liwy.study.spring.spring4.bean.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/20 14:00 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/user/{id}")
    @ResponseBody
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setUsername("liwey");
        user.setEmail("liwey@126.com");
        int i = 1/0;
        return user;
    }

    /**
     * <b>描述：</b> Controller内处理异常<br/>
     * <b>作者：</b>wenyao02.li<br/>
     * <b>版本：</b>V1.0 <br/>
     *
     * @param exception
     * @return org.springframework.web.servlet.ModelAndView
     */
//    @ExceptionHandler
    public ModelAndView handleException(Exception exception){
        ModelAndView mv = new ModelAndView("error/500");
        mv.addObject("ex",exception);
        return mv;
    }
}
