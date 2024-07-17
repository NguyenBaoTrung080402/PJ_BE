package com.DSTA.PJ_BE.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DSTA.PJ_BE.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
    @Query(value = "SELECT otp FROM Otp otp WHERE otp.accountId = :accountId AND otp.otp = :otp")
	Otp getOtpByAccountIdAndOtp(@Param("accountId") Long accountId, @Param("otp") String otp);

    @Query(value = "SELECT otp FROM Otp otp WHERE otp.accountId = :accountId AND otp.otp = :otp")
	Otp findByEmail(@Param("email") String email);
}
