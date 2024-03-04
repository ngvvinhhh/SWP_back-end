package vn.vvinh.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.vvinh.be.dto.ScheduleRequestDTO;
import vn.vvinh.be.entity.Schedule;

import vn.vvinh.be.service.ScheduleService;

import java.util.List;

@SecurityRequirement(name = "api")
@RestController
@CrossOrigin("*")
public class ScheduleController {
    @Autowired
    ScheduleService scheduleService;

    @PostMapping("schedule/createSchedule")
    public ResponseEntity createSchedule(@RequestBody  ScheduleRequestDTO scheduleRequestDTO){
        return ResponseEntity.ok(scheduleService.postSchedule(scheduleRequestDTO));}

    @PutMapping("schedule/updateSchedule/{id}")
    public ResponseEntity updateSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO, @PathVariable long id){
        return  ResponseEntity.ok(scheduleService.updateSchedule(scheduleRequestDTO, id));
    }
    @GetMapping("schedule/getSchduleByHost/{hostId}")
    public  ResponseEntity<List<Schedule>> getScheduleByHostId(@PathVariable int hostId){
        List<Schedule> schedule = scheduleService.getScheduleByHostId(hostId);
        return ResponseEntity.ok(schedule);
    }

    @DeleteMapping("schedule/deleteSchedule/{id}")
    public
     ResponseEntity deleteSchedule(@PathVariable long id){
        return ResponseEntity.ok(scheduleService.deleteSchedule(id));
    }

}
