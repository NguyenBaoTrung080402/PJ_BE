package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.Security.Authorities;
import com.DSTA.PJ_BE.dto.Account.AccountChangePassDto;
import com.DSTA.PJ_BE.dto.Account.AccountInforSendMail;
import com.DSTA.PJ_BE.dto.Account.AccountOtpSendMail;
import com.DSTA.PJ_BE.dto.Account.AccountRegisterDto;
import com.DSTA.PJ_BE.dto.Account.AccountUpdateDto;
import com.DSTA.PJ_BE.dto.otp.OtpDTO;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.entity.Otp;
import com.DSTA.PJ_BE.repository.AccountRepository;
import com.DSTA.PJ_BE.repository.OtpRepository;
import com.DSTA.PJ_BE.service.AccountService;
import com.DSTA.PJ_BE.service.MailService;
import com.DSTA.PJ_BE.service.OtpService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import com.DSTA.PJ_BE.utils.Validate;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
// import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    
    private final Logger log = LoggerFactory.getLogger(AccountServiceImp.class);
    private final Map<String, Account> tempAccounts = new ConcurrentHashMap<>();

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
	private MailService mailService;

    @Autowired
	private OtpService otpService;

    @Override
    public Account getAccountByUsername(String email) {
        log.debug("Get account by user name" + email);
        return accountRepository.getAccountUserName(email);
    }

    @Override
    public Account getAccountById(Long id) {
        log.debug("Get account by id" + id);
        return accountRepository.getAccountId(id);
    }

    @Override
    public Account getAccountLogin() {
        return Common.getCurrentUserLogin();
    }

    @Override
    public DataResponse register(AccountRegisterDto accountRegisterDto) {
        log.debug("Request Register Imp");
        DataResponse res = new DataResponse();
        try {
            Account account = mapper.map(accountRegisterDto, Account.class);
            Account accountCheck = accountRepository.getAccountUserName(account.getEmail());

            if(accountCheck != null){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.REGISTER_MAIL_EXESIT);
                return res;
            }

            account.setAuthority((Authorities.CUSTOMER));
            String password = account.getPassword();
            account.setPassword(passwordEncoder.encode(password));

            String otp = otpService.create(account.getEmail());
            mailService.sendMailOtp(new AccountOtpSendMail(account.getEmail(), account.getName(), otp));

            tempAccounts.put(account.getEmail(), account);

            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.CHECK_YOUR_MAIL);
            res.setResult(account.getEmail());
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Scheduled(cron = "0 0 0 1 * ?")
    @Transactional
    public void scheduleDeleteExpiredOtps() {
        deleteExpiredOtps();
    }

    @Transactional
    public void deleteExpiredOtps() {
        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(3);
        otpRepository.deleteByCreateTimeBefore(expirationTime);
    }

    private DataResponse verifyOtp(OtpDTO optVerify) {
        DataResponse res = new DataResponse();
        try {
            Optional<Otp> latestOtp = otpRepository.findByEmail(optVerify.getEmail());
    
            if (latestOtp.isEmpty()) {
                res.setStatus(Constants.ERROR);
                res.setMessage("OTP has expired");
                otpRepository.deleteByEmail(optVerify.getEmail());
                return res;
            }
        
            Otp storedOtp = latestOtp.get();
            LocalDateTime now = LocalDateTime.now();
            if (storedOtp != null && storedOtp.getOtp().equals(optVerify.getOtp())) {
                if (now.isBefore(storedOtp.getCreateTime().plusMinutes(3))) {
                    res.setStatus(Constants.SUCCESS);
                    otpRepository.deleteByEmail(optVerify.getEmail());
                    res.setMessage("OTP verified successfully");
                }
            } else {
                res.setStatus(Constants.ERROR);
                res.setMessage("Invalid OTP");
                otpRepository.deleteByEmail(optVerify.getEmail());
            }

            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse completeRegistration(OtpDTO optVerify) {
        DataResponse res = new DataResponse();
        
        // Xác minh OTP
        DataResponse otpVerification = verifyOtp(optVerify);
        if (otpVerification.getStatus().equals(Constants.ERROR)) {
            return otpVerification;
        }
        
        // Lấy thông tin tài khoản tạm thời
        Account account = tempAccounts.remove(optVerify.getEmail());
        if (account == null) {
            res.setStatus(Constants.ERROR);
            otpRepository.deleteByEmail(optVerify.getEmail());
            res.setMessage("Registration session expired");
            return res;
        }

        account.setVerified(true);
        accountRepository.save(account);

        mailService.sendMailRegister(new AccountInforSendMail(null, account.getEmail(), account.getName()));

        res.setStatus(Constants.SUCCESS);
        res.setMessage(Constants.REGISTER_SUCCESS);
        res.setResult(account);
        return res;
    }

    // private String getRoleJson(String role) {
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     try {
    //         return objectMapper.writeValueAsString(Collections.singletonList("" + role));
    //     } catch (JsonProcessingException e) {
    //         throw new RuntimeException("Error converting roles to JSON", e);
    //     }
    // }
    @Override
    @Transactional
    public DataResponse updateAccount(String str, MultipartFile file) {
        log.debug("Request Update Account");
        DataResponse res = new DataResponse();
        try {
            Account account = Common.getCurrentUserLogin();
            AccountUpdateDto accountUpdateDto = Common.convertStringToObject(str, AccountUpdateDto.class);
            String date = Common.convertStringDate(accountUpdateDto.getDob(), Constants.YYYY_MM_DD, Constants.YYYYMMDD);
            if(account == null || account.equals("")){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.ACCOUNT_NOT_FOUND);
                return res;
            }
            if(!Validate.validateTel(accountUpdateDto.getTel())){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.UPDATE_FAIL);
                return res;
            }
            if(file != null && !file.isEmpty()){
                String avatarUrl = Constants.AVATAR_SAVE + account.getId() + "/" + Common.currentDate() + "/";
                String image = Common.saveFile(file, avatarUrl, account.getId(), account.getName());
                if(image != null){
                    account.setAvatar(image);
                }
            }else {
                account.setAvatar(account.getAvatar());
            }
            account.setName(accountUpdateDto.getName());
            account.setDob(date);
            account.setAddress(accountUpdateDto.getAddress());
            account.setGender(accountUpdateDto.getGender());
            account.setTel(accountUpdateDto.getTel());

            accountRepository.save(account);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.UPDATE_ACCOUNT_SUCCESS);
            res.setResult(account);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }


    @Override
    public DataResponse deleteUser(Long id) {
        log.debug("Request Delete User");
        DataResponse res = new DataResponse();
        try {
            Account account = accountRepository.getAccountId(id);
            if(account == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.ACCOUNT_NOT_FOUND);
                return res;
            }

            String imgPath = Constants.AVATAR_SAVE + account.getId();
            accountRepository.delete(account);
            Common.deleteImageFolder(imgPath);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.DELETE_SUCCESS);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    @Transactional
    public DataResponse getAllUser(Pageable pageable) {
        log.debug("Request Get All User___BUG: ");
        DataResponse res = new DataResponse();
        try {
            Page<Account> user = accountRepository.getUser(pageable);

            if(!user.hasContent()){
                res.setResult(Constants.NOT_FOUND);
                res.setMessage(Constants.ACCOUNT_NOT_FOUND);
                return res;
            }

            res.setStatus(Constants.SUCCESS);
            res.setResult(user);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse changePass(AccountChangePassDto accountChangePassDto) {
        log.debug("Request Change Pass___BUG: ");
        DataResponse res = new DataResponse();
        try {
            Account account = this.getAccountLogin();
            if(accountChangePassDto.getNewPass().length()<3){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.NEW_PASSWORD_ERROR);
                return res;
            }
            if(!passwordEncoder.matches(accountChangePassDto.getOldPass(), account.getPassword())){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.OLD_PASSWORD_ERROR);
                return res;
            }
            account.setPassword(passwordEncoder.encode(accountChangePassDto.getNewPass()));
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.CHANGE_PASSWORD_SUCCESS);
            accountRepository.save(account);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse updateAdmin(Long id) {
        log.debug("Request update admin");
        DataResponse res = new DataResponse();
        try {
            Account account = accountRepository.getAccountId(id);
            if(account == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.ACCOUNT_NOT_FOUND);
                return res;
            }
            if(account.getAuthority().equals(Authorities.CUSTOMER)){
                account.setAuthority(Authorities.ADMIN);
            }else{
                account.setAuthority(Authorities.CUSTOMER);
            }
            accountRepository.save(account);
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.UPDATE_SUCCESS);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse getCurrentAccountLogin() {
        log.debug("Request Get Current Account Login");
        DataResponse res = new DataResponse();
        try {
            Account account = accountRepository.getAccountUserName(Common.getCurrentUserLogin().getEmail());
            AccountUpdateDto getAccount = mapper.map(account, AccountUpdateDto.class);
            res.setStatus(Constants.SUCCESS);
            res.setResult(getAccount);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse forgotPassword(String email) {
        log.debug("Request Get Email Forgot Password");
        DataResponse res = new DataResponse();
        try {
            Account account = accountRepository.getAccountUserName(email);
            if(account == null){
                res.setStatus(Constants.NOT_FOUND);
                res.setMessage(Constants.ACCOUNT_NOT_FOUND);
                return res;
            }

            String otp = otpService.create(account.getEmail());
            mailService.sendMailOtp(new AccountOtpSendMail(account.getEmail(), account.getName(), otp));
            
            res.setStatus(Constants.SUCCESS);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }

    @Override
    public DataResponse verifyOtpForgotPassword(OtpDTO forgotPass) {
        DataResponse res = new DataResponse();
        try {
            // Xác minh OTP
            DataResponse otpVerification = verifyOtp(forgotPass);
            if (otpVerification.getStatus().equals(Constants.ERROR)) {
                return otpVerification;
            }
            res.setStatus(Constants.SUCCESS);
            return res;
        } catch (Exception ex) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }
}
