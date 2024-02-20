package vn.blearning.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPi {
    @GetMapping("/api/test")
    public ResponseEntity TestAPi() {
        return ResponseEntity.ok("api ok");
    }
}