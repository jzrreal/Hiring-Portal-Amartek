import { useEffect, useState, React } from 'react'
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import dateFormat from 'dateformat'

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Index() {
  const [data, setData] = useState([{}]);

  // Get Data
  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/applications",
      headers: {
        Authorization: "Bearer " + localStorage.getItem("authToken")
      }
    })
      .then(function (response) {
        setData(response.data.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [])

  return (
    <>
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
          <div className="content-header">
            <div className="container-fluid">
              <div className="row mb-2">
                <div className="col-sm-6">
                  <h1 className="m-0">List of History Applicant</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><NavLink to="/applicant/dashboard">Dashboard</NavLink></li>
                    <li className="breadcrumb-item active">History Applicant</li>
                  </ol>
                </div>
              </div>
            </div>
          </div>
          {/* Content Header */}

          {/* Main Content */}
          <section className="content">
            <div className="row">
              <div className="col-12">
                <div className="card">
                  <div className="card-body">
                    <table id="example1" className="table table-bordered table-striped table-hover">
                      <thead>
                        <tr>
                          <th>Job Title</th>
                          <th>Job Level</th>
                          <th>Job Function</th>
                          <th>Apply Date</th>
                          <th>Status</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {data.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.title}</td>
                              <td className="text-capitalize">{data.job_level}</td>
                              <td className="text-capitalize">{data.job_function}</td>
                              <td className="text-capitalize">{dateFormat(data.apply_date, "dd mmmm yyyy")}</td>
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
                              <td>
                                <NavLink to={`/applicant/job-list/detail/${data.job_post_id}`} className="btn btn-sm btn-info mr-2"><i className="fas fa-eye"></i></NavLink>
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
          </section>
          {/* Main Contet */}
        </div>
        {/* Content */}

        {/* Footer */}
        <Footer />
        {/* Footer */}
      </div>
    </>
  )
}

export default Index