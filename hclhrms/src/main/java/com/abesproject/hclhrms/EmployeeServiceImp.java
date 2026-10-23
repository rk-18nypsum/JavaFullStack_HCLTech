package com.abesproject.hclhrms;

import com.abesproject.hclhrms.entity.Employee;
import com.abesproject.hclhrms.repository.EmpRepInf;
import com.abesproject.hclhrms.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class EmployeeServiceImp {
    @Autowired
    private EmpRepInf emprepo;
    public String addEmp(){

        Employee empl=new Employee(200, "Rahul", "FSD",
                "java", "rahul@gmail.com");
        return emprepo.addEmp(empl);


    }
    public List<Employee> getemp(){
        return emprepo.getEmp();
    }
    public Employee getEmpbyid(){
        return emprepo.getEmpById(200);
    }

    public String addEmpBulk(){
        List<Employee> employeeList = List.of(
                new Employee(897, "sonu", "inspector", "FSD", "sonu@gmail.com"),
                new Employee(565, "ramesh", "chor", "flutter", "ramesh@gmail.com")
        );
        return emprepo.addEmpBulk(employeeList);
    }
}
