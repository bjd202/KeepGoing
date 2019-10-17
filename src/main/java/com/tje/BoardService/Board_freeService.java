package com.tje.BoardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Free;
import com.tje.repo.Board_freeDAO;
import com.tje.repo.Board_qnaDAO;


	@Service
	public class Board_freeService {
		@Autowired
		private Board_freeDAO board_freeDAO;
		
		public Object service(Object args) {
			Object result=null;
			
			result=board_freeDAO.insert((Board_Free)args);
			
			return result;
		}

}
