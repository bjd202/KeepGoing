package com.tje.model;

public class LikeAndDislike {
	public int like_id;
	public String member_id;
	public int board_id;
	public int topic;
	public int is_like;
	
	public LikeAndDislike() {}

	public LikeAndDislike(int like_id, String member_id, int board_id, int topic, int is_like) {
		this.like_id = like_id;
		this.member_id = member_id;
		this.board_id = board_id;
		this.topic = topic;
		this.is_like = is_like;
	}

	public int getLike_id() {
		return like_id;
	}

	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
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

	public int getIs_like() {
		return is_like;
	}

	public void setIs_like(int is_like) {
		this.is_like = is_like;
	}
	
}
