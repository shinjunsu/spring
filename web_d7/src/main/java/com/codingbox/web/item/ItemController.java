package com.codingbox.web.item;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
/*
 * @RequiredArgsConstructor
 * : final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어 준다. 
 */
public class ItemController {

	private final ItemRepository itemRepository;
	
	/*
	 * 이렇게 생성자가 딱 1개만 있으면 스프링이
	 * 해당 생성자에 @Autowired로 의존관계를 주입해준다. 
	 */
//	@Autowired
//	public BasicController(ItemRepository itemRepository) {
//		this.itemRepository = itemRepository;
//	}
	
	@GetMapping
	public String items(Model model) {
		// 로그인체크 여부
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "items/items";
	}
	
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		// 로그인체크 여부
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "items/item";
	}
	
	@GetMapping("/add")
	public String addForm() {
		// 로그인체크 여부
		return "items/addForm";
	}
	
	@PostMapping("/add")
	public String save6(Item item, 
			RedirectAttributes redirectAttributes) {
		// 로그인체크 여부
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/items/{itemId}";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, 
			Model model) {
		// 로그인체크 여부
		/*
		 * itemRepository에서 itemId값으로 조회
		 * model 담아서, editForm.html 이동
		 * value값을 화면에서 조회 
		 * static/editForm.html 
		 * 	-> templates.basic.editForm.html
		 * 4시까지
		 */
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "items/editForm";
	}
	
	/**
	 * /{itemId}/edit 그대로 post방식으로 상품 수정
	 * get 	/items/{itemId}/edit	: 상품 수정 폼 화면
	 * post	/items/{itemId}/edit	: 상품 수정 기능 처리
	 */
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, 
				@ModelAttribute Item item) {
		// 로그인체크 여부
		itemRepository.update(itemId, item);
		return "redirect:/items/{itemId}";
	}
	
	
	/*
	 * 테스트용 데이터 추가
	 */
//	@PostConstruct
//	public void init() {
//		System.out.println("초기화 메서드");
//		itemRepository.save(new Item("testA", 10000, 10));
//		itemRepository.save(new Item("testB", 20000, 20));
//	}
	
	/**
	 * 종료 메서드
	 */
	@PreDestroy
	public void destory() {
		System.out.println("종료 메서드 호출");
	}
	
}












