import React, { useContext, useEffect, useState } from "react";
import axios from "axios";

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";
import { useOutletContext } from "react-router-dom";

function Dashboard() {
  const [totalJobs, setTotalJobs] = useState(0);
  const [totalApplicants, setTotalApplicants] = useState(0);
  const token = useOutletContext()

  useEffect( () => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/dashboards",
      headers: {
        Authorization : "Bearer " + localStorage.getItem("authToken")
      }
    })
    .then(response => {
      setTotalJobs(response.data.data.total_job_post)
      setTotalApplicants(response.data.data.applicant_responses.length)
    })
    .catch(err => {
      setTotalJobs(0);
      setTotalApplicants(0);
    })
  }, []);

  return (
    <div className="wrapper">
      {/* Navbar */}
      <Navbar />
      {/* Navbar */}

      {/* Sidebar */}
      <Sidebar />
      {/* Sidebar */}

      {/* Content */}
      <div className="content-wrapper">
        {/* Content Header */}
        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>Dashboard</h1>
              </div>
            </div>
          </div>
          {/* Content Header */}
        </section>

        {/* Main Content */}
        <section className="content">
          <div className="row">
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box">
                <span className="info-box-icon bg-info elevation-1"><i className="fas fa-cog"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Jobs</span>
                  <span className="info-box-number">
                    {totalJobs}
                    <small>%</small>
                  </span>
                </div>
              </div>
            </div>
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-danger elevation-1"><i className="fas fa-thumbs-up"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Applicants</span>
                  <span className="info-box-number">{totalApplicants}</span>
                </div>
              </div>
            </div>
            <div className="clearfix hidden-md-up"></div>
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-success elevation-1"><i className="fas fa-shopping-cart"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Jobs</span>
                  <span className="info-box-number">{totalJobs}</span>
                </div>
              </div>
            </div>
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-warning elevation-1"><i className="fas fa-users"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Applicants</span>
                  <span className="info-box-number">{totalApplicants}</span>
                </div>
              </div>
            </div>
          </div>
          <div className="row">
            <div className="col">
              <div className="card">
                <div className="card-header ui-sortable-handle">
                  <h3 className="card-title">
                    <i className="fas fa-users mr-2"></i>
                    <span>New Applicants</span>
                  </h3>
                  <div className="card-tools">
                    <a className="btn btn-sm btn-primary" href="#">See More Applicants</a>
                  </div>
                </div>
                <div className="card-body p-0">
                  <div className="table-responsive">
                    <table className="table m-0 table-hover">
                      <thead>
                        <tr>
                          <th>Applicants ID</th>
                          <th>Fullname</th>
                          <th>Email</th>
                          <th>Phone Number</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr>
                          <td><a href="pages/examples/invoice.html">123</a></td>
                          <td>John Doe</td>
                          <td>john.doe@gmail.com</td>
                          <td>089123123123</td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">124</a></td>
                          <td>John Doe</td>
                          <td>john.doe@gmail.com</td>
                          <td>089123123123</td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">125</a></td>
                          <td>John Doe</td>
                          <td>john.doe@gmail.com</td>
                          <td>089123123123</td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">126</a></td>
                          <td>John Doe</td>
                          <td>john.doe@gmail.com</td>
                          <td>089123123123</td>
                        </tr>
                        <tr>
                          <td><a href="pages/examples/invoice.html">127</a></td>
                          <td>John Doe</td>
                          <td>john.doe@gmail.com</td>
                          <td>089123123123</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
        {/* Main Contet */}
      </div>
      {/* Content */}

      {/* Footer */}
      <Footer />
      {/* Footer */}
    </div>
  );
}

export default Dashboard;
