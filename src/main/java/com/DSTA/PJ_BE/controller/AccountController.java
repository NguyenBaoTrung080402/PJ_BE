package com.DSTA.PJ_BE.controller;

import com.DSTA.PJ_BE.Security.CustomUserDetails;
import com.DSTA.PJ_BE.Security.JwtTokenProvider;
import com.DSTA.PJ_BE.Security.LoginResponse;
import com.DSTA.PJ_BE.dto.Account.AccountChangePassDto;
import com.DSTA.PJ_BE.dto.Account.AccountLoginDto;
import com.DSTA.PJ_BE.dto.Account.AccountRegisterDto;
import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.service.AccountService;
import com.DSTA.PJ_BE.utils.Constants;
import com.DSTA.PJ_BE.utils.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public DataResponse login(@RequestBody AccountLoginDto accountLogin) throws Exception {
        log.debug("Request login");
        DataResponse res = new DataResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountLogin.getEmail(), accountLogin.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
            res.setStatus(Constants.SUCCESS);
            res.setMessage(Constants.LOGIN_SUCCESS);
            Account account = accountService.getAccountLogin();
            res.setResult(new LoginResponse(jwt, account));
//            res.setResult(account);
            return res;
        } catch (Exception e) {
            res.setStatus(Constants.ERROR);
            res.setMessage(Constants.LOGIN_FAIL);
            return res;
        }

    }
    @PostMapping("/register")
    public DataResponse register(@RequestBody AccountRegisterDto accountRegisterDto) throws Exception{
        log.debug("Request Register");
        DataResponse res = accountService.register(accountRegisterDto);
        return res;
    }

    @PutMapping("/update-account")
    public DataResponse updateUser(MultipartHttpServletRequest data){
        log.debug("Update User Controller");
        MultipartFile file = data.getFile("avatar");
        String str = data.getParameter("user");
        DataResponse res = accountService.updateAccount(str, file);
        return res;
    }

    @DeleteMapping("/delete-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse deleteUser(@PathVariable("id") Long id){
        log.debug("Delete User");
        DataResponse res = accountService.deleteUser(id);
        return res;
    }

    @GetMapping("/get-all-user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public DataResponse getAllUser(@PageableDefault(page = 0, size = 6) Pageable pageable){
        log.debug("Controller Get All User");
        DataResponse res = accountService.getAllUser(pageable);
        return res;
    }
    @PostMapping("change-password")
    public DataResponse changPass (@RequestBody AccountChangePassDto accountChangePassDto){
        log.debug("Controller Change Password");
        DataResponse res = accountService.changePass(accountChangePassDto);
        return res;
    }
}
