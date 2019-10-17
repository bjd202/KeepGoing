package com.tje.model;

public class Chat_room {
	public int room_id;
	public String room;
	
	public Chat_room() {}

	public Chat_room(int room_id, String room) {
		this.room_id = room_id;
		this.room = room;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
}
