package com.tje.homeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class HomeItemService {
	@Autowired
	private SimpleBoardItemViewDAO simpleBoardItemViewDAO;
	
	public Object service() {
		Object result = null;

		try {
			result = simpleBoardItemViewDAO.selectOrderByLikeCount();
		} catch (Exception e) {
			System.out.println("HomeItemView service 부분 예외");
			e.printStackTrace();
		}

		return result;
	}
}