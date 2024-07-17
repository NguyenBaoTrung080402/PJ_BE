package com.DSTA.PJ_BE.dto.Account;

public class AccountOtpSendMail {
	private String email;

	private String name;
	
	private String otp;

	public AccountOtpSendMail(String email, String name, String otp) {
		super();
		this.email = email;
		this.name = name;
		this.otp = otp;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
}
