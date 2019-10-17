package com.tje.service.faqQna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Faq;
import com.tje.repo.Board_FaqDAO;


@Service
public class Board_FaqWriteService {
	@Autowired
	private Board_FaqDAO board_faqDAO;
	
	public Object service(Object args) {
		Object result=null;
		result=board_faqDAO.insert((Board_Faq)args);
		return result;
	}

}
