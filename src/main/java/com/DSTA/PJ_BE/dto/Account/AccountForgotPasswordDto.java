package com.DSTA.PJ_BE.dto.Account;

public class AccountForgotPasswordDto {
    private String email;
    private String otp;
    private String newPassword;
    private String rePassword;
    
    // getters and setters
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
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
