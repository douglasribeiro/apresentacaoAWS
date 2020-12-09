package com.apresentacaoaws.dto;

import java.io.Serializable;

public class UserLoginResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String token;
	private Long expireIn;
	private String tokenProvider;
	
	public UserLoginResponseDto(String token, Long expireIn, String tokenProvider) {
		super();
		this.token = token;
		this.expireIn = expireIn;
		this.tokenProvider = tokenProvider;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(Long expireIn) {
		this.expireIn = expireIn;
	}
	public String getTokenProvider() {
		return tokenProvider;
	}
	public void setTokenProvider(String tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	
}
