import axios from 'axios'
import React from 'react'
import { NavLink } from 'react-router-dom'

function Navbar() {
  const logoutEvent = (e) => {
    e.preventDefault()
    axios({
      method: "POST",
      url: process.env.REACT_APP_API_URL + "/api/auth/logout",
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem("authToken")
      }
    })
      .then(response => {
        if (response.data.status == 200) {
          localStorage.removeItem("authToken")
          localStorage.removeItem("role")
          window.location.replace("http://localhost:3000/login")
        }
      })
      .catch(err => {
        console.log(err.response.data.message)
      })
  }
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
          <NavLink className="nav-link text-danger" onClick={logoutEvent} >
            <i className="fas fa-door-open mr-2"></i>
            <span>Logout</span>
          </NavLink>
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