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
    @PostMapping("package/postNewPackage")
    public ResponseEntity<Package> postPackage(@RequestBody PackageRequestDTO packages){
        Package newPackage = packageService.postParty(packages);
        return ResponseEntity.ok(newPackage);
    }


    @GetMapping("package/getPackages")
    public ResponseEntity getPackages(){
        return ResponseEntity.ok(packageService.getPackageByAccount());
    }

    @PutMapping("package/updatePackage/{id}")
    public ResponseEntity updatePackage(@RequestBody UpdatePackageDTO packageDTO, @PathVariable long id){
        return ResponseEntity.ok(packageService.updatePackage(packageDTO, id));
    }

    @DeleteMapping("package/deletePackage/{id}")
    public ResponseEntity deletePackages(@PathVariable long id){return ResponseEntity.ok(packageService.deletePackage(id));}
}
