package com.zephyr.connector;

public class JwtResponse {
	private String finalApi;
	private String jwtToken;
	
	public String getApi() {
		return finalApi;
	}
	public void setApi(String api) {
		this.finalApi = api;
	}
	public String getToken() {
		return jwtToken;
	}
	public void setToken(String token) {
		this.jwtToken = token;
	}
}
