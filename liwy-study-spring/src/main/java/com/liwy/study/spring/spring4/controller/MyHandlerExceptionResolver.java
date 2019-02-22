package com.liwy.study.spring.spring4.controller;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <b>名称：</b> 自定义全局异常<br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/2/20 16:39 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@Component
public class MyHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) {
        System.out.println("自定义异常处理器");
        ResponseStatus responseStatus = AnnotationUtils.getAnnotation(ex.getClass(), ResponseStatus.class); // 获取请求响应状态编码
        ModelAndView modelAndView = new ModelAndView("error/502");
        //自定义的异常
        if (responseStatus != null) {
            modelAndView.setViewName("error/" + responseStatus.value().value());
        } else {
            //其他异常，做一些其他的处理,如发送错误报警邮件，记录日志
            logger.error(ex.getMessage(), ex);
        }
        return modelAndView;
    }
}
