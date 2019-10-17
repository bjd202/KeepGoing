package com.tje.api;

public class Auth {
	private String accToken;
	private String refToken;

	public Auth() {
	}
	
	public Auth(String accToken, String refToken) {
		this.accToken = accToken;
		this.refToken = refToken;
	}
	public String getAccToken() {
		return accToken;
	}
	public void setAccToken(String accToken) {
		this.accToken = accToken;
	}
	public String getRefToken() {
		return refToken;
	}
	public void setRefToken(String refToken) {
		this.refToken = refToken;
	}
	
	
}
