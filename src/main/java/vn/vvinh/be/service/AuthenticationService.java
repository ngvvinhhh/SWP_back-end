package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.RegisterRequestDTO;
import vn.vvinh.be.dto.response.LoginResponse;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.repository.AccountRepository;
import vn.vvinh.be.security.TokenHandler;

@Service
public class AuthenticationService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenHandler tokenHandler;

    public Account register(RegisterRequestDTO requestDTO){
        Account account = new Account();
        account.setUserName(requestDTO.getUserName());
        account.setPassword(requestDTO.getPassword());
        account.setFullName(requestDTO.getFullName());
        account.setEmail(requestDTO.getEmail());
        account.setGender(requestDTO.getGender());
        String rawPassword = account.getPassword();
        account.setPassword(passwordEncoder.encode(rawPassword));
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    public LoginResponse login(Account account){
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               account.getUsername(),
               account.getPassword()
            ));
            Account loginAccount = (Account) authentication.getPrincipal();
            // dang nhap thanh cong

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(loginAccount.getId());
            loginResponse.setFullname(loginAccount.getFullName());
            loginResponse.setUsername(loginAccount.getUsername());
            loginResponse.setToken(tokenHandler.generateToken(loginAccount));
            return loginResponse;
        } catch (Exception e){
            // sai tk mk
            return null;
        }
    }

}
