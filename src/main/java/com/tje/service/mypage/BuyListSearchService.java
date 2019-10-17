package com.tje.service.mypage;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Cart;
import com.tje.model.DetailBoardItemView;
import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.repo.Board_ReviewDAO;
import com.tje.repo.Board_freeDAO;
import com.tje.repo.Board_itemDAO;
import com.tje.repo.Board_qnaDAO;
import com.tje.repo.CartDAO;
import com.tje.repo.DetailBoardItemViewDAO;
import com.tje.repo.MemberDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class BuyListSearchService {
	@Autowired
	private Sold_itemDAO sold_itemDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		HashMap<String, Object> values=(HashMap<String, Object>) args;

		result=sold_itemDAO.select_search(values);
		
		return result;
	}
}
