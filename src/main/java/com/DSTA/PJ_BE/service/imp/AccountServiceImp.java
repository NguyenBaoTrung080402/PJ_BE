package com.DSTA.PJ_BE.service.imp;

import com.DSTA.PJ_BE.Security.Authorities;
import com.DSTA.PJ_BE.dto.Account.AccountChangePassDto;
import com.DSTA.PJ_BE.dto.Account.AccountRegisterDto;
import com.DSTA.PJ_BE.dto.Account.AccountUpdateDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.repository.AccountRepository;
import com.DSTA.PJ_BE.service.AccountService;
import com.DSTA.PJ_BE.utils.Common;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import com.DSTA.PJ_BE.utils.Validate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.twilio.base.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountServiceImp.class);
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper mapper;

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

            if(!Validate.validateEmail(account.getEmail())){
                res.setStatus(Constants.ERROR);
                res.setMessage(Constants.REGISTER_FAIL);
                return res;
            }
            account.setAuthority(getRoleJson(Authorities.CUSTOMER));
            String password = account.getPassword();
            account.setPassword(passwordEncoder.encode(password));
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.REGISTER_SUCCESS);
            res.setResult(account);
            accountRepository.save(account);
            return res;
        }catch (Exception ex){
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.SYSTEM_ERROR);
            return res;
        }
    }
    private String getRoleJson(String role) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(Collections.singletonList("" + role));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting roles to JSON", e);
        }
    }
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
            if(file != null || !file.isEmpty()){
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

}
