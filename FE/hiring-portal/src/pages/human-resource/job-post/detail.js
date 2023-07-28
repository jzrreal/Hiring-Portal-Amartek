import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2'
import dateFormat from 'dateformat'

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Index() {
  const navigate = useNavigate();
  const { id } = useParams();
  const [dataJobPost, setDataJobPost] = useState([{}]);
  const [dataApplicant, setDatadataApplicant] = useState([{}]);

  // Get Data
  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/job-posts/" + id,
    })
      .then(function (response) {
        setDataJobPost(response.data.data);
        axios({
          method: "GET",
          url: process.env.REACT_APP_API_URL + "/api/applications/job-post/" + response.data.data.id,
        })
          .then(function (response) {
            setDatadataApplicant(response.data.data);
          })
          .catch(function (error) {
            console.log(error);
          });
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
                  <h1 className="m-0">Detail of Job Post</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                    <li className="breadcrumb-item"><NavLink to="/human-resource/job-post">Job Post</NavLink></li>
                    <li className="breadcrumb-item active">Detail Job Post</li>
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
                <div className="card mb-4">
                  <div className="card-body">
                    <div className='row'>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Job Title</label>
                          <input type="text" className="form-control" id="id" value={dataJobPost.title} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Job Function</label>
                          <input type="text" className="form-control" id="id" value={dataJobPost.job_function} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Job Level</label>
                          <input type="text" className="form-control text-capitalize" id="id" value={dataJobPost.job_level} readOnly />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h4 className="mb-3">List of Applicant</h4>
                    <table id="example1" className="table table-bordered table-striped table-hover">
                      <thead>
                        <tr>
                          <th>Applicant Name</th>
                          <th>Gender</th>
                          <th>Age</th>
                          <th>School / University</th>
                          <th>Apply At</th>
                          <th>Status</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {dataApplicant.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.name}</td>
                              <td className="text-capitalize">{data.gender}</td>
                              <td className="text-capitalize">{data.age} Years</td>
                              <td className="text-capitalize">{data.major} - {data.school_name}</td>
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
                                <NavLink to={`/human-resource/job-post/detail/applicant/${data.job_application_id}`} className="btn btn-sm btn-info mr-2"><i className="fas fa-eye"></i></NavLink>
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