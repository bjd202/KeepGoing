package com.tje.model;

import java.util.Date;

public class SimpleBoardFreeView {
	private int board_id;
	private int category;
	private String title;
	private int comment_cnt;
	private String member_id;
	private String nickname;
	private int view_cnt;
	private int like_cnt;
	private int dislike_cnt;
	private Date write_date;

	public SimpleBoardFreeView() {
	}
	
	public SimpleBoardFreeView(int board_id, int category, String title, int comment_cnt, String member_id,
			String nickname, int view_cnt, int like_cnt, int dislike_cnt, Date write_date) {
		this.board_id = board_id;
		this.category = category;
		this.title = title;
		this.comment_cnt = comment_cnt;
		this.member_id = member_id;
		this.nickname = nickname;
		this.view_cnt = view_cnt;
		this.like_cnt = like_cnt;
		this.dislike_cnt = dislike_cnt;
		this.write_date = write_date;
	}

	public String getCategoryString() {
		
		if( category == 1) {
			return "전체 게시글";
		}else if (category == 2){
			return "우리동네 운동부";
		}else if (category == 3){
			return "건강한 식생활";
		}else if (category == 4){
			return "나만의 운동법";
		}else if (category == 5){
			return "초보자를 위한 운동 추천";
		}else{
			return "컴플랙스 극복";
		}
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
}
