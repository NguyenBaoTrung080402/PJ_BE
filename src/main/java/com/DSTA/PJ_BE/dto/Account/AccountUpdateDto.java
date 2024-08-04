package com.DSTA.PJ_BE.dto.Account;

import java.io.IOException;

import com.DSTA.PJ_BE.utils.Common;

public class AccountUpdateDto {
    private String name;
    private String dob;
    private String tel;
    private String gender;
    private String address;
    private String email;
    private String avatar;
    private String authority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        try {
            this.avatar = Common.convertToBase64(avatar);
        } catch (IOException e) {
            this.avatar = avatar;
        }
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
