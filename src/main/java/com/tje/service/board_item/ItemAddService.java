package com.tje.service.board_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Item;
import com.tje.model.Member;
import com.tje.repo.Board_itemDAO;
import com.tje.repo.SimpleBoardItemViewDAO;

@Service
public class ItemAddService {
	@Autowired
	private Board_itemDAO board_itemDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=board_itemDAO.insert((Board_Item)args);
		
		return result;
	}
}
