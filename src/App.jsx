import "./App.css";
import { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./component/Navbar";
import Login from "./component/Login";
import Registration from "./component/Registration";
import Attendance from "./component/Attendance";

function App() {
  const [user, setUser] = useState(() => {
    try {
      const saved = localStorage.getItem("user");
      return saved ? JSON.parse(saved) : null;
    } catch {
      return null;
    }
  });

  const handleLogin = (userData) => {
    localStorage.setItem("user", JSON.stringify(userData));
    setUser(userData);
  };

  const handleLogout = () => {
    localStorage.removeItem("user");
    setUser(null);
  };

  return (
    <BrowserRouter>
      <Navbar user={user} onLogout={handleLogout} />
      <div className="container">
        <Routes>
          {/* If user is logged in, default is attendance; otherwise login */}
          <Route
            path="/"
            element={<Navigate to={user ? "/attendance" : "/login"} replace />}
          />

          {/* Login page */}
          <Route
            path="/login"
            element={
              user ? (
                <Navigate to="/attendance" replace />
              ) : (
                <Login onLoginSuccess={handleLogin} />
              )
            }
          />

          {/* Registration page */}
          <Route path="/registration" element={<Registration />} />

          {/* Attendance page: ONLY accessible after login */}
          <Route
            path="/attendance"
            element={
              user ? (
                <Attendance user={user} onLogout={handleLogout} />
              ) : (
                <Navigate to="/login" replace />
              )
            }
          />

          {/* Catch-all route */}
          <Route
            path="*"
            element={<Navigate to={user ? "/attendance" : "/login"} replace />}
          />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
