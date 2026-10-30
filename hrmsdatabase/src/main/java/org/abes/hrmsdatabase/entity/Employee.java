package org.abes.hrmsdatabase.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    private  long emp_id;
    private  String emp_name;
    private  String stack;
    private  String email;
    private  String password;

    public Employee(long emp_id, String emp_name, String stack, String designation) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.stack = stack;
        this.designation = designation;
    }

    public Employee(long emp_id, String emp_name, String email, String designation, String stack, String password) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.email = email;
        this.designation = designation;
        this.stack = stack;
        this.password = password;
    }

    private String designation;

    // Solution: Default (no-arg) constructor add karein
    public Employee() {
    }


    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", email='" + email + '\'' +
                ", designation='" + designation + '\'' +
                ", stack='" + stack + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}