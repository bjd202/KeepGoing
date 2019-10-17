package com.tje.model;

public class Board_Info {
	private int topic;
	private String board_name;
	
	public Board_Info() {
	}

	public Board_Info(int topic, String board_name) {
		this.topic = topic;
		this.board_name = board_name;
	}

	public int getTopic() {
		return topic;
	}

	public void setTopic(int topic) {
		this.topic = topic;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoarder_name(String board_name) {
		this.board_name = board_name;
	}
	
	
	
	
	

}
