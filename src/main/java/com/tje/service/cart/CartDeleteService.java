package com.tje.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Cart;
import com.tje.model.Sold_item;
import com.tje.repo.CartDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class CartDeleteService {
	@Autowired
	private CartDAO cartDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Object service(List<Cart> args) throws Exception {
		Object result=null;
				
		result=cartDAO.batchDelete(args);
		System.out.println(result);
		
		return result;
	}
}
