package com.DSTA.PJ_BE.Security;

import com.DSTA.PJ_BE.entity.Account;

public class LoginResponse {

	private final String token;

//	private final String username;
//
//	private final String name;
//
//	private final String authority;
	private Account account;
	public LoginResponse(String token, Account account) {
		this.token = token;
//		this.username = username;
//		this.name = name;
//		this.authority = authority;
		this.account = account;
	}

	public Account getAccount(){
		return account;
	}
	public String getToken() {
		return token;
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public String getAuthority() {
//		return authority;
//	}
}
