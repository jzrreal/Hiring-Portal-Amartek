import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import axios from 'axios';
import dateFormat from 'dateformat'

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Detail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [data, setData] = useState({});
  const token = useOutletContext()

  // Alert Toast
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer)
      toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
  })

  // Get Data
  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/job-posts/" + id,
      headers: {
        Authorization: "Bearer " + token
      }
    }).then(function (response) {
      setData(response.data.data);
    }).catch(function (error) {
      console.log(error);
    });
  }, [])

  // Apply Now
  function applyNow(e) {
    e.preventDefault()
    axios({
      method: "POST",
      url: process.env.REACT_APP_API_URL + "/api/applications/" + data.id,
      headers: {
        Authorization: "Bearer " + token
      }
    }).then(
      Toast.fire({
        icon: 'success',
        title: 'Success apply job'
      }),
      setData(data),
      navigate('/applicant/history-applicant', { replace: false })
    ).catch(function (error) { console.log(error); })
  }

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
                  <h1 className="m-0">Detail of Job</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><NavLink to="/applicant/dashboard">Dashboard</NavLink></li>
                    <li className="breadcrumb-item"><NavLink to="/applicant/job-list">Job List</NavLink></li>
                    <li className="breadcrumb-item active">Detail Job</li>
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
                    <div className="form-group">
                      <label for="name">ID Job Post</label>
                      <input type="text" className="form-control" id="id" value={data.id} readOnly />
                    </div>
                    <div className="row">
                      <div className="col">
                        <div className="form-group">
                          <label for="job_title">Post At</label>
                          <input type="text" className="form-control" id="job_title" value={dateFormat(data.post_at, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="vacancy">Vacancy</label>
                          <input type="text" className="form-control text-capitalize" id="vacancy" value={data.vacancy + " People"} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="job_function">Updated At</label>
                          <input type="text" className="form-control text-capitalize" id="job_function" value={dateFormat(data.updated_at, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className="row">
                      <div className="col">
                        <div className="form-group">
                          <label for="job_level">Open Until</label>
                          <input type="text" className="form-control text-capitalize" id="job_level" value={dateFormat(data.open_until, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="id">Closed</label>
                          {
                            (data.closed === null || data.closed === false) ?
                              <input type="text" className="form-control bg-success" id="id" value="Open" />
                              : <input type="text" className="form-control bg-danger" id="id" value="Closed" />
                          }
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <div className="row">
                      <div className="col">
                        <div className="form-group">
                          <label for="job_title">Job Title</label>
                          <input type="text" className="form-control" id="job_title" value={data.title} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="job_level">Job Level</label>
                          <input type="text" className="form-control text-capitalize" id="job_level" value={data.job_level} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="job_function">Job Function</label>
                          <input type="text" className="form-control text-capitalize" id="job_function" value={data.job_function} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className="form-group">
                      <label for="job_description">Job Description</label>
                      <textarea className="form-control" id="job_description" value={data.description} readOnly />
                    </div>
                    <div className="form-group">
                      <label for="job_requirement">Job Requirement</label>
                      <textarea className="form-control" id="job_requirement" value={data.requirements} readOnly />
                    </div>
                    <div className="float-right">
                      <NavLink to="/applicant/job-list" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                      <button onClick={applyNow} className="btn btn-primary" disabled={data.closed || data.applied}>Apply Now</button>
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
    </>
  )
}

export default Detail