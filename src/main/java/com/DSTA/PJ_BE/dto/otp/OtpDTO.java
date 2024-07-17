package com.DSTA.PJ_BE.dto.otp;

public class OtpDTO {
    private String otp;
	
	private Long accountId;

	public OtpDTO() {
	}

	public OtpDTO(String otp, Long accountId) {
		super();
		this.otp = otp;
		this.accountId = accountId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
