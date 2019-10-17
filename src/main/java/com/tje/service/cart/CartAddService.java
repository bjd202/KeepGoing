package com.tje.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Cart;
import com.tje.repo.CartDAO;

@Service
public class CartAddService {
	@Autowired
	private CartDAO cartDAO;
	
	public Object service(Object args) {
		Object result=null;
		
		result=cartDAO.insert((Cart)args);
		
		return result;
	}
}
