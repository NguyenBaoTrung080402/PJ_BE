package com.DSTA.PJ_BE.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.DSTA.PJ_BE.entity.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{
    @Query(value = "SELECT otp FROM Otp otp WHERE otp.accountId = :accountId AND otp.otp = :otp")
	Otp getOtpByAccountIdAndOtp(@Param("accountId") Long accountId, @Param("otp") String otp);

    @Query("SELECT o FROM Otp o WHERE o.email = :email ORDER BY o.createTime DESC")
    Optional<Otp> findByEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM Otp o WHERE o.email = :email")
    void deleteByEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM Otp o WHERE o.createTime < :expirationTime")
    void deleteByCreateTimeBefore(@Param("expirationTime") LocalDateTime expirationTime);
}
