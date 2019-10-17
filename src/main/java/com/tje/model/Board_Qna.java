package com.tje.model;

import java.util.Date;

public class Board_Qna {

	private int board_id;
	private int topic;
	private int category;
	private String title;
	private String content;
	private String member_id;
	private String nickname;
	private int view_cnt;
	private Date write_date;
	
	public Board_Qna() {
		// TODO Auto-generated constructor stub
	}

	public Board_Qna(int board_id, int topic, int category, String title, String content, String member_id,
			String nickname, int view_cnt, Date write_date) {
		super();
		this.board_id = board_id;
		this.topic = topic;
		this.category = category;
		this.title = title;
		this.content = content;
		this.member_id = member_id;
		this.nickname = nickname;
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

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
