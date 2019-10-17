package com.tje.service.board_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.*;

@Service
public class SimpleBoardItemListCountCriteriaService {
	@Autowired
	private SimpleBoardItemViewDAO simpleBoardItemViewDAO;
	
	public Object service() {
		Object result=null;
		result=simpleBoardItemViewDAO.listCountCriteria();
		return result;
	}
}
