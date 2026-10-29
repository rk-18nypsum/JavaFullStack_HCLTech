
package com.abesproject.hclhrms;

import com.abesproject.hclhrms.entity.Employee;
import com.abesproject.hclhrms.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImp {

    @Autowired
    private EmployeeRepo emprepo;

    public String addEmp(Employee emp) {
        return emprepo.addEmp(emp);
    }

    public List<Employee> getemp() {
        return emprepo.getEmp();
    }

    public Employee getEmpbyid(long empid) {
        return emprepo.getEmpById(empid);
    }

    public String addEmpBulk(List<Employee> employeeList) {
        return emprepo.addEmpBulk(employeeList);
    }

    public String deleteByid(long empid) {
        return emprepo.deleteById(empid);
    }

    public String updateById(long empid, Employee updatedEmp) {
        return emprepo.updateById(empid, updatedEmp);
    }
}
