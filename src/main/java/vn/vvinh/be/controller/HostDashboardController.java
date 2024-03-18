package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.AdminDashboardDTO;
import vn.vvinh.be.service.AdminDashboardService;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("/dashboard/host")
public class HostDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/detail{id}")
    public AdminDashboardDTO getChartDetail(@PathVariable long id){
        AdminDashboardDTO adminDashboardDTO = adminDashboardService.getDetail();
        return adminDashboardDTO;
}
}
