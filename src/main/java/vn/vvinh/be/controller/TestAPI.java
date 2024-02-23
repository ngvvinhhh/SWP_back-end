package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vvinh.be.utils.AccountUtils;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class TestAPI {

    @Autowired
    AccountUtils accountUtils;

    @GetMapping("test")
    public ResponseEntity Test(){
        return ResponseEntity.ok(accountUtils.getCurrentAccount());
    }
}
