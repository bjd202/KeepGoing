package com.tje.model;

import java.util.Date;

public class DetailBoardReviewView {
	private int board_id;
	private int category;
	private String title;
	private int comment_cnt;
	private String image;
	private String content;
	private String member_id;
	private String nickname;
	private int view_cnt;
	private int like_cnt;
	private int dislike_cnt;
	private Date write_date;
	private String selectedAddress;
	private double selectedLat;
	private double selectedLng;
	
	public DetailBoardReviewView() {
	}

	public DetailBoardReviewView(int board_id, int category, String title, int comment_cnt, String image,
			String content, String member_id, String nickname, int view_cnt, int like_cnt, int dislike_cnt,
			Date write_date, String selectedAddress, double selectedLat, double selectedLng) {
		this.board_id = board_id;
		this.category = category;
		this.title = title;
		this.comment_cnt = comment_cnt;
		this.image = image;
		this.content = content;
		this.member_id = member_id;
		this.nickname = nickname;
		this.view_cnt = view_cnt;
		this.like_cnt = like_cnt;
		this.dislike_cnt = dislike_cnt;
		this.write_date = write_date;
		this.selectedAddress = selectedAddress;
		this.selectedLat = selectedLat;
		this.selectedLng = selectedLng;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getComment_cnt() {
		return comment_cnt;
	}

	public void setComment_cnt(int comment_cnt) {
		this.comment_cnt = comment_cnt;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public int getLike_cnt() {
		return like_cnt;
	}

	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}

	public int getDislike_cnt() {
		return dislike_cnt;
	}

	public void setDislike_cnt(int dislike_cnt) {
		this.dislike_cnt = dislike_cnt;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
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
