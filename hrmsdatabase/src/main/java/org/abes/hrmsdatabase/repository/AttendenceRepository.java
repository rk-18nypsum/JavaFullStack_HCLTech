package org.abes.hrmsdatabase.repository;

import org.abes.hrmsdatabase.entity.Attendance;
import org.abes.hrmsdatabase.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendance, Long> {
    boolean existsByEmployeeAndAttendancedate(Employee employee, LocalDate attendancedate);

    List<Attendance> findByEmployee(Employee employee);
}