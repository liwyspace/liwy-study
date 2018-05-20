package com.liwy.project.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liwy.project.entity.Resource;
import com.liwy.project.entity.User;
import com.liwy.project.service.IResourceService;
import com.liwy.project.service.IUserService;

@Controller
public class IndexController {

	private static final transient Logger log = LoggerFactory
			.getLogger(IndexController.class);

	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/")
	public String index(Model model) {
		User loginUser = userService.findOne(1L);
		Set<String> permissions = userService.findPermissions(loginUser
				.getUsername());
		List<Resource> menus = resourceService.findMenus(permissions);
		model.addAttribute("menus", menus);
		return "index";
	}

	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getArticle(Map<String, Object> map, @PathVariable("id") Long id) {
		User user = userService.findOne(id);
		log.info(user.toString());
		return user;
	}
}
