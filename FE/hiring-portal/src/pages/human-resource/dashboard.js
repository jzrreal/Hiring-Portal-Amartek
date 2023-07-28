import React, { useContext, useEffect, useState } from "react";
import axios from "axios";

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";
import { useOutletContext } from "react-router-dom";

function Dashboard() {
  const [totalJobs, setTotalJobs] = useState(0);
  const [totalApplicants, setTotalApplicants] = useState(0);
  const [dataNewApplicant, setDataNewApplicant] = useState([{}]);
  const token = useOutletContext()

  useEffect(() => {

    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/dashboards",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("authToken")
      }
    })
      .then(response => {
        setTotalJobs(response.data.data.total_job_post)
        setTotalApplicants(response.data.data.total_applicants)
        setDataNewApplicant(response.data.data.applicant_responses)
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
            <div className="col-12 col-sm-6 col-md-6">
              <div className="info-box">
                <span className="info-box-icon bg-info elevation-1"><i className="fas fa-briefcase"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Jobs</span>
                  <span className="info-box-number">
                    {totalJobs}
                  </span>
                </div>
              </div>
            </div>
            <div className="col-12 col-sm-6 col-md-6">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-danger elevation-1"><i className="fas fa-users"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Applicants Apply Job</span>
                  <span className="info-box-number">{totalApplicants}</span>
                </div>
              </div>
            </div>
          </div>
          <div className="row mt-2">
            <div className="col">
              <div className="card">
                <div className="card-body">
                  <h5><i className="fas fa-users mr-2"></i> New Applicants Apply Job</h5>
                  <div className="table-responsive mt-3">
                    <table className="table table-bordered table-striped table-hover">
                      <thead>
                        <tr>
                          <th>Fullname</th>
                          <th>Email</th>
                          <th>Phone Number</th>
                        </tr>
                      </thead>
                      <tbody>
                        {dataNewApplicant.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.full_name}</td>
                              <td>{data.email}</td>
                              <td className="text-capitalize">{data.phone_number}</td>
                            </tr>
                          );
                        })}
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
