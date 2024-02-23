package vn.vvinh.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.vvinh.be.entity.Package;
@RestController
public class AddProductController {
    @PostMapping("/partyManagement/CreateParty")
    public ResponseEntity<Package> product(@RequestBody Package aPackage){
        return  ResponseEntity.ok(aPackage);
    }
}

