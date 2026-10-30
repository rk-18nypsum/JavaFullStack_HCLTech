import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

function Attendance({ user, onLogout }) {
  const navigate = useNavigate();
  const currentUser =
    user ||
    (() => {
      try {
        return JSON.parse(localStorage.getItem("user"));
      } catch {
        return null;
      }
    })();

  const empId = currentUser?.emp_id || "";
  const [attendanceDate, setAttendanceDate] = useState(() => {
    return new Date().toISOString().split("T")[0];
  });
  const [comment, setComment] = useState("Present");
  const [message, setMessage] = useState("");
  const [isError, setIsError] = useState(false);
  const [attendanceList, setAttendanceList] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!currentUser) {
      navigate("/login");
      return;
    }
    fetchAttendanceList();
  }, [currentUser]);

  async function fetchAttendanceList() {
    try {
      const response = await fetch(
        "http://localhost:8080/v1/user/attendance/getall",
      );
      if (response.ok) {
        const data = await response.json();
        // Filter records for the logged in employee or show all
        setAttendanceList(data);
      }
    } catch (err) {
      console.log("Error fetching attendance list:", err);
    }
  }

  async function handleMarkAttendance(e) {
    e.preventDefault();
    setMessage("");
    setIsError(false);

    if (!empId) {
      setMessage("No logged in employee found. Please login again.");
      setIsError(true);
      return;
    }

    setLoading(true);
    try {
      const payload = {
        employee: {
          emp_id: Number(empId),
        },
        attendancedate:
          attendanceDate || new Date().toISOString().split("T")[0],
        comment: comment || "Present",
      };

      const res = await fetch(
        "http://localhost:8080/v1/user/attendance/markattendance",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(payload),
        },
      );

      const resultText = await res.text();
      setMessage(resultText);

      if (resultText.toLowerCase().includes("success")) {
        setIsError(false);
        fetchAttendanceList();
      } else {
        setIsError(true);
      }
    } catch (err) {
      console.error(err);
      setMessage(
        "Failed to connect to backend server. Make sure hrmsDatabase is running.",
      );
      setIsError(true);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="container mt-3 mb-5" style={{ maxWidth: "800px" }}>
      <div className="card shadow-sm mb-4 border-0">
        <div className="card-header bg-success text-white d-flex justify-content-between align-items-center py-3">
          <div>
            <h4 className="mb-0 fw-bold">Mark Attendance</h4>
            <small>Welcome! Mark your daily attendance below.</small>
          </div>
          <span className="badge bg-light text-dark fs-6 px-3 py-2">
            Emp ID: {empId}
          </span>
        </div>

        <div className="card-body p-4 text-start">
          {message && (
            <div
              className={`alert ${isError ? "alert-danger" : "alert-success"} mb-4`}
              role="alert"
            >
              <strong>{message}</strong>
            </div>
          )}

          <form onSubmit={handleMarkAttendance}>
            <div className="row g-3">
              <div className="col-md-4">
                <label className="form-label font-weight-bold">
                  Employee ID
                </label>
                <input
                  type="text"
                  className="form-control bg-light"
                  value={empId}
                  readOnly
                  disabled
                />
              </div>

              <div className="col-md-4">
                <label className="form-label font-weight-bold">Date</label>
                <input
                  type="date"
                  className="form-control"
                  value={attendanceDate}
                  onChange={(e) => setAttendanceDate(e.target.value)}
                  required
                />
              </div>

              <div className="col-md-4">
                <label className="form-label font-weight-bold">
                  Status / Comment
                </label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="e.g. Present / WFH"
                  value={comment}
                  onChange={(e) => setComment(e.target.value)}
                />
              </div>
            </div>

            <div className="mt-4 d-flex gap-2">
              <button
                type="submit"
                className="btn btn-success px-4 py-2 fw-bold"
                disabled={loading}
              >
                {loading ? "Marking..." : "Mark Today's Attendance"}
              </button>
              <button
                type="button"
                className="btn btn-outline-secondary"
                onClick={fetchAttendanceList}
              >
                Refresh Records
              </button>
            </div>
          </form>
        </div>
      </div>

      <div className="card shadow-sm border-0">
        <div className="card-header bg-dark text-white d-flex justify-content-between align-items-center">
          <h5 className="mb-0">Attendance Records</h5>
          <span className="badge bg-secondary">
            {attendanceList.length} Total
          </span>
        </div>
        <div className="card-body p-0 table-responsive">
          {attendanceList.length === 0 ? (
            <p className="p-4 text-center text-muted mb-0">
              No attendance records found yet.
            </p>
          ) : (
            <table className="table table-striped table-hover mb-0 text-start">
              <thead className="table-light">
                <tr>
                  <th>#</th>
                  <th>Emp ID</th>
                  <th>Employee Name</th>
                  <th>Date</th>
                  <th>In Time</th>
                  <th>Comment</th>
                </tr>
              </thead>
              <tbody>
                {attendanceList.map((item, idx) => (
                  <tr key={item.attendanceId || idx}>
                    <td>{item.attendanceId}</td>
                    <td>
                      <strong>{item.employee?.emp_id}</strong>
                    </td>
                    <td>{item.employee?.emp_name || "-"}</td>
                    <td>{item.attendancedate}</td>
                    <td>
                      {item.inTime
                        ? new Date(item.inTime).toLocaleTimeString()
                        : "-"}
                    </td>
                    <td>
                      <span className="badge bg-success">
                        {item.comment || "Present"}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>
    </div>
  );
}

export default Attendance;
