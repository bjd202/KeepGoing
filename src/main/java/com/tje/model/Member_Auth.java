package com.tje.model;

public class Member_Auth {
	private String member_id;
	private String name;
	private String nickname;
	private int cur_auth;
	private int req_auth;
	private int member_type;
	
	public Member_Auth() {
		// TODO Auto-generated constructor stub
	}

	public Member_Auth(String member_id, String name, String nickname, int cur_auth, int req_auth, int member_type) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.nickname = nickname;
		this.cur_auth = cur_auth;
		this.req_auth = req_auth;
		this.member_type = member_type;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCur_auth() {
		return cur_auth;
	}
	
	public String getCur_authString() {
		
		if(cur_auth==0) 
			return "일반회원";
		else if(cur_auth==1)
			return "리뷰어";
		else if(cur_auth==2)
			return "판매자";
		else if(cur_auth==3)
			return "매니저";
		else
			return "관리자";	
	}

	public void setCur_auth(int cur_auth) {
		this.cur_auth = cur_auth;
	}

	public int getReq_auth() {
		return req_auth;
	}
	
	public String getReq_authString() {
		
		if(req_auth==0) 
			return "일반회원";
		else if(req_auth==1)
			return "리뷰어";
		else if(req_auth==2)
			return "판매자";
		else if(req_auth==3)
			return "매니저";
		else
			return "관리자";	
	}

	public void setReq_auth(int req_auth) {
		this.req_auth = req_auth;
	}

	public int getMember_type() {
		return member_type;
	}
	
	public String getMember_typeString() {
		if(member_type==0) 
			return "일반";
		else if(member_type==1)
			return "네이버";
		else if(member_type==2)
			return "구글";
		else
			return "카카오";
	}

	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}
	
	
}
