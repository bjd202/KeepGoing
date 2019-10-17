package com.tje.model;

public class Board_Faq {
	private int board_id;
	private int topic;
	private int head;
	private int category;
	private String title;
	private String content;
	
	public Board_Faq() {
	}


	public Board_Faq(int board_id, int topic, int head, int category, String title, String content) {
		super();
		this.board_id = board_id;
		this.topic = topic;
		this.head = head;
		this.category = category;
		this.title = title;
		this.content = content;
	}



	public int getHead() {
		return head;
	}


	public void setHead(int head) {
		this.head = head;
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
	
	
}
