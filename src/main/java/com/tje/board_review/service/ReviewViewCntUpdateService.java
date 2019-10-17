package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class ReviewViewCntUpdateService {

	@Autowired
	private DetailBoardReviewViewDAO detailBoardReviewViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = detailBoardReviewViewDAO.update_view_cnt((DetailBoardReviewView)args);

		return result;
	}
}
