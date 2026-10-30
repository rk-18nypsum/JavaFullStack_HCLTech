package org.abes.hrmsdatabase.service;

import org.abes.hrmsdatabase.entity.Employee;
import org.abes.hrmsdatabase.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String createNewEmployee(Employee newEmp){
//        Employee empl =new Employee(400,"Ram","FSD","Teacher");
        employeeRepository.save(newEmp);


        return "Employee is added succsessfuly";
    }
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();

    }
    public Optional<Employee> getById(long id){
        return employeeRepository.findById(id);

    }
    public String deletebyid(long id){
        employeeRepository.deleteById(id);
        return "Done";
    }
    public String deleteall(){
        employeeRepository.deleteAll();
        return "Done";
    }

    public String login(long empid, String password) {
        Optional<Employee> empOpt = employeeRepository.findById(empid);
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            if (emp.getPassword() != null && emp.getPassword().equals(password)) {
                return "Login Successful";
            } else {
                return "Invalid Password";
            }
        }
        return "Employee not found";
    }

    public String verification(Employee emp) {
        if (emp == null) {
            return "Employee data missing";
        }
        return login(emp.getEmp_id(), emp.getPassword());
    }

}