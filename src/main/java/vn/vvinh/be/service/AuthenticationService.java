package vn.vvinh.be.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.vvinh.be.dto.*;
import vn.vvinh.be.dto.response.LoginResponse;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.enums.Role;
import vn.vvinh.be.exception.AccountNotFound;
import vn.vvinh.be.repository.AccountRepository;
import vn.vvinh.be.security.TokenHandler;
import vn.vvinh.be.utils.AccountUtils;

import java.util.List;

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

    @Autowired
    EmailService emailService;

    public Account register(RegisterRequestDTO requestDTO) {
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
        emailService.sendMailTemplate(newAccount);
        return newAccount;
    }

    public void verify(String token) {
        try {
            String username = tokenHandler.getInfoByToken(token);
            // => token chuan
            Account account = accountRepository.findByUserName(username).get();
            account.setVerify(true);
            accountRepository.save(account);
        } catch (Exception e) {
            throw new AccountNotFound("Invalid account");
        }
    }

    public LoginResponse login(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        Account loginAccount = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUserName(),
                    loginRequestDTO.getPassword()
            ));
            loginAccount = (Account) authentication.getPrincipal();
            // dang nhap thanh cong
        } catch (Exception e) {
            e.printStackTrace();
            throw new AccountNotFound("Invalid Account");

        }

        if (loginAccount.isVerify() && !loginAccount.isDeleted()) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(loginAccount.getId());
            loginResponse.setFullname(loginAccount.getFullName());
            loginResponse.setUsername(loginAccount.getUsername());
            loginResponse.setToken(tokenHandler.generateToken(loginAccount));
            loginResponse.setAvatar(loginAccount.getAvatar());
            loginResponse.setRole(loginAccount.getRole());

            return loginResponse;
        }else {
            throw new AccountNotFound("Account does not exist or the email has not been comfirmed!!");
        }
        //return  null;

    }


    public LoginResponse logingg(LoginGoogleRequest loginGoogleRequest) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(loginGoogleRequest.getToken());
            String email = decodedToken.getEmail();
            Account account = accountRepository.findAccountByEmail(email);
            if (account == null) {
                throw new AccountNotFound("Cannot find by email");
            }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setId(account.getId());
            loginResponse.setFullname(account.getFullName());
            loginResponse.setUsername(account.getUsername());
            loginResponse.setToken(tokenHandler.generateToken(account));
            loginResponse.setAvatar(account.getAvatar());
            loginResponse.setRole(account.getRole());
            return loginResponse;
        } catch (FirebaseAuthException e) {
            //e.printStackTrace();
            System.out.println(e);
        }
        return null;
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

    public List<Account> getAllAccountHost() {
        return accountRepository.getAllAccountByRole(Role.HOST);
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }
}


