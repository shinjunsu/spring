package com.codingbox.item.domain.basic.controller;

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

import com.codingbox.item.domain.item.Item;
import com.codingbox.item.domain.item.ItemRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
/*
 * @RequiredArgsConstructor
 * : final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어 준다. 
 */
public class BasicController {

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
		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "basic/items";
	}
	
	@GetMapping("/{itemId}")
	public String item(@PathVariable long itemId, Model model) {
		Item item = itemRepository.findById(itemId);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
	@GetMapping("/add")
	public String addForm() {
		return "basic/addForm";
	}
	
	/*
	 * @RequestParam 
	 * : html의 name으로 들어온다.
	 */
//	@PostMapping("/add")
	public String save(
				@RequestParam String 	itemName,
				@RequestParam int		price,
				@RequestParam Integer	quantity,
				Model model
			) {
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(price);
		item.setQuantity(quantity);
		
		itemRepository.save(item);
		model.addAttribute("item", item);
		return "basic/item";
	}
	
//	@PostMapping("/add")
	public String save2(@ModelAttribute("item")Item item, 
			Model model) {
		itemRepository.save(item);
//		model.addAttribute("item", item);
//		model.addAttribute(item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute
	 * - name 생략 가능
	 * - model.addAttribute(item); 자동 추가, 생략 가능
	 *   생략시 model에 저장되는 name은 클래스명 첫 글자만
	 *   소문자로 등록 경우만 생략가능, Item -> item
	 */
//	@PostMapping("/add")
	public String save3(@ModelAttribute Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	/*
	 * @ModelAttribute 자체 생략 가능
	 * : 대상 객체는 모델에 자동 등록 된다.
	 */
//	@PostMapping("/add")
	public String save4(Item item) {
		itemRepository.save(item);
		return "basic/item";
	}
	
	// 45분까지
//	@PostMapping("/add")
	public String save5(Item item) {
		itemRepository.save(item);
		return "redirect:/basic/items/" + item.getId();
	}
	
	@PostMapping("/add")
	public String save6(Item item, 
			RedirectAttributes redirectAttributes) {
		Item savedItem = itemRepository.save(item);
		redirectAttributes.addAttribute("itemId", savedItem.getId());
		redirectAttributes.addAttribute("status", true);
		return "redirect:/basic/items/{itemId}";
	}
	
	@GetMapping("/{itemId}/edit")
	public String editForm(@PathVariable Long itemId, 
			Model model) {
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
		return "basic/editForm";
	}
	
	/**
	 * /{itemId}/edit 그대로 post방식으로 상품 수정
	 * get 	/items/{itemId}/edit	: 상품 수정 폼 화면
	 * post	/items/{itemId}/edit	: 상품 수정 기능 처리
	 */
	@PostMapping("/{itemId}/edit")
	public String edit(@PathVariable Long itemId, 
				@ModelAttribute Item item) {
		itemRepository.update(itemId, item);
		return "redirect:/basic/items/{itemId}";
	}
	
	
	
	
	/*
	 * 테스트용 데이터 추가
	 */
	@PostConstruct
	public void init() {
		System.out.println("초기화 메서드");
		itemRepository.save(new Item("testA", 10000, 10));
		itemRepository.save(new Item("testB", 20000, 20));
	}
	
	/**
	 * 종료 메서드
	 */
	@PreDestroy
	public void destory() {
		System.out.println("종료 메서드 호출");
	}
	
}












