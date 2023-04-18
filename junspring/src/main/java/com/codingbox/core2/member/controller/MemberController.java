package com.codingbox.core2.member.controller;

import com.codingbox.core2.member.dto.Member;
import com.codingbox.core2.member.dto.MemberFormDTO;
import com.codingbox.core2.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
class MemberController {
    /*
    controller가 service를 의존한다 라고 표현한다.
    service는 여러  controller가 가져다 쓸 수 있기 떄문에
    Spring Container에 등록을 해야한다.
     */
    //MemberService service = new MemberService();

    //스프링 스럽게 작업
    private final MemberService memberService;
    /*
    MemberController가 생성될때, 생성자를 호출해준다.
    즉, service까지 생성해서 자동으로 호출해준다.
    서버 기동시 연결 실패시 에러가 발생한다.
    ->기존은 테스트를 통해서만 service가 오류가 나는 것을
    확인할 수 있었다.
     */
    @Autowired
    MemberController(MemberService memberService){
        this.memberService = memberService;

    }

    @GetMapping("/members/new")
    public String createForm() {
        //model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberFormDTO form){
        Member member = new Member();
        System.out.println(form.getName());
        member.setName(form.getName());
        memberService.join(member);

        //홈 화면으로 돌린다.
        return "redirect:form.getName()";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
