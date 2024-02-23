package vn.vvinh.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vvinh.be.dto.LoginRequestDTO;
import vn.vvinh.be.dto.RegisterRequestDTO;
import vn.vvinh.be.dto.response.LoginResponse;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.service.AuthenticationService;

@RestController
@CrossOrigin("*")
public class AuthenticationController {

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

//    @PostMapping("/authentication/logingg")
//    public ResponseEntity logingg(@RequestBody ){
//
//    }


}
