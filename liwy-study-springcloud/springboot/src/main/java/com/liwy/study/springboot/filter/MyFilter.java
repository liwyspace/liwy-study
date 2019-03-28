package com.liwy.study.springboot.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * <b>模块：</b> <br/>
 * <b>名称：</b> <br/>
 * <b>描述：</b> <br/>
 * <b>作者：</b> wenyao02.li <br/>
 * <b>创建时间：</b> 2019/3/1 18:34 <br/>
 * <b>版本：</b> V1.0 <br/>
 */
@WebFilter(filterName = "myFilter", urlPatterns = {"/myservlet"})
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入Filter啦@@");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
