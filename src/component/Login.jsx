import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function Login({ onLoginSuccess }) {
  const [empid, setEmpid] = useState("");
  const [password, setPassword] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  async function handleLogin(e) {
    e.preventDefault();
    setErrorMsg("");
    setLoading(true);

    try {
      const url = `http://localhost:8080/v1/user/login?empid=${encodeURIComponent(empid)}&password=${encodeURIComponent(password)}`;
      const response = await fetch(url, {
        method: "POST",
      });

      const result = await response.text();

      if (result.toLowerCase().includes("success")) {
        const userData = { emp_id: Number(empid) };
        if (onLoginSuccess) {
          onLoginSuccess(userData);
        } else {
          localStorage.setItem("user", JSON.stringify(userData));
        }
        alert("Login Successful!");
        navigate("/attendance");
      } else {
        setErrorMsg(result || "Login failed. Please check your credentials.");
      }
    } catch (err) {
      console.error(err);
      setErrorMsg(
        "Could not connect to backend server. Make sure hrmsDatabase is running.",
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container mt-5" style={{ maxWidth: "420px" }}>
      <div className="card shadow-sm text-start border-0">
        <div className="card-header bg-primary text-white text-center py-3">
          <h4 className="mb-0 fw-bold">Employee Login</h4>
          <small className="text-light">
            Login with your Employee ID to mark attendance
          </small>
        </div>
        <div className="card-body p-4">
          {errorMsg && (
            <div className="alert alert-danger" role="alert">
              {errorMsg}
            </div>
          )}

          <form onSubmit={handleLogin}>
            <div className="mb-3">
              <label
                htmlFor="inputEmpId"
                className="form-label font-weight-bold"
              >
                Employee ID
              </label>
              <input
                type="number"
                className="form-control"
                id="inputEmpId"
                placeholder="Enter Employee ID (e.g. 101)"
                value={empid}
                onChange={(e) => setEmpid(e.target.value)}
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
                className="form-control"
                id="inputPassword"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
            </div>

            <button
              type="submit"
              className="btn btn-primary w-100 py-2 fw-bold mb-3"
              disabled={loading}
            >
              {loading ? "Logging in..." : "Login"}
            </button>

            <div className="text-center pt-2 border-top">
              <small className="text-muted">
                Don't have an account?{" "}
                <Link
                  to="/registeration"
                  className="text-primary fw-bold text-decoration-none"
                >
                  Register here
                </Link>
              </small>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
