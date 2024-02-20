package vn.vvinh.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vvinh.be.entity.Product;
@RestController
public class AddProductController {
    @PostMapping("/partyManagement/CreateParty")
    public ResponseEntity<Product> product(@RequestBody Product product){
        return  ResponseEntity.ok(product);
    }
}

