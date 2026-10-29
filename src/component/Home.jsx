import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div className="container py-5 text-center">
      <div className="py-5 px-3">
        <h1 className="display-5 fw-bold text-primary mb-3">Welcome to HCL HRMS</h1>
        <p className="fs-5 text-muted mb-4">
          Human Resource Management System for Employee Registration, Authentication, and Daily Attendance Tracking.
        </p>

        <div className="d-flex justify-content-center gap-3 flex-wrap">
          <Link to="/attendance" className="btn btn-primary btn-lg px-4">
            Mark Attendance
          </Link>
          <Link to="/dashboard" className="btn btn-outline-primary btn-lg px-4">
            Go to Dashboard
          </Link>
          <Link to="/registeration" className="btn btn-outline-secondary btn-lg px-4">
            Register Employee
          </Link>
          <Link to="/login" className="btn btn-outline-dark btn-lg px-4">
            Login
          </Link>
        </div>

        <div className="row mt-5 pt-4 text-start">
          <div className="col-md-4 mb-3">
            <div className="card h-100 shadow-sm border-0 bg-light p-3">
              <h5 className="fw-bold text-primary">Employee Registration</h5>
              <p className="text-muted small">
                Register new employees with their ID, name, email, designation, and tech stack details.
              </p>
              <Link to="/registeration" className="text-decoration-none mt-auto">
                Register Now &rarr;
              </Link>
            </div>
          </div>
          <div className="col-md-4 mb-3">
            <div className="card h-100 shadow-sm border-0 bg-light p-3">
              <h5 className="fw-bold text-primary">Daily Attendance</h5>
              <p className="text-muted small">
                Mark punch-in attendance securely with duplicate check and timestamp tracking.
              </p>
              <Link to="/attendance" className="text-decoration-none mt-auto">
                Mark Today &rarr;
              </Link>
            </div>
          </div>
          <div className="col-md-4 mb-3">
            <div className="card h-100 shadow-sm border-0 bg-light p-3">
              <h5 className="fw-bold text-primary">Admin Dashboard</h5>
              <p className="text-muted small">
                View all registered employees and review daily attendance logs in real time.
              </p>
              <Link to="/dashboard" className="text-decoration-none mt-auto">
                View Dashboard &rarr;
              </Link>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;