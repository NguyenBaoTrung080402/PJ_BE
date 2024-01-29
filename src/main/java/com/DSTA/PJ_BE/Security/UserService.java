package com.DSTA.PJ_BE.Security;


import com.DSTA.PJ_BE.entity.Account;
import com.DSTA.PJ_BE.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Lazy
	@Autowired
	private AccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountService.getAccountByUsername(email);
		if (account == null) {
            throw new UsernameNotFoundException(email);
        }
        return new CustomUserDetails(account);
	}
	
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		Account account = accountService.getAccountById(id);
		if (account == null) {
            throw new UsernameNotFoundException(String.valueOf(id));
        }
        return new CustomUserDetails(account);
	}
}
