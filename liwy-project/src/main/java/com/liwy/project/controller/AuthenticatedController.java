package com.liwy.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticatedController {

	@RequestMapping(value = "/authenticated")
	public String showPay(Model model) {
		return "authenticated";
	}

}
