package com.DSTA.PJ_BE.dto.otp;

public class OtpDTO {
    private String otp;
	
	private String email;

	private String newPassword;

    private String rePassword;
	
	public OtpDTO() {
	}

	public OtpDTO(String otp, String email) {
		super();
		this.otp = otp;
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getRePassword() {
        return rePassword;
    }
    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
