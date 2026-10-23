package com.abesproject.hclhrms.controller;

import com.abesproject.hclhrms.EmployeeServiceImp;
import com.abesproject.hclhrms.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeServiceImp employeeServiceImp;

    @GetMapping("/addemp")
    public  String addnewEmployee(){
        return employeeServiceImp.addEmp();
    }


    @GetMapping("/getemp")
    public List<Employee> getEmp(){
        return employeeServiceImp.getemp();
    }

    @GetMapping("/getempbyid")
    public  Employee getempbyid(){
        return employeeServiceImp.getEmpbyid();
    }

    @GetMapping("/bulk")
    public String addBulkEmployees(){
        return employeeServiceImp.addEmpBulk();
    }
}
