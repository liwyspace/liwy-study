package com.liwy.project.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.liwy.project.service.IUserService;

/**
 * 自定义系统用户过滤器
 * 
 * @author liwy
 * 
 */
public class SysUserFilter extends PathMatchingFilter {
	@Autowired
	private IUserService userService;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {

		String username = (String) SecurityUtils.getSubject().getPrincipal();
		request.setAttribute("user",
				userService.findByUsername(username));
		return true;
	}
}
