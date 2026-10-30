package org.abes.hrmsdatabase.controller;

import org.abes.hrmsdatabase.entity.Attendance;
import org.abes.hrmsdatabase.service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5173/"})
@RequestMapping("/v1/user/attendance")
public class AttendanceController {
    @Autowired
    private AttendenceService attendenceService;

    @PostMapping("/markattendance")
    public String markAttendance(@RequestBody Attendance attendance){
        return attendenceService.markAttendence(attendance);
    }

    @GetMapping("/getall")
    public List<Attendance> getAllAttendance(){
        return attendenceService.getAllAttendence();
    }

    @GetMapping("/getbyid/{id}")
    public Optional<Attendance> getById(@PathVariable Long id){
        return attendenceService.getById(id);
    }

    @GetMapping("/getbyemp/{empid}")
    public List<Attendance> getByEmployeeId(@PathVariable Long empid){
        return attendenceService.getByEmployeeId(empid);
    }
}