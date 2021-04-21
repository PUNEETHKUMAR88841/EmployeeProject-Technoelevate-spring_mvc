package com.te.springmvc2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestDeligationController {

	@GetMapping("/redirect")
	public String redirectPage() {
		return "redirect:https://youtube.com";
	}

	@GetMapping("/forward")
	public String forwardPage() {
		return "forward:empLogin";

	}

}
