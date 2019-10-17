package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.repo.*;
import com.tje.model.*;

@Service
public class ReviewUpdateTransactionService {
	@Autowired
	private Board_ReviewDAO board_ReviewDAO;
	@Autowired
	private Review_MapDAO review_MapDAO;
	
	
	@Transactional
	public boolean service(Board_Review b_r, Review_Map r_m) throws Exception {
		System.out.println("트랜잭션 시작 - service 메소드 시작부분");
		boolean board_result = false;
		boolean map_result = false;
		int result = (Integer)board_ReviewDAO.update(b_r);
		board_result = true;
		r_m.setBoard_id(b_r.getBoard_id());
		// board_result = (Integer)board_ReviewDAO.insertReturnsKey(b_r) ==  ? true : false;
		map_result = (Integer)review_MapDAO.update(r_m) == 1 ? true : false;
		
		
		
		return board_result && map_result;
	}
	
	
}
