package com.tje.board_review.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;
import com.tje.model.*;

@Service
public class Board_ReviewInsertService {
	@Autowired
	private Board_ReviewDAO board_ReviewDAO;
	
	public Object service(Board_Review b_r) {
		Object result=null;
		try {
			result=board_ReviewDAO.insert(b_r);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(b_r.getBoard_id());
			System.out.println(b_r.getTopic());
			System.out.println(b_r.getCategory());
			System.out.println(b_r.getTitle());
			System.out.println(b_r.getContent());
			System.out.println(b_r.getMember_id());
			System.out.println(b_r.getImage());
			System.out.println(b_r.getWrite_date());
			result = null;
		}
		return result;
	}
}
