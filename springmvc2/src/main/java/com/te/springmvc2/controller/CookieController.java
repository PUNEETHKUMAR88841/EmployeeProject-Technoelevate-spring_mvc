package com.te.springmvc2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie")
public class CookieController {

	@GetMapping("/cookiesPage")
	public String getCookies() {
		return "cookies";
	}

	@GetMapping("/createCookie")
	public String name(HttpServletResponse response, ModelMap map) {
		Cookie cookie = new Cookie("EmpName", "Sai");
		response.addCookie(cookie);
		map.addAttribute("msg", "created Cookies");
		return "cookies";
	}

	@GetMapping("/showCookie")
	public String showCookies(@CookieValue(name = "EmpName", required = false) String name, ModelMap map) {
		map.addAttribute("cookie", name);
		return "cookies";
	}

}// end of class
