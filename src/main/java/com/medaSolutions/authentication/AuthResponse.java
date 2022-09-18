package com.medaSolutions.authentication;

public class AuthResponse {

	private String cin;
	
	private String accessToken;

	public AuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String cin, String accessToken) {
		super();
		this.cin = cin;
		this.accessToken = accessToken;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
