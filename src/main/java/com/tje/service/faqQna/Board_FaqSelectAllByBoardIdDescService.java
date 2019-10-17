package com.tje.service.faqQna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;

@Service
public class Board_FaqSelectAllByBoardIdDescService {
	@Autowired
	private Board_FaqDAO board_faqDAO;
	
	public Object service() {
		Object result=null;
		
		try {
			result=board_faqDAO.selectAllOrdByBoard_IdDesc();
		} catch (Exception e) {
			result=null;
		}
		return result;
	}
}
