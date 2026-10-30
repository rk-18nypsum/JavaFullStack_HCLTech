package org.abes.hrmsdatabase.service;


import org.abes.hrmsdatabase.entity.Attendance;
import org.abes.hrmsdatabase.entity.Employee;
import org.abes.hrmsdatabase.repository.AttendenceRepository;
import org.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendenceService {

    @Autowired
    private AttendenceRepository attendenceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public String markAttendence(Attendance attendance) {
        if (attendance == null || attendance.getEmployee() == null) {
            return "Employee details are required";
        }

        long empId = attendance.getEmployee().getEmp_id();
        Optional<Employee> empOpt = employeeRepository.findById(empId);
        if (empOpt.isEmpty()) {
            return "Employee not found with id: " + empId;
        }

        Employee employee = empOpt.get();
        attendance.setEmployee(employee);

        if (attendance.getAttendancedate() == null) {
            attendance.setAttendancedate(LocalDate.now());
        }

        if (attendance.getInTime() == null) {
            attendance.setInTime(LocalDateTime.now());
        }

        if (attendenceRepository.existsByEmployeeAndAttendancedate(employee, attendance.getAttendancedate())) {
            return "Attendance already marked for today";
        }

        attendenceRepository.save(attendance);
        return "Attendance marked successfully";
    }

    public List<Attendance> getAllAttendence() {
        return attendenceRepository.findAll();
    }

    public Optional<Attendance> getById(Long id) {
        return attendenceRepository.findById(id);
    }

    public List<Attendance> getByEmployeeId(Long empId) {
        Optional<Employee> empOpt = employeeRepository.findById(empId);
        if (empOpt.isPresent()) {
            return attendenceRepository.findByEmployee(empOpt.get());
        }
        return List.of();
    }
}

