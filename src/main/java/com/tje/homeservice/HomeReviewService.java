package com.tje.homeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class HomeReviewService {
	@Autowired
	private SimpleBoardReviewViewDAO simpleBoardReviewViewDAO;
	
	public Object service() {
		Object result = null;

		try {
			result = simpleBoardReviewViewDAO.selectOrderByLikeCount();
		} catch (Exception e) {
			System.out.println("HomeReviewView service 부분 예외");
			e.printStackTrace();
		}

		return result;
	}
}
