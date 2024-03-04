package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.LoginGoogleRequest;
import vn.vvinh.be.dto.LoginRequestDTO;
import vn.vvinh.be.dto.RegisterRequestDTO;
import vn.vvinh.be.dto.UpdateRequestDTO;
import vn.vvinh.be.dto.response.LoginResponse;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.service.AuthenticationService;
import vn.vvinh.be.service.EmailService;
import vn.vvinh.be.utils.AccountUtils;



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

    @GetMapping("profile/getProfile")
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

//    @GetMapping("/testSendMail")
//    public ResponseEntity testSendMail(){
//        service.sendMailTemplate(registerRequestDTO);
//        return  ResponseEntity.ok("success");
//    }

}
