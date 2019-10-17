package com.tje.model;

public class Review_Map {
	private int review_map_id;
	private int topic;
	private int board_id;
	private String selectedAddress;
	private double selectedLat;
	private double selectedLng;

	public Review_Map() {
	}
	
	public Review_Map(int review_map_id, int topic, int board_id, String selectedAddress, double selectedLat,
			double selectedLng) {
		this.review_map_id = review_map_id;
		this.topic = topic;
		this.board_id = board_id;
		this.selectedAddress = selectedAddress;
		this.selectedLat = selectedLat;
		this.selectedLng = selectedLng;
	}

	public int getReview_map_id() {
		return review_map_id;
	}

	public void setReview_map_id(int review_map_id) {
		this.review_map_id = review_map_id;
	}

	public int getTopic() {
		return topic;
	}

	public void setTopic(int topic) {
		this.topic = topic;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getSelectedAddress() {
		return selectedAddress;
	}

	public void setSelectedAddress(String selectedAddress) {
		this.selectedAddress = selectedAddress;
	}

	public double getSelectedLat() {
		return selectedLat;
	}

	public void setSelectedLat(double selectedLat) {
		this.selectedLat = selectedLat;
	}

	public double getSelectedLng() {
		return selectedLng;
	}

	public void setSelectedLng(double selectedLng) {
		this.selectedLng = selectedLng;
	}
}