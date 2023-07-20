import React from "react";

function Sidebar() {
  return (
    <aside
      id="layout-menu"
      className="layout-menu menu-vertical menu bg-menu-theme"
    >
      <div className="app-brand demo">
        <a href="index.html" className="app-brand-link">
          <span className="app-brand-logo demo"></span>
          <span className="app-brand-text demo menu-text fw-bolder ms-2">
            Sneat
          </span>
        </a>

        <a
          href="#"
          className="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none"
        >
          <i className="bx bx-chevron-left bx-sm align-middle"></i>
        </a>
      </div>

      <div className="menu-inner-shadow"></div>

      <ul className="menu-inner py-1">
        {/* Dashboard */}
        <li className="menu-item active">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Dashboard</div>
          </a>
        </li>

        {/* Master Data */}
        <li className="menu-item">
          <a href="#" className="menu-link menu-toggle">
            <i className="menu-icon tf-icons bx bx-cube-alt"></i>
            <div>Master Data</div>
          </a>
          <ul className="menu-sub">
           {/* Roles */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Roles</div>
          </a>
        </li>
        {/* Users */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Users</div>
          </a>
        </li>
        {/* Candidate Profiles */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Candidate Profiles</div>
          </a>
        </li>
        {/* Application Status */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Application Status</div>
          </a>
        </li>
        {/* Question Levels */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Question Levels</div>
          </a>
        </li>
        {/* Skill Levels */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Skill Levels</div>
          </a>
        </li>
        {/* Job Levels */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Job Levels</div>
          </a>
        </li>
        {/* Job Functions */}
        <li className="menu-item">
          <a href="index.html" className="menu-link">
            <i className="menu-icon tf-icons bx bx-home-circle"></i>
            <div>Job Functions</div>
          </a>
        </li>
          </ul>
        </li>
      </ul>
    </aside>
  );
}

export default Sidebar;
