package com.tje.model;

import java.util.Date;

public class Sold_item {
	private int sold_item_id;
	private int board_id;
	private int category;
	private String member_id;
	private String name;
	private String address_post;
	private String address_basic;
	private String address_detail;
	private String title;
	private int number;
	private String price;
	private Date sold_date;

	public Sold_item() {

	}

	public Sold_item(int sold_item_id, int board_id, int category, String member_id, String name, String address_post,
			String address_basic, String address_detail, String title, int number, String price, Date sold_date) {
		super();
		this.sold_item_id = sold_item_id;
		this.board_id = board_id;
		this.category = category;
		this.member_id = member_id;
		this.name = name;
		this.address_post = address_post;
		this.address_basic = address_basic;
		this.address_detail = address_detail;
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
	
	public String getCategoryString() {
		if(this.category==1)
			return "운동기구";
		else if(this.category==2)
			return "보충제";
		else
			return "기타";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress_post() {
		return address_post;
	}

	public void setAddress_post(String address_post) {
		this.address_post = address_post;
	}

	public String getAddress_basic() {
		return address_basic;
	}

	public void setAddress_basic(String address_basic) {
		this.address_basic = address_basic;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
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
