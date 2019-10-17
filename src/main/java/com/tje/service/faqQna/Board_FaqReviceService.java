package com.tje.service.faqQna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Faq;
import com.tje.repo.Board_FaqDAO;


@Service
public class Board_FaqReviceService {
	@Autowired
	private Board_FaqDAO board_faqDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		try {
			result=board_faqDAO.update((Board_Faq)args);
		} catch (Exception e) {
			result=null;
		}
		return result;
	}

}
