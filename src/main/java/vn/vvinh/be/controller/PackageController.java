package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.PackageRequestDTO;
import vn.vvinh.be.dto.UpdatePackageDTO;
import vn.vvinh.be.entity.Package;
import vn.vvinh.be.service.PackageService;

@SecurityRequirement(name = "api")
@RestController
@CrossOrigin("*")
public class PackageController {
    @Autowired
    PackageService packageService;
    @PostMapping("package")
    public ResponseEntity<Package> postPackage(@RequestBody PackageRequestDTO packages){
        Package newPackage = packageService.postProduct(packages);
        return ResponseEntity.ok(newPackage);
    }


    @GetMapping("package")
    public ResponseEntity getPackages(){
        return ResponseEntity.ok(packageService.getPackageByAccount());
    }

//    @PutMapping("package")
//    public ResponseEntity updatePackage(@RequestBody UpdatePackageDTO packageDTO, @PathVariable Long Id){
//        return ResponseEntity.ok(packageService.updateAccount(packageDTO, Id));
//    }
}
