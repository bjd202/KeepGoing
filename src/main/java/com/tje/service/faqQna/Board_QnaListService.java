package com.tje.service.faqQna;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.Board_qnaDAO;

@Service
public class Board_QnaListService {

	@Autowired
	private Board_qnaDAO board_qnaDAO;

	public Object service(Criteria criteria) {
		HashMap<String, Object> result = new HashMap<String, Object>();

		//result = board_qnaDAO.selectAllOrdByDateDesc();
		
		result.put("list", board_qnaDAO.selectAllOrdByDateDesc(criteria.getPageStart(), criteria.getPerPageNum()));
		result.put("count", board_qnaDAO.listCountCriteria());

		return result;
	}
}
