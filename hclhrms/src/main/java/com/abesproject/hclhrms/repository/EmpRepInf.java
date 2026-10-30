
package com.abesproject.hclhrms.repository;

import com.abesproject.hclhrms.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmpRepoImp implements EmployeeRepo {

    private final List<Employee> emplist = new ArrayList<>();

    @Override
    public String addEmp(Employee emp) {
        emplist.add(emp);
        return "Employee added successfully";
    }

    @Override
    public String addEmpBulk(List<Employee> employeeList) {
        emplist.addAll(employeeList);
        return "All employees added successfully";
    }

    @Override
    public List<Employee> getEmp() {
        return emplist;
    }

    @Override
    public Employee getEmpById(long empid) {
        return emplist.stream()
                .filter(e -> e.getEmp_id() == empid)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String deleteById(long empid) {
        boolean deleted = emplist.removeIf(
                e -> e.getEmp_id() == empid
        );

        return deleted
                ? "Employee deleted successfully"
                : "Employee not found";
    }

    @Override
    public String updateById(long empid, Employee updatedEmp) {
        Employee existingEmp = getEmpById(empid);

        if (existingEmp == null) {
            return "Employee not found";
        }

        existingEmp.setEmp_name(updatedEmp.getEmp_name());
        existingEmp.setDesignation(updatedEmp.getDesignation());
        existingEmp.setProgStack(updatedEmp.getProgStack());
        existingEmp.setEmail(updatedEmp.getEmail());

        return "Employee updated successfully";
    }
}
