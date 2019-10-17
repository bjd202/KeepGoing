package com.tje.service.board_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.*;

@Service
public class SimpleBoardItemListCriteriaService {
	@Autowired
	private SimpleBoardItemViewDAO simpleBoardItemViewDAO;
	
	public Object service(Criteria criteria) {
		Object result=null;
		result=simpleBoardItemViewDAO.listCriteria(criteria.getPageStart(), criteria.getPerPageNum());
		return result;
	}
}
