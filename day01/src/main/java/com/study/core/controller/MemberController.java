package com.study.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.core.dto.MemberDTO;

@Controller
public class MemberController {

	// member url mapping
	@RequestMapping("member")
	public String getMember(Model model) {
		MemberDTO member 
			= new MemberDTO(1, "자바학생", "01012345678");
		model.addAttribute("member", member);
		return "thymeleaf/member";
	}
	
	
	
	
	
	
	
	
}
