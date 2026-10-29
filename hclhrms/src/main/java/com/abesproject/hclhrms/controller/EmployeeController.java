
package com.abesproject.hclhrms.controller;

import com.abesproject.hclhrms.EmployeeServiceImp;
import com.abesproject.hclhrms.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeServiceImp employeeServiceImp;

    @PostMapping("/addemp")
    public String addnewEmployee(@RequestBody Employee emp) {
        return employeeServiceImp.addEmp(emp);
    }

    @GetMapping("/getemp")
    public List<Employee> getEmp() {
        return employeeServiceImp.getemp();
    }

    @GetMapping("/getempbyid/{id}")
    public Employee getempbyid(@PathVariable long id) {
        return employeeServiceImp.getEmpbyid(id);
    }

    @PostMapping("/bulk")
    public String addBulkEmployees(
            @RequestBody List<Employee> employeeList) {
        return employeeServiceImp.addEmpBulk(employeeList);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable long id) {
        return employeeServiceImp.deleteByid(id);
    }

    @PutMapping("/updateById/{id}")
    public String updateById(
            @PathVariable long id,
            @RequestBody Employee emp) {
        return employeeServiceImp.updateById(id, emp);
    }
}
