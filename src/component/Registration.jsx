import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function Registration() {
  const [empid, setEmpid] = useState("");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [designation, setDesignation] = useState("");
  const [techstack, setTechstack] = useState("");
  const [password, setPassword] = useState("");
  const [msg, setMsg] = useState("");
  const navigate = useNavigate();

  async function sendData(e) {
    try {
      e.preventDefault();
      setMsg("");
      const server = await fetch("http://localhost:8080/v1/user/addemp", {
        method: "POST",
        body: JSON.stringify({
          emp_id: Number(empid),
          emp_name: name,
          email: email,
          designation: designation,
          stack: techstack,
          password: password,
        }),
        headers: {
          "Content-Type": "application/json",
        },
      });
      const status = await server.text();
      setMsg(status);
      alert(status + " Please login now.");
      navigate("/login");
    } catch (e) {
      console.log(e);
      setMsg("Error connecting to backend: " + e.message);
    }
  }

  return (
    <div className="container mt-4 mb-5" style={{ maxWidth: "600px" }}>
      <div className="card shadow-sm text-start">
        <div className="card-header bg-primary text-white">
          <h4 className="mb-0 text-center">Employee Registration</h4>
        </div>
        <div className="card-body p-4">
          {msg && <div className="alert alert-info">{msg}</div>}

          <form onSubmit={sendData}>
            <div className="mb-3">
              <label
                htmlFor="inputEmpid"
                className="form-label font-weight-bold"
              >
                Employee ID
              </label>
              <input
                type="number"
                id="inputEmpid"
                value={empid}
                onChange={(e) => setEmpid(e.target.value)}
                className="form-control"
                placeholder="Enter emp_id (e.g. 101)"
                required
              />
            </div>

            <div className="mb-3">
              <label
                htmlFor="inputName"
                className="form-label font-weight-bold"
              >
                Full Name
              </label>
              <input
                type="text"
                id="inputName"
                value={name}
                onChange={(e) => setName(e.target.value)}
                className="form-control"
                placeholder="Enter name"
                required
              />
            </div>

            <div className="mb-3">
              <label
                htmlFor="inputEmail"
                className="form-label font-weight-bold"
              >
                Email address
              </label>
              <input
                type="email"
                id="inputEmail"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="form-control"
                placeholder="Enter email address"
                required
              />
              <small className="form-text text-muted">
                We'll never share your email with anyone else.
              </small>
            </div>

            <div className="mb-3">
              <label
                htmlFor="inputDesignation"
                className="form-label font-weight-bold"
              >
                Designation
              </label>
              <input
                type="text"
                id="inputDesignation"
                value={designation}
                onChange={(e) => setDesignation(e.target.value)}
                className="form-control"
                placeholder="e.g. Software Engineer / Trainee"
                required
              />
            </div>

            <div className="mb-3">
              <label
                htmlFor="inputStack"
                className="form-label font-weight-bold"
              >
                Tech Stack
              </label>
              <input
                type="text"
                id="inputStack"
                value={techstack}
                onChange={(e) => setTechstack(e.target.value)}
                className="form-control"
                placeholder="e.g. Java, Spring Boot, React"
                required
              />
            </div>

            <div className="mb-3">
              <label
                htmlFor="inputPassword"
                className="form-label font-weight-bold"
              >
                Password
              </label>
              <input
                type="password"
                id="inputPassword"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="form-control"
                placeholder="Password"
                required
              />
            </div>

            <button
              type="submit"
              className="btn btn-primary w-100 py-2 font-weight-bold"
            >
              Register Employee
            </button>

            <div className="text-center mt-3">
              <small className="text-muted">
                Already registered? <Link to="/login">Login here</Link>
              </small>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Registration;
