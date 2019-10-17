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
public class RecentActivitySearchService {
	@Autowired
	private Board_ReviewDAO reviewDAO;
	@Autowired
	private Board_freeDAO freeDAO;
	@Autowired
	private Board_itemDAO itemDAO;
	@Autowired
	private Board_qnaDAO qnaDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		HashMap<String, Object> values=(HashMap<String, Object>) args;
		int topic=(int) values.get("topic");
		
		if(topic==1) {
			result=reviewDAO.select_search(values);
		}else if(topic==2) {
			result=freeDAO.select_search(values);
		}else if (topic==3) {
			result=itemDAO.select_search(values);
		}else if (topic==6) {
			result=qnaDAO.select_search(values);
		}
		
		return result;
	}
}
