package com.tje.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.model.Cart;
import com.tje.model.CartJsonModel;
import com.tje.model.DetailBoardItemView;
import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.service.cart.CartDeleteService;
import com.tje.service.cart.CartListService;
import com.tje.service.cart.SoldItemAddAndCartDeleteService;

@Controller
public class CartController {
	
	@Autowired
	private CartListService clService;
	@Autowired
	private SoldItemAddAndCartDeleteService siaService;
	@Autowired
	private CartDeleteService cdService;
	
	@RequestMapping("/cart")
	public String cart(
			Model model,
			HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member!=null) {
			Cart cart=new Cart();
			cart.setMember_id(login_member.getMember_id());
			model.addAttribute("cartList", clService.service(cart));
		}
		return "cart";
	}
	
	@PostMapping(value = "/item_buy", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String item_buy(@RequestBody List<CartJsonModel> list) {
		
		List<Sold_item> itemList=new ArrayList<Sold_item>();
		List<Cart> cartList=new ArrayList<Cart>();
		List<DetailBoardItemView> de_itemList=new ArrayList<DetailBoardItemView>();
		
		for (CartJsonModel model : list) {
			Sold_item item=new Sold_item(0, model.getBoard_id(), model.getCategory(),
					model.getMember_id(), model.getName(), model.getAddress_post(),
					model.getAddress_basic(), model.getAddress_detail(), model.getTitle(),
					model.getNumber(), model.getPrice(), null);
			
			itemList.add(item);
			
			Cart cart=new Cart();
			cart.setCart_id(model.getCart_id());
			
			cartList.add(cart);
			
			DetailBoardItemView de_item=new DetailBoardItemView();
			de_item.setBoard_id(model.getBoard_id());
			de_item.setNumber(model.getNumber());
			
			de_itemList.add(de_item);
		}
		
		try {
			siaService.service(itemList, cartList, de_itemList);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@PostMapping(value = "/cart_delete", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String cart_delete(@RequestBody List<CartJsonModel> list,
			HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		if(login_member==null) {
			ArrayList<Cart> oldCartList=(ArrayList<Cart>) session.getAttribute("cartList");
			ArrayList<Cart> newCartList=new ArrayList<Cart>();
			
			if(!oldCartList.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getIndex()-1==i)
						continue;

					newCartList.add(oldCartList.get(i));
				}
				
				session.setAttribute("cartList", newCartList);
				return "success";
			}
			
			return "fail";
		}		
		
		List<Cart> cartList=new ArrayList<Cart>();
		
		for (CartJsonModel model : list) {

			Cart cart=new Cart();
			cart.setCart_id(model.getCart_id());
			
			cartList.add(cart);
		}
		
		try {
			cdService.service(cartList);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
}
