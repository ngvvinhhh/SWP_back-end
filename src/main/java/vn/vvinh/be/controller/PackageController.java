package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.entity.Account;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.service.PackageService;

@SecurityRequirement(name = "api")
@RestController
@CrossOrigin("*")
public class PackageController {
    @Autowired
    PackageService packageService;
    @PostMapping("product")
    public ResponseEntity<Package> postProduct(@RequestBody Package packages){
        Package newPackage = packageService.postProduct(packages);
        return ResponseEntity.ok(newPackage);
    }
}
