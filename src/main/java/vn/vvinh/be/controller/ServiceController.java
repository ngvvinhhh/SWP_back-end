package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.*;
import vn.vvinh.be.entity.Service;
import vn.vvinh.be.service.ServiceServices;

import java.util.ArrayList;
import java.util.List;

@SecurityRequirement(name = "api")
@RestController
@CrossOrigin("*")
public class ServiceController {
    @Autowired
    ServiceServices servicesss;
    @GetMapping("service/getService/{id}")
    public ResponseEntity getServicesByPackages(@PathVariable long id){
        List<Service> services = servicesss.getServicesByPackage(id);

        return ResponseEntity.ok(services);}

    @GetMapping("service/getService/host/{id}")
    public ResponseEntity getPackagesByHostId(@PathVariable int id){
        return ResponseEntity.ok(servicesss.getPackageByHostId(id));
    }

    @PostMapping("service/createService")
    public ResponseEntity createService(@RequestBody ServiceRequestDTO serviceRequestDTO){
        Service service = servicesss.createService(serviceRequestDTO);
        return  ResponseEntity.ok(service);
    }

    @PutMapping("service/updateService/{id}")
    public ResponseEntity updateService(@RequestBody UpdateServiceDTO updateServiceDTO, @PathVariable long id){
        return ResponseEntity.ok(servicesss.updateService(updateServiceDTO, id));
    }

    @DeleteMapping("service/deleteService/{id}")
    public ResponseEntity deleteService(@PathVariable long id){return ResponseEntity.ok(servicesss.deleteService(id));}

}
