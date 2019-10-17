package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class DetailBoardReviewViewSelectOneService {
	@Autowired
	private DetailBoardReviewViewDAO detailBoardReviewViewDAO;
	
	public Object service(DetailBoardReviewView dbrv) {
		Object result=null;
		try {
			result=detailBoardReviewViewDAO.selectOne(dbrv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
