package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.*;
import vn.vvinh.be.entity.Service;
import vn.vvinh.be.service.ServiceServices;

import java.util.List;

@SecurityRequirement(name = "api")
@RestController
@CrossOrigin("*")
public class ServiceController {
    @Autowired
    ServiceServices servicesss;
    @GetMapping("service")
    public ResponseEntity getServicesByPackages(PackageRequestIdForServiceDTO packageRequestIdForServiceDTO){
        List<Service> serviceList = servicesss.getServiceByPackage(packageRequestIdForServiceDTO);
        return ResponseEntity.ok(serviceList);}

    @PostMapping("service")
    public ResponseEntity createService(@RequestBody ServiceRequestDTO serviceRequestDTO){
        Service service = servicesss.createService(serviceRequestDTO);
        return  ResponseEntity.ok(service);
    }

    @PutMapping("service/{id}")
    public ResponseEntity updatePackage(@RequestBody UpdateServiceDTO updateServiceDTO, @PathVariable long id){
        return ResponseEntity.ok(servicesss.updateService(updateServiceDTO, id));
    }

    @DeleteMapping("service/{id}")
    public ResponseEntity deleteService(@PathVariable long id){return ResponseEntity.ok(servicesss.deleteService(id));}

}
