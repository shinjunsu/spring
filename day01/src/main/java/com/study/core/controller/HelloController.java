package com.study.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        System.out.println("hello mapping");
        model.addAttribute("data", "hello mapping!!!");
        //return "redirect:/hello";
        //return "forward:/hello";
        return "hello";
    }

    // @RequestParam("name") 	: 기본 name이라는 key값을 파싱한다.
    //							  옵션을 넣어 줄 수 있다.
    // 	- required : 파라미터 값 필수 여부,
    //				 true 	-> 필수(default)
    //				 false 	-> 필수 아님
    //  - defaultValue : 파라미터 값이 없을 경우, 기본으로 들어갈 값
    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false, defaultValue = "required value") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


}
