package com.tje.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Cart;
import com.tje.model.DetailBoardItemView;
import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.repo.CartDAO;
import com.tje.repo.DetailBoardItemViewDAO;
import com.tje.repo.MemberDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class MembersDeleteService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service(List<Member> args) throws Exception {
		Object result=null;
		
		result=memberDAO.batchDelete(args);
		System.out.println(result);
		
		return result;
	}
}
