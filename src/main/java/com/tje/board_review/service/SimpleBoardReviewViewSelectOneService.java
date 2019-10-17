package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.SimpleBoardReviewView;
import com.tje.repo.*;

@Service
public class SimpleBoardReviewViewSelectOneService {
	@Autowired
	private SimpleBoardReviewViewDAO simpleBoardReviewViewDAO;
	
	public Object service(SimpleBoardReviewView sbrv) {
		Object result=null;
		try {
			result=simpleBoardReviewViewDAO.selectOne(sbrv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
