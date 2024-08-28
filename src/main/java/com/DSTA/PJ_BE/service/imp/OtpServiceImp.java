package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.dto.otp.OtpDTO;
import com.DSTA.PJ_BE.entity.Otp;
import com.DSTA.PJ_BE.repository.OtpRepository;
import com.DSTA.PJ_BE.service.OtpService;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;

import java.security.SecureRandom;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OtpServiceImp implements OtpService{

    private final Logger log = LoggerFactory.getLogger(OtpServiceImp.class);

	@Autowired
	OtpRepository otpRepository;

	@Autowired
	PasswordEncoder passwordEncoder;


    @Override
	public String create(String email) {
		log.debug("Request to create Random Otp");
		String number = "0123456789";
		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			sb.append(number.charAt(rnd.nextInt(number.length())));
		}
		String strOtp = sb.toString();
        LocalDateTime createdAt = LocalDateTime.now();
		Otp otp = new Otp(createdAt, email, strOtp );
		otpRepository.save(otp);
		return strOtp;
	}

	@Override
	public DataResponse check(OtpDTO otpCheck) {
		log.debug("Request to check Otp");
		DataResponse res = new DataResponse();
		if(otpCheck == null) {
			res.setStatus(Constants.ERROR);
			res.setMessage(Constants.ERROR_OTP);
			return res;
		}
		try{
			// Otp otpDb = otpRepository.getOtpByAccountIdAndOtp(otpCheck.getAccountId(), otpCheck.getOtp());
			// if (otpDb != null) {
			// 	Date current = Common.getCurrentDateTime();
			// 	Date createTime = Common.getDateTime(otpDb.getCreateTime());
			// 	// 180000 : 3 phút
			// 	if ((current.getTime() - createTime.getTime()) <= 180000) {
			// 		String password = Characters.getStringRamdom();
			// 		Account account = accountRepository.getAccountId(otpCheck.getAccountId());
			// 		account.setPassword(passwordEncoder.encode(password));
			// 		accountRepository.save(account);
			// 		mailService.sendMailSuccessOtp(
			// 				new AccountInforSendMail(password, account.getEmail(), account.getName()));
			// 		res.setStatus(Constants.SUCCESS);
			// 		res.setMessage(Constants.SUCCESS_OTP);
			// 		return res;
			// 	}
			// }
			res.setStatus(Constants.ERROR);
			res.setMessage(Constants.ERROR_OTP);
			return res;
		}catch (Exception e){
			res.setStatus(Constants.ERROR);
			return res;
		}

	}
}
