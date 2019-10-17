package com.tje.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Cart;
import com.tje.model.DetailBoardItemView;
import com.tje.model.Sold_item;
import com.tje.repo.CartDAO;
import com.tje.repo.DetailBoardItemViewDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class SoldItemAddAndCartDeleteService {
	@Autowired
	private Sold_itemDAO sold_itemDAO;
	@Autowired
	private CartDAO cartDAO;
	@Autowired
	private DetailBoardItemViewDAO detailBoardItemViewDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service(List<Sold_item> args1, List<Cart> args2, List<DetailBoardItemView> args3) throws Exception {
		Object result=null;
		
		result=sold_itemDAO.batchInsert(args1);
		System.out.println(result);
		
		result=cartDAO.batchDelete(args2);
		System.out.println(result);
		
		result=detailBoardItemViewDAO.batchNumberUpdate(args3);
		System.out.println(result);
		
		return result;
	}
}
