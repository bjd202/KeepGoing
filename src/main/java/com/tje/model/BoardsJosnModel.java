package com.tje.model;

public class BoardsJosnModel {
	private int board_id;
	private int topic;

	public BoardsJosnModel() {
		// TODO Auto-generated constructor stub
	}

	public BoardsJosnModel(int board_id, int topic) {
		this.board_id = board_id;
		this.topic = topic;
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

}
