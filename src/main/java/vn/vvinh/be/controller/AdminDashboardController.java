package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.vvinh.be.dto.AdminDashboardDTO;
import vn.vvinh.be.service.AdminDashboardService;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("/dashboard/admin")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService adminDashboardService;

    @GetMapping("/detail")
    public AdminDashboardDTO getChartDetail(){
        AdminDashboardDTO adminDashboardDTO = adminDashboardService.getDetail();
        return adminDashboardDTO;
    }

}
