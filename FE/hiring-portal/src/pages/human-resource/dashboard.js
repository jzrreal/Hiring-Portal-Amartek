import { React, useEffect, useState } from "react";
import { useOutletContext } from "react-router-dom";
import axios from "axios";
import dateFormat from "dateformat";
import "chart.js/auto";
import { Bar } from "react-chartjs-2";

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";

function Dashboard() {
  const [totalJobs, setTotalJobs] = useState(0);
  const [totalApplicants, setTotalApplicants] = useState(0);
  const [dataNewApplicant, setDataNewApplicant] = useState([{}]);
  const [dataNewJobs, setDataNewJobs] = useState([{}]);
  const [dataChart, setDataChart] = useState([{}]);
  const labelChart = []
  const valueChart = []
  const token = useOutletContext()

  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/dashboards",
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(response => {
        setTotalJobs(response.data.data.total_job_post)
        setTotalApplicants(response.data.data.total_applicants_apply)
        setDataNewApplicant(response.data.data.applicants_apply_responses)
        setDataNewJobs(response.data.data.job_post_responses)
      })
      .catch(err => {
        setTotalJobs(0);
        setTotalApplicants(0);
      })

    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/job-posts/chart",
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(response => {
        setDataChart(response.data.data)
      })
      .catch(err => {
        console.log(err)
      })
  }, []);

  dataChart.forEach(value => {
    labelChart.push(value.job_function)
    valueChart.push(value.total)
  })

  // Chart
  const chart = {
    labels: labelChart,
    datasets: [
      {
        label: "Applicant",
        backgroundColor: "#007bff",
        borderColor: "#007bff",
        data: valueChart,
      },
    ],
  };

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
          <div className="container">
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
            <div className="row mt-3">
              <div className="col">
                <div className="card">
                  <div className="card-body">
                    <h5><i className="fas fa-chart-bar mr-2"></i> Statistic Applicants Apply by Job Function</h5>
                    <Bar data={chart} />
                  </div>
                </div>
              </div>
            </div>
            <div className="row mt-3">
              <div className="col">
                <div className="card">
                  <div className="card-body">
                    <h5><i className="fas fa-users mr-2"></i> List New Applicants Apply Job</h5>
                    <div className="table-responsive mt-3">
                      <table className="table table-bordered table-striped table-hover">
                        <thead>
                          <tr>
                            <th>Fullname</th>
                            <th>Job Title</th>
                            <th>Job Level</th>
                            <th>Job Function</th>
                            <th>Apply At</th>
                            <th>Status</th>
                          </tr>
                        </thead>
                        <tbody>
                          {dataNewApplicant.map((data) => {
                            return (
                              <tr>
                                <td className="text-capitalize">{data.applicant_name}</td>
                                <td className="text-capitalize">{data.title}</td>
                                <td className="text-capitalize">{data.job_function}</td>
                                <td className="text-capitalize">{data.job_level}</td>
                                <td className="text-capitalize">{dateFormat(data.apply_date, "dd mmmm yyyy")}</td>
                                <td className="text-capitalize">
                                  {
                                    data.status == "submitted" ?
                                      <span className="badge badge-secondary">{data.status}</span>
                                      : (data.status == "reviewed" ?
                                        <span className="badge badge-info">{data.status}</span>
                                        : (data.status == "test" ?
                                          <span className="badge badge-warning">{data.status}</span>
                                          : (data.status == "rejected" ?
                                            <span className="badge badge-danger">{data.status}</span>
                                            : (data.status == "passed" ?
                                              <span className="badge badge-success">{data.status}</span>
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
            <div className="row mt-3">
              <div className="col">
                <div className="card">
                  <div className="card-body">
                    <h5><i className="fas fa-users mr-2"></i> List New Job Post</h5>
                    <div className="table-responsive mt-3">
                      <table className="table table-bordered table-striped table-hover">
                        <thead>
                          <tr>
                            <th>Job Title</th>
                            <th>Job Level</th>
                            <th>Job Function</th>
                            <th>Post At</th>
                            <th>Open Until</th>
                            <th>Closed</th>
                          </tr>
                        </thead>
                        <tbody>
                          {dataNewJobs.map((data) => {
                            return (
                              <tr>
                                <td className="text-capitalize">{data.title}</td>
                                <td className="text-capitalize">{data.job_level}</td>
                                <td className="text-capitalize">{data.job_function}</td>
                                <td className="text-capitalize">{dateFormat(data.post_at, "dd mmmm yyyy")}</td>
                                <td className="text-capitalize">{dateFormat(data.open_until, "dd mmmm yyyy")}</td>
                                <td className="text-capitalize">
                                  {data.closed === null || data.closed === false ?
                                    <span className="badge badge-success">Open</span>
                                    : <span className="badge badge-danger">Closed</span>
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
