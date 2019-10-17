package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member;
import com.tje.repo.SimpleBoardItemViewDAO;
import com.tje.repo.SimpleBoardReviewViewDAO;

@Service
public class AllReviewListService {
	@Autowired
	private SimpleBoardReviewViewDAO dao;
	
	public Object service() {
		Object result=null;
		
		result=dao.selectAll();
		
		return result;
	}
}
