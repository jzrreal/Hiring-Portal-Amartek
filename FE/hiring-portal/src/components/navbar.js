import React from 'react'
import { useNavigate } from 'react-router-dom'

function Navbar() {
  const navigate = useNavigate();
  return (
    <nav className="main-header navbar navbar-expand navbar-white navbar-light">
      {/* Left navbar links */}
      <ul className="navbar-nav">
        <li className="nav-item">
          <a className="nav-link" data-widget="pushmenu" href="#" role="button"><i className="fas fa-bars"></i></a>
        </li>
      </ul>

      {/* Right navbar links */}
      <ul className="navbar-nav ml-auto">
        <li className="nav-item">
          <a href="#" className="nav-link text-danger" onClick={() => navigate("/login")}>
            <i className="fas fa-door-open mr-2"></i>
            <span>Logout</span>
          </a>
        </li>
        <li className="nav-item">
          <a className="nav-link" data-widget="fullscreen" href="#" role="button">
            <i className="fas fa-expand-arrows-alt"></i>
          </a>
        </li>
      </ul>
    </nav>
  )
}

export default Navbar