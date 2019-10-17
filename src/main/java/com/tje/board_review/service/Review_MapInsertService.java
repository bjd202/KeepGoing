package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class Review_MapInsertService {
	@Autowired
	private Review_MapDAO review_MapDAO;
	
	public Object service(Review_Map r_m) {
		Object result=null;
		try {
			result=review_MapDAO.selectOne(r_m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
