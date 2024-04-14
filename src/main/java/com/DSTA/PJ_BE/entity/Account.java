package com.DSTA.PJ_BE.entity;

import com.DSTA.PJ_BE.utils.Common;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "user")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", columnDefinition = "VARCHAR(20)", nullable = false)
    private String name;
    @Column(name = "email", columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    private String email;
    @Column(name = "password", columnDefinition = "VARCHAR(200)", nullable = false)
    private String password;
    @Column(name = "address", columnDefinition = "VARCHAR(50)", nullable = true)
    private String address;
    @Column(name = "authority", columnDefinition = "VARCHAR(50)", nullable = false)
    private String authority;
    @Column(name = "tel", columnDefinition = "VARCHAR(10)")
    private String tel;
    @Column(name = "dob", columnDefinition = "VARCHAR(20)")
    private String dob;
    @Column(name = "gender", columnDefinition = "VARCHAR(1) DEFAULT 'M'", nullable = true)
    private String gender;
    @Column(name = "avatar", columnDefinition = "VARCHAR(200)", nullable = true)
    private String avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
//        try {
//            this.avatar = Common.convertToBase64(avatar);
//        } catch (IOException e) {
//            this.avatar = avatar;
//        }
    }
}
