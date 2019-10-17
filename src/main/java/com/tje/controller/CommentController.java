package com.tje.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.model.Comment;
import com.tje.service.common.CommentAddService;
import com.tje.service.common.CommentDeleteService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentAddService caService;
	@Autowired
	private CommentDeleteService cdService;
	
	@PostMapping(value = "/comment_add")
	@ResponseBody
	public Comment comment_add(
			Comment comment,
			Model model,
			HttpServletResponse response) {
		
		int r=(int) caService.service(comment);
		comment.setComment_id(r);
		
		response.setContentType("application/json; charset=utf-8");
		
		if(r!=0) {
			return comment;
		}
		
		return null;
	}
	
	@PostMapping(value = "/comment_delete", produces = "application/text; charset=utf8")
	@ResponseBody
	public String comment_delete(@RequestParam("comment_id") int comment_id) {
		
		Comment comment=new Comment();
		comment.setComment_id(comment_id);
		
		int r=(int) cdService.service(comment);
		
		if(r==1) {
			return "댓글 삭제을 완료했습니다.";
		}
		
		return "댓글 삭제를 실패했습니다.";
	}
}
