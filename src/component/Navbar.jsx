import React from 'react';
import { Link, useNavigate, useLocation } from 'react-router-dom';

function Navbar({ user, onLogout }) {
  const location = useLocation();
  const navigate = useNavigate();

  const handleLogoutClick = () => {
    if (onLogout) {
      onLogout();
    }
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm mb-4">
      <div className="container">
        <span className="navbar-brand fw-bold text-white mb-0">
          HRMS Portal
        </span>

        <div className="navbar-nav ms-auto d-flex flex-row align-items-center gap-3">
          {!user ? (
            <>
              <Link
                className={`nav-link text-white px-2 ${location.pathname === '/login' ? 'fw-bold text-warning border-bottom border-warning' : ''}`}
                to="/login"
              >
                Login
              </Link>
              <Link
                className={`nav-link text-white px-2 ${location.pathname === '/registeration' ? 'fw-bold text-warning border-bottom border-warning' : ''}`}
                to="/registeration"
              >
                Register
              </Link>
            </>
          ) : (
            <>
              <span className="navbar-text text-light small me-2">
                Employee: <strong>#{user.emp_id}</strong>
              </span>
              <button
                className="btn btn-sm btn-outline-danger"
                onClick={handleLogoutClick}
              >
                Logout
              </button>
            </>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;