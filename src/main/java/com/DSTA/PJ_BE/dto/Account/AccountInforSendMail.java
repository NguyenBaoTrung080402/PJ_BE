package com.DSTA.PJ_BE.dto.Account;

public class AccountInforSendMail {
	
    private String email;
    private String name;
    private String password;

	public AccountInforSendMail(String password, String email, String name) {
		super();
		this.password = password;
		this.email = email;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}