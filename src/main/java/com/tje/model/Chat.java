package com.tje.model;

public class Chat {
	public int chat_id;
	public int chat_type;
	public String member_id;
	public int room_id;
	public String log_path;
	
	public Chat() {}

	public Chat(int chat_id, int chat_type, String member_id, int room_id, String log_path) {
		this.chat_id = chat_id;
		this.chat_type = chat_type;
		this.member_id = member_id;
		this.room_id = room_id;
		this.log_path = log_path;
	}

	public int getChat_id() {
		return chat_id;
	}

	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}

	public int getChat_type() {
		return chat_type;
	}

	public void setChat_type(int chat_type) {
		this.chat_type = chat_type;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getLog_path() {
		return log_path;
	}

	public void setLog_path(String log_path) {
		this.log_path = log_path;
	}
	
	
	
}
