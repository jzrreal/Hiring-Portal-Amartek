import React from "react";
import { Link, NavLink } from "react-router-dom";

function Sidebar() {
  return (
    <aside className="main-sidebar sidebar-dark-primary elevation-4">
      {/* Brand Logo */}
      <Link to="/human-resource/dashboard" className="brand-link">
        <img src="https://www.amartek.id/i/favicon.png" className="img-fuild" />
        <span className="brand-text font-weight-bold font-weight-light ml-3">
          Portal Hiring
        </span>
      </Link>
      {/* Brand Logo */}

      {/* User */}
      <div className="user-panel mt-3 pb-3 mb-3 d-flex align-items-center">
        <div className="image">
          <img src="/assets/dist/img/user2-160x160.jpg" className="img-circle elevation-2" alt="User Image" />
        </div>
        <div className="info ml-2">
          <NavLink to="/human-resource/profile" className="d-block text-white font-weight-bold" style={{ fontSize: 18, }}>Alexander Pierce</NavLink>
          <span href="#" className="d-block text-secondary">Human Resource</span>
        </div>
      </div>
      {/* User */}

      {/* Sidebar Menu */}
      <div className="sidebar">
        <nav className="mt-2">
          <ul
            className="nav nav-pills nav-sidebar flex-column"
            data-widget="treeview"
            role="menu"
            data-accordion="false"
          >
            {/* Dashboard */}
            <li className="nav-item">
              <NavLink to="/human-resource/dashboard" className="nav-link">
                <i className="nav-icon fas fa-tachometer-alt mr-2"></i>
                <p>Dashboard</p>
              </NavLink>
            </li>
            {/* Dashboard */}

            {/* Master Data */}
            <li className="nav-item">
              <a href="#" className="nav-link" id="master-data">
                <i className="nav-icon fas fa-copy mr-2"></i>
                <p>
                  Master Data
                  <i className="fas fa-angle-left right"></i>
                </p>
              </a>
              <ul className="nav nav-treeview">
                <li className="nav-item">
                  <NavLink to="/human-resource/role" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Role</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/applicant" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Applicant</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/applicant-status" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Applicant Status</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/skill-level" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Skill Level</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/question-level" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Question Level</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/job-level" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Job Level</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/job-function" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Job Function</p>
                  </NavLink>
                </li>
                <li className="nav-item">
                  <NavLink to="/human-resource/test-parameter" className="nav-link">
                    <i className="far fa-circle nav-icon mr-2"></i>
                    <p>Test Parameter</p>
                  </NavLink>
                </li>
              </ul>
            </li>
            {/* Master Data */}

            {/* Job */}
            <li class="nav-header mt-2">JOB</li>
            {/* Job Post */}
            <li className="nav-item">
              <NavLink to="/human-resource/job-post" className="nav-link">
                <i className="nav-icon fas fa-briefcase mr-2"></i>
                <p>Job Post</p>
              </NavLink>
            </li>
            {/* Job Post */}
            {/* Job Applicant */}
            <li className="nav-item">
              <NavLink to="/human-resource/job-applicant" className="nav-link">
                <i className="nav-icon fas fa-briefcase mr-2"></i>
                <p>Job Applicant</p>
              </NavLink>
              {/* Job Applicant */}
            </li>
            {/* Job */}
          </ul>
        </nav>
      </div>
      {/* Sidebar Menu */}
    </aside >
  );
}

export default Sidebar;
