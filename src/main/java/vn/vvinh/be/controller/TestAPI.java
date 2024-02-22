package vn.vvinh.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class TestAPI {
    @GetMapping("test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity Test(){
        return ResponseEntity.ok("ok");
    }
}
