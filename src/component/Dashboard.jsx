import React, { useState, useEffect } from 'react';
import Attendance from './Attendance';
import { Link } from 'react-router-dom';

function Dashboard() {
  const [employees, setEmployees] = useState([]);
  const [activeTab, setActiveTab] = useState('attendance');

  useEffect(() => {
    fetch('http://localhost:8080/v1/user/getall')
      .then((res) => res.json())
      .then((data) => setEmployees(data))
      .catch((err) => console.log('Error fetching employees:', err));
  }, []);

  return (
    <div className="container py-3">
      <div className="p-4 mb-4 bg-light rounded-3 shadow-sm text-start">
        <h2 className="fw-bold">HRMS Dashboard</h2>
        <p className="lead text-muted mb-3">
          Manage employee registration, view staff records, and mark daily attendance.
        </p>
        <div className="d-flex gap-2">
          <button
            className={`btn ${activeTab === 'attendance' ? 'btn-primary' : 'btn-outline-primary'}`}
            onClick={() => setActiveTab('attendance')}
          >
            Mark Attendance
          </button>
          <button
            className={`btn ${activeTab === 'employees' ? 'btn-primary' : 'btn-outline-primary'}`}
            onClick={() => setActiveTab('employees')}
          >
            All Employees ({employees.length})
          </button>
          <Link to="/registeration" className="btn btn-outline-success">
            + New Employee
          </Link>
        </div>
      </div>

      {activeTab === 'attendance' ? (
        <Attendance />
      ) : (
        <div className="card shadow-sm text-start">
          <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center">
            <h5 className="mb-0">Employee Directory</h5>
            <span className="badge bg-primary">{employees.length} Employees</span>
          </div>
          <div className="card-body p-0 table-responsive">
            {employees.length === 0 ? (
              <p className="p-4 text-center text-muted mb-0">No employees registered yet.</p>
            ) : (
              <table className="table table-striped table-hover mb-0">
                <thead className="table-light">
                  <tr>
                    <th>Emp ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Designation</th>
                    <th>Stack</th>
                  </tr>
                </thead>
                <tbody>
                  {employees.map((emp) => (
                    <tr key={emp.emp_id}>
                      <td><strong>{emp.emp_id}</strong></td>
                      <td>{emp.emp_name}</td>
                      <td>{emp.email}</td>
                      <td>{emp.designation}</td>
                      <td><span className="badge bg-info text-dark">{emp.stack}</span></td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default Dashboard;