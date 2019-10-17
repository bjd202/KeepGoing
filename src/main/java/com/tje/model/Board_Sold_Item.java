package com.tje.model;

import java.util.Date;

public class Board_Sold_Item {
	private int sold_item_id;
	private int board_id;
	private int category;
	private String member_id;
	private String title;
	private int number;
	private String price;
	private Date sold_date;
	
	public Board_Sold_Item() {
	}

	public Board_Sold_Item(int sold_item_id, int board_id, int category, String member_id, String title, int number,
			String price, Date sold_date) {
		this.sold_item_id = sold_item_id;
		this.board_id = board_id;
		this.category = category;
		this.member_id = member_id;
		this.title = title;
		this.number = number;
		this.price = price;
		this.sold_date = sold_date;
	}

	public int getSold_item_id() {
		return sold_item_id;
	}

	public void setSold_item_id(int sold_item_id) {
		this.sold_item_id = sold_item_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Date getSold_date() {
		return sold_date;
	}

	public void setSold_date(Date sold_date) {
		this.sold_date = sold_date;
	}
	
}
