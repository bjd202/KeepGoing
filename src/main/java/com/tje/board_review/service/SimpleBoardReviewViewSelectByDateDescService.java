package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class SimpleBoardReviewViewSelectByDateDescService {
	@Autowired
	private SimpleBoardReviewViewDAO simpleBoardReviewViewDAO;
	
	public Object service() {
		Object result=null;
		result=simpleBoardReviewViewDAO.selectAllOrdByDateDesc();
		return result;
	}
}
