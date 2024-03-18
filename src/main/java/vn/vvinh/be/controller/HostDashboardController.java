package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.AdminDashboardDTO;
import vn.vvinh.be.dto.HostDashboardDTO;
import vn.vvinh.be.service.AdminDashboardService;
import vn.vvinh.be.service.HostDashboardService;

@RestController
@SecurityRequirement(name = "api")
@CrossOrigin("*")
@RequestMapping("/dashboard/host")
public class HostDashboardController {

    @Autowired
    private HostDashboardService hostDashboardService;

    @GetMapping("/detail")
    public HostDashboardDTO getChartDetail(){
        HostDashboardDTO hostDashboardDTO = hostDashboardService.getDetail();
        return hostDashboardDTO;
}
}
