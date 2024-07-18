package com.DSTA.PJ_BE.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otp")
public class Otp {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "account_id", columnDefinition = "BIGINT")
    private Long accountId;
	
    @Column(name = "email", columnDefinition = "VARCHAR(20)")
    private String email;

	@Column(name = "otp", columnDefinition = "VARCHAR(20)", nullable = false)
	private String otp;
	
	@Column(name = "create_time", columnDefinition = "VARCHAR(50)", nullable = false)
    private LocalDateTime createTime;

	public Otp() {
	}

	public Otp(LocalDateTime createTime, String email, String otp) {
		super();
        this.email = email;
		this.otp = otp;
		this.createTime = createTime;
	}

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
}
