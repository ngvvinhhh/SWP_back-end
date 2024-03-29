package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.*;
import vn.vvinh.be.dto.response.LoginResponse;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.service.AuthenticationService;
import vn.vvinh.be.service.EmailService;
import vn.vvinh.be.utils.AccountUtils;

import java.util.List;


@SecurityRequirement(name = "api")
@RestController
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    AccountUtils accountUtils;
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/authentication/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO account){
        Account newAccount = authenticationService.register(account);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/authentication/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO account){
        LoginResponse newAccount = authenticationService.login(account);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/authentication/logingg")
    public ResponseEntity logingg(@RequestBody LoginGoogleRequest account){
        LoginResponse  newAccount = authenticationService.logingg(account);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("profile/getCurrentProfile")
    public ResponseEntity getProfile(){
        return ResponseEntity.ok(accountUtils.getCurrentAccount());
    }

    @PutMapping("profile/updateProfile")
    public ResponseEntity updateProfile(@RequestBody UpdateRequestDTO account){
        return ResponseEntity.ok(authenticationService.updateProfile(account));
    }

    @DeleteMapping("profile/deleteProfile/{id}")
    public ResponseEntity deleteProfile(@PathVariable long id){
        return  ResponseEntity.ok(authenticationService.deleteProfile(id));
    }

    @GetMapping("verify")
    public ResponseEntity verify(@RequestParam String token){
        authenticationService.verify(token);
        return ResponseEntity.ok("Confirm email success!");
    }

    @GetMapping("profile/getHost")
    public  ResponseEntity<List<Account>> listHost(){
        return  ResponseEntity.ok(authenticationService.getAllAccountHost());

    }

    @GetMapping("profile/getAllAccount")
    public  ResponseEntity<List<Account>> listAccount(){
        return  ResponseEntity.ok(authenticationService.getAllAccount());

    }
}
