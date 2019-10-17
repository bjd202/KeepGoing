package com.tje.homeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class HomeFreeService {
	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;
	
	public Object service() {
		Object result = null;

		try {
			result = detailBoardFreeViewDAO.selectOrderByLikeCount();
		} catch (Exception e) {
			System.out.println("HomeFreeView service 부분 예외");
			e.printStackTrace();
		}

		return result;
	}
}
