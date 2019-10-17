package com.tje.model;

import java.util.Date;

public class Board_Notice {
	private int board_id;
	private int topic;
	private int head;
	private String title;
	private String content;
	private Date write_date;
	
	
	public Board_Notice() {
	}


	public Board_Notice(int board_id, int topic, int head, String title, String content, Date write_date) {
		this.board_id = board_id;
		this.topic = topic;
		this.head = head;
		this.title = title;
		this.content = content;
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


	public int getHead() {
		return head;
	}


	public void setHead(int head) {
		this.head = head;
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


	public Date getWrite_date() {
		return write_date;
	}


	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}
	
	
	
	
	
	
	
	
	


}
