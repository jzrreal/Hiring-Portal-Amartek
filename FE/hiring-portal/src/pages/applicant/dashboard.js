import { React, useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import axios from "axios";
import dateFormat from "dateformat";

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";

function Dashboard() {
  const [dataJobList, setDataJobList] = useState([{}]);
  const [dataHistoryApplicant, setDataHistoryApplicant] = useState([{}]);
  const [totalApplyJob, setTotalApplyJob] = useState(0);

  // Get Data
  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/job-posts",
    })
      .then(function (response) {
        setDataJobList(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });

    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/dashboards",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("authToken")
      }
    })
      .then(function (response) {
        setTotalApplyJob(response.data.data.total_apply_job);
        setDataHistoryApplicant(response.data.data.job_application_responses);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [])

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
                    2
                  </span>
                </div>
              </div>
            </div>
            <div className="col-12 col-sm-6 col-md-6">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-success elevation-1"><i className="fas fa-check-double"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Your Applicant</span>
                  <span className="info-box-number">{totalApplyJob}</span>
                </div>
              </div>
            </div>
          </div>
          <div className="row mt-3">
            <div className="col">
              <div className="card">
                <div className="card-header ui-sortable-handle">
                  <h3 className="card-title">
                    <i className="fas fa-briefcase mr-2"></i>
                    <span>New Availabe Jobs</span>
                  </h3>
                  <div className="card-tools">
                    <NavLink to="/applicant/job-list" className="btn btn-sm btn-primary" >See More Availabe Job</NavLink>
                  </div>
                </div>
                <div className="card-body p-0">
                  <div className="table-responsive">
                    <table className="table m-0 table-hover">
                      <thead>
                        <tr>
                          <th>Job Title</th>
                          <th>Job Level</th>
                          <th>Job Function</th>
                          <th>Open Until</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {dataJobList.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.title}</td>
                              <td className="text-capitalize">{data.job_level}</td>
                              <td className="text-capitalize">{data.job_function}</td>
                              <td className="text-capitalize">{dateFormat(data.open_until, "dd mmmm yyyy")}</td>
                              <td>
                                <NavLink to={`/applicant/job-list/detail/${data.id}`} className="btn btn-sm btn-info mr-2"><i className="fas fa-eye"></i></NavLink>
                              </td>
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
          <div className="row mt-3">
            <div className="col">
              <div className="card">
                <div className="card-header ui-sortable-handle">
                  <h3 className="card-title">
                    <i className="fas fa-clock mr-2"></i>
                    <span>New History Applicant</span>
                  </h3>
                  <div className="card-tools">
                    <NavLink to="/applicant/job-list" className="btn btn-sm btn-primary" >See More Availabe Job</NavLink>
                  </div>
                </div>
                <div className="card-body p-0">
                  <div className="table-responsive">
                    <table className="table m-0 table-hover">
                      <thead>
                        <tr>
                          <th>Job Title</th>
                          <th>Job Level</th>
                          <th>Job Function</th>
                          <th>Apply Date</th>
                          <th>Status</th>
                        </tr>
                      </thead>
                      <tbody>
                        {dataHistoryApplicant.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.job_name}</td>
                              <td className="text-capitalize">{data.job_level}</td>
                              <td className="text-capitalize">{data.job_function}</td>
                              <td className="text-capitalize">{dateFormat(data.open_until, "dd mmmm yyyy")}</td>
                              <td className="text-capitalize">
                                {
                                  data.status == "submitted" ?
                                    <span class="badge badge-secondary">{data.status}</span>
                                    : (data.status == "reviewed" ?
                                      <span class="badge badge-info">{data.status}</span>
                                      : (data.status == "test" ?
                                        <span class="badge badge-warning">{data.status}</span>
                                        : (data.status == "rejected" ?
                                          <span class="badge badge-danger">{data.status}</span>
                                          : (data.status == "accepted" ?
                                            <span class="badge badge-success">{data.status}</span>
                                            : null
                                          )
                                        )
                                      )
                                    )
                                }
                              </td>
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
