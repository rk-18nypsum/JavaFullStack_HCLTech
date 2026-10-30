package org.abes.hrmsdatabase.controller;

import org.abes.hrmsdatabase.entity.Employee;
import org.abes.hrmsdatabase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:5173/")
@RequestMapping("/v1/user")
public class EmployeeController {
    @Autowired

    private EmployeeService employeeService;

    @GetMapping("/")
    public  String msg(){
        return "Chal gya project!! Khush ho jaaa";
    }

    @PostMapping("/addemp")
    public  String addnewEmployee(@RequestBody Employee emp){

        return employeeService.createNewEmployee(emp);
    }

    @GetMapping("/getall")
    public List<Employee> getallEmployee(){
        return employeeService.getAllEmployee();
    }
    @GetMapping("/getbyid/{empid}")
    public Optional<Employee> getbyId(@PathVariable Long empid){
        return employeeService.getById(empid);
    }

    @DeleteMapping("/deletebyid/{empid}")
    public String deleteById(@PathVariable Long empid){
        return employeeService.deletebyid(empid);
    }

    @DeleteMapping("/deleteall")
    public String deleteall(){
        return employeeService.deleteall();
    }

    @PostMapping("/login")
    public String login(@RequestParam long empid, @RequestParam String password){
        return employeeService.login(empid, password);
    }


}