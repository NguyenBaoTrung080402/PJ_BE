package com.DSTA.PJ_BE.service;

import com.DSTA.PJ_BE.dto.Account.AccountChangePassDto;
import com.DSTA.PJ_BE.dto.Account.AccountRegisterDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface AccountService {
    Account getAccountByUsername(String email);

    Account getAccountById(Long id);

    Account getAccountLogin();

    DataResponse register(AccountRegisterDto accountRegisterDto);


    DataResponse updateAccount(String str, MultipartFile file);

    DataResponse deleteUser(Long id);

    DataResponse getAllUser(Pageable pageable);

    DataResponse changePass(AccountChangePassDto accountChangePassDto);

    DataResponse updateAdmin(Long id);
}
