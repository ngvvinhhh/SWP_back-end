package vn.vvinh.be.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.LoginRequestDTO;
import vn.vvinh.be.dto.RegisterRequestDTO;
import vn.vvinh.be.dto.UpdateRequestDTO;
import vn.vvinh.be.dto.response.LoginResponse;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.exception.AccountNotFound;
import vn.vvinh.be.repository.AccountRepository;
import vn.vvinh.be.security.TokenHandler;
import vn.vvinh.be.utils.AccountUtils;

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

    @Autowired
    AccountUtils accountUtils;

    public Account register(RegisterRequestDTO requestDTO){
        Account account = new Account();
        account.setAvatar(requestDTO.getAvatar());
        account.setUserName(requestDTO.getUserName());
        account.setPassword(requestDTO.getPassword());
        account.setFullName(requestDTO.getFullName());
        account.setEmail(requestDTO.getEmail());
        account.setGender(requestDTO.getGender());
        account.setPhoneNumber(requestDTO.getPhoneNumber());
        String rawPassword = requestDTO.getPassword();
        account.setPassword(passwordEncoder.encode(rawPassword));
        account.setRole(requestDTO.getRole());
        Account newAccount = accountRepository.save(account);
        return newAccount;
    }

    public LoginResponse login(LoginRequestDTO loginRequestDTO){
        Authentication authentication;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
               loginRequestDTO.getUserName(),
               loginRequestDTO.getPassword()
            ));
            Account loginAccount = (Account) authentication.getPrincipal();
            // dang nhap thanh cong

            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(loginAccount.getId());
            loginResponse.setFullname(loginAccount.getFullName());
            loginResponse.setUsername(loginAccount.getUsername());
            loginResponse.setToken(tokenHandler.generateToken(loginAccount));
            loginResponse.setAvatar(loginAccount.getAvatar());
            return loginResponse;
        } catch (Exception e){
            e.printStackTrace();
            throw new AccountNotFound("Invalid Account");
        }
    }


    public Account updateProfile(UpdateRequestDTO updateRequestDTO){
        Account account = accountUtils.getCurrentAccount();
        account.setAvatar(updateRequestDTO.getAvatar());
        account.setEmail(updateRequestDTO.getEmail());
        account.setPhoneNumber(updateRequestDTO.getPhoneNumber());
        account.setGender(updateRequestDTO.getGender());
        account.setFullName(updateRequestDTO.getFullName());

        return accountRepository.save(account);
    }

        public Account deleteProfile(long id){

            Account account = accountRepository.findAccountById(id);
            account.setDeleted(true);
            return  accountRepository.save(account);

           //return   accountRepository.delete(account);

        }
}
