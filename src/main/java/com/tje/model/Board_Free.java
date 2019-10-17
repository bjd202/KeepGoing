package com.tje.model;

import java.util.Date;

public class Board_Free {
	private int board_id;
	private int topic;
	private int category;
	private String title;
	private String content;
	private String image;
	private String member_id;
	private int view_cnt;
	private Date write_date;
	
	public Board_Free() {
	}

	public Board_Free(int board_id, int topic, int category, String title, String content, String image,
			String member_id, int view_cnt, Date write_date) {
		super();
		this.board_id = board_id;
		this.topic = topic;
		this.category = category;
		this.title = title;
		this.content = content;
		this.image = image;
		this.member_id = member_id;
		this.view_cnt = view_cnt;
		this.write_date = write_date;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getTopic() {
		return topic;
	}

	public void setTopic(int topic) {
		this.topic = topic;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(int view_cnt) {
		this.view_cnt = view_cnt;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	
	
	
}
