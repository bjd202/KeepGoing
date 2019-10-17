package com.tje.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.model.LikeAndDislike;
import com.tje.model.Member;
import com.tje.service.common.LikeAndDislikeService;

@Controller
public class LikeAndDislikeController {
	
	@Autowired
	private LikeAndDislikeService ladService;
	
	@PostMapping(value = "/like_and_dislike", produces = "application/text; charset=utf8")
	@ResponseBody
	public String like_and_dislike(
			@RequestBody Map<String, Object> map,
			HttpSession session,
			Model model) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		LikeAndDislike lad=new LikeAndDislike();
		lad.setMember_id(login_member.getMember_id());
		lad.setBoard_id((int) map.get("board_id"));
		lad.setTopic((int) map.get("topic"));
		
		int status=(int) map.get("status");
		
		int r=0;
		
		switch (status) {
		case 1:	
			lad.setIs_like(1);
			r=(int) ladService.update(lad);
			break;
		case 2:	
			lad.setIs_like(1);
			r=(int) ladService.insert(lad);
			break;
		case 3:	
			r=(int) ladService.delete(lad);
			break;
		case 4:	
			lad.setIs_like(2);
			r=(int) ladService.update(lad);
			break;
		case 5:	
			lad.setIs_like(2);
			r=(int) ladService.insert(lad);
			break;
		case 6:	
			r=(int) ladService.delete(lad);
			break;
		default:
			break;
		}
		
		if(r!=0) {
			return "success";
		}
		
		return "fail";
	}
}
