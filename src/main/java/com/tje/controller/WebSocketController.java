package com.tje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {
	
	@GetMapping("/chatPop")
	public String chatPop() {
		return "chatPop";
	}
	
	@GetMapping("/chattingWithAdmin")
	public String chattingWithAdmin() {
		return "chattingWithAdmin";
	}
	
	@GetMapping("/chattingGroupSelect")
	public String chattingGroupSelect() {
		return "chattingGroupSelect";
	}
	
	@GetMapping("/chattingGroupModal_1")
	public String chattingGroupModal_1() {
		return "chattingGroupModal_1";
	}
	
	@GetMapping("/chattingGroupModal_2")
	public String chattingGroupModal_2() {
		return "chattingGroupModal_2";
	}
	
	@GetMapping("/chattingGroupModal_3")
	public String chattingGroupModal_3() {
		return "chattingGroupModal_3";
	}
	
	@GetMapping("/chattingGroupModal_4")
	public String chattingGroupModal_4() {
		return "chattingGroupModal_4";
	}
	
	@GetMapping("/chattingGroupModal_5")
	public String chattingGroupModal_5() {
		return "chattingGroupModal_5";
	}
	
	@GetMapping("/chattingGroupModal_6")
	public String chattingGroupModal_6() {
		return "chattingGroupModal_6";
	}
}
