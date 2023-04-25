package com.codingbox.web.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	/**
	 * localhost:9090
	 * -> home.html(welcome)
	 * -> 12시까지 
	 */
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	
	
	
}










