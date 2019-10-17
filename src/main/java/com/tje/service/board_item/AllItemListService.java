package com.tje.service.board_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Member;
import com.tje.repo.SimpleBoardItemViewDAO;

@Service
public class AllItemListService {
	@Autowired
	private SimpleBoardItemViewDAO simpleBrdIViewDAO;
	
	public Object service() {
		Object result=null;
		
		result=simpleBrdIViewDAO.selectAll();
		
		return result;
	}
}
