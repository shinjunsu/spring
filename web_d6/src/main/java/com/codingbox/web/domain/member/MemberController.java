package com.codingbox.web.domain.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberRepository memberRepository;

	/*
	 * - @{/members/add} url이 매핑되는 컨트롤러
	 *  - /members 	: controller단에서 매핑
	 *  - /add		: method단에서 매핑
	 * - addForm()
	 * - members/addMemberForm
	 * - 제공된 html파일명에서 '_'는 삭제 
	 */
	
	/*
	 * @ModelAttribute("member")Member member
	 * 	-> model.addAttribute("member", new Member()); 
	 */
	@GetMapping("/add")
	public String addForm(@ModelAttribute("member")Member member) {
		return "members/addMemberForm";
	}
	
	/*
	 *	/add post요청
	 *  memberrepository 의 save()
	 *  home.html  페이지 이동
	 */
	@PostMapping("/add")
	public String save(@ModelAttribute Member member) {
		memberRepository.save(member);
		return "redirect:/";
	}
	
}










