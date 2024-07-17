package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.Account.AccountInforSendMail;
import com.DSTA.PJ_BE.dto.Account.AccountOtpSendMail;

public interface MailService {
    	public void sendMailRegister(AccountInforSendMail account);

	    public void sendMailOtp(AccountOtpSendMail account);

        public void sendMailSuccessOtp(AccountInforSendMail account);
}
