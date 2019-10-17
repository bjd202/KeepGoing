package com.tje.service.faqQna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Qna;
import com.tje.repo.Board_qnaDAO;


	@Service
	public class Board_qnaService {
		@Autowired
		private Board_qnaDAO board_qnaDAO;
		
		public Object service(Object args) {
			Object result=null;
			
			result=board_qnaDAO.insert((Board_Qna)args);
			
			return result;
		}

}
