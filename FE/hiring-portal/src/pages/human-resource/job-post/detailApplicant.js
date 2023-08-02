import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext, useParams } from 'react-router-dom';
import Swal from 'sweetalert2';
import axios from 'axios';
import dateFormat from 'dateformat';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Add() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [data, setData] = useState([{}]);
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

  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/applications/" + id + "/applicants",
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then((response) => {
        setData(response.data.data)
      })
  }, [])

  // Apply Now
  const generateTest = (e) => {
    e.preventDefault()
    axios({
      method: "POST",
      url: process.env.REACT_APP_API_URL + "/api/online-tests/applications/" + id,
      headers: {
        Authorization: "Bearer " + token
      }
    }).then((response) => {
      Toast.fire({
        icon: 'success',
        title: response.data.message
      })
      navigate('/human-resource/job-post', { replace: false })
    })
      .catch((error) => {
        Toast.fire({
          icon: 'success',
          title: error.response.data.message
        })
      })
  }

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
        <div className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1 className="m-0">Detail of List Applicant</h1>
              </div>
              <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                  <li className="breadcrumb-item"><NavLink to="/human-resource/job-post">Job Post</NavLink></li>
                  <li className="breadcrumb-item"><NavLink onClick={() => navigate(-1)}>Detail Job Post</NavLink></li>
                  <li className="breadcrumb-item active">Detail of List Applicant</li>
                </ol>
              </div>
            </div>
          </div>
        </div>
        {/* Content Header */}

        {/* Main Content */}
        <section className="content">
          <div className="container">
            <div className="row">
              <div className="col-12">
                <div className="card">
                  <div className="card-body">
                    <div className="row">
                      <div className="col">
                        <div className="form-group">
                          <label for="fullname">Full Name</label>
                          <input type='text' className="form-control" id="fullname" value={data.full_name} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="email">Email</label>
                          <input type='email' className="form-control" id="email" value={data.email} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className="row">
                      <div className="col">
                        <div className="form-group">
                          <label for="age">Age</label>
                          <input type='text' className="form-control" id="age" value={data.age + " Years"} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="gender">Gender</label>
                          <input type='text' className="form-control" id="gender" value={data.gender} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className="row">
                      <div className="col">
                        <div className="form-group">
                          <label for="birthdate">Birth Date</label>
                          <input type='text' className="form-control" id="birthdate" value={dateFormat(data.birth_date, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                      <div className="col">
                        <div className="form-group">
                          <label for="phone">Phone</label>
                          <input type='text' className="form-control" id="phone" value={data.phone} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className="form-group">
                      <label for="last_education">Last Education</label>
                      <textarea className="form-control" id="last_education" value={data.last_education} readOnly />
                    </div>
                    <div className="form-group">
                      <label for="summary">Summary</label>
                      <textarea className="form-control" id="summary" value={data.summary} readOnly />
                    </div>
                    <div className="float-right">
                      <NavLink onClick={() => navigate(-1)} type="button" className="btn btn-secondary mr-2">Back</NavLink>
                      <button onClick={generateTest} type="button" className="btn btn-primary mr-2">Generate Test Online</button>
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
  )
}

export default Add