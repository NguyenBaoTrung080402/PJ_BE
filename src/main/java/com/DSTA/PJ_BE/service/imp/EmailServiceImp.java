package com.DSTA.PJ_BE.service.imp;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.DSTA.PJ_BE.dto.Account.AccountInforSendMail;
import com.DSTA.PJ_BE.dto.Account.AccountOtpSendMail;
import com.DSTA.PJ_BE.service.MailService;
import com.DSTA.PJ_BE.utils.Constants;

@Service
@Transactional
public class EmailServiceImp implements MailService{
    private final Logger log = LoggerFactory.getLogger(EmailServiceImp.class);

	private final SpringTemplateEngine templateEngine;

	private final JavaMailSender javaMailSender;

    public EmailServiceImp(SpringTemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Async
	@Override
	public void sendMailRegister(AccountInforSendMail account) {
		log.debug("Request send mail register to: " + account.getEmail());
		Locale locale = Locale.forLanguageTag("vn");
		Context context = new Context(locale);
		context.setVariable(Constants.ACCOUNT, account);
		String content = templateEngine.process("email/register-account", context);
		this.sendEmail(Constants.SUBJECT_CREATE_ACCOUNT, content, account.getEmail());
	}

	@Async
	@Override
	public void sendMailOtp(AccountOtpSendMail account) {
		log.debug("Request send mail register to: " + account.getEmail());
		Locale locale = Locale.forLanguageTag("vn");
		Context context = new Context(locale);
		context.setVariable(Constants.ACCOUNT, account);
		String content = templateEngine.process("email/otp", context);
		this.sendEmail(Constants.SUBJECT_OTP, content, account.getEmail());
	}

	@Async
	@Override
	public void sendMailSuccessOtp(AccountInforSendMail account){
		log.debug("Request send mail success otp to: " + account.getEmail());
		Locale locale = Locale.forLanguageTag("vn");
		Context context = new Context(locale);
		context.setVariable(Constants.ACCOUNT, account);
		String content = templateEngine.process("email/otp-success", context);
		this.sendEmail(Constants.SUCCESS_OTP_ACCOUNT, content, account.getEmail());
	}

    @Async
	void sendEmail(String subject, String content, String email) {
		log.debug("Send email to '{}' with subject '{}' and content={}", email, subject, content);
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
			message.setTo(email);
			message.setSubject(subject);
			message.setText(content, true);
			javaMailSender.send(mimeMessage);
			log.debug("Sent email to: " + email);
		} catch (MailException | MessagingException e) {
			log.debug("Fail to send mail to: " + email);
		}
	}
}
