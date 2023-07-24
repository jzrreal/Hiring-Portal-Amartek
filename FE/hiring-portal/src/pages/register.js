import React, { useState } from 'react'
import { Link, useNavigate } from "react-router-dom";
import axios from 'axios';
import Swal from 'sweetalert2';

function Register() {
  const navigate = useNavigate();

  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [full_name, setFull_name] = useState("")
  const [birth_date, setBirth_date] = useState("")
  const [gender, setGender] = useState("")
  const [phone, setPhone] = useState("")

  const body = {
    full_name : full_name,
    email : email,
    password : password,
    phone : phone,
    birth_date : birth_date,
    gender : gender
  }

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

  // const genderHandler = e => {
  //   if 
  // }

  const handleSubmit = e => {
    e.preventDefault()

    console.log(body)

    axios({
      method: "POST",
      url: process.env.REACT_APP_API_URL + "/api/auth/register",
      data: body
      })
    .then(
      Toast.fire({
        icon: 'success',
        title: 'Account verification has been sent to your email'
    }),
    )
    .catch(function (error) {
        console.log(error);
    });
  }

  return (
    <div className="hold-transition login-page">
      <div className="login-box" style={{ width: 800 }}>
        <div className="login-logo">
          <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
        </div>
        <div className="card mt-5">
          <div className="card-body login-card-body">
            <p className="login-box-msg">Register a new applicant</p>

            <form method="POST" onSubmit={handleSubmit}>
              <div className="row">
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputEmail1">Fullname</label>
                    <div className="input-group mb-3">
                      <input 
                        type="text"
                        value={full_name}
                        onChange={e => setFull_name(e.target.value)}
                        className="form-control" 
                        placeholder="Fullname" />
                      <div className="input-group-append">
                        <div className="input-group-text">
                          <span className="fas fa-user"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputEmail1">Email</label>
                    <div className="input-group mb-3">
                      <input 
                        type="email"
                        value={email}
                        onChange={e => setEmail(e.target.value)}
                        className="form-control" 
                        placeholder="Email" />
                      <div className="input-group-append">
                        <div className="input-group-text">
                          <span className="fas fa-envelope"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <div className="input-group mb-3">
                      <input 
                        type="password" 
                        value={password}
                        onChange={e => setPassword(e.target.value)}
                        className="form-control" 
                        placeholder="Password" />
                      <div className="input-group-append">
                        <div className="input-group-text">
                          <span className="fas fa-lock"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputEmail1">Phone Number</label>
                    <div className="input-group mb-3">
                      <input 
                        type="number"
                        value={phone}
                        onChange={e => setPhone(e.target.value)}
                        className="form-control" 
                        placeholder="Phone Number" />
                      <div className="input-group-append">
                        <div className="input-group-text">
                          <span className="fas fa-phone"></span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="row">
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputEmail1">Birthdate</label>
                    <div className="input-group mb-3">
                      <input 
                        type="date" 
                        value={birth_date}
                        onChange={e => setBirth_date(e.target.value)}
                        className="form-control" />
                    </div>
                  </div>
                </div>
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputPassword1">Gender</label>
                    <div className="input-group mb-3">
                      <div className="form-check form-check-inline">
                        <input 
                          className="form-check-input" 
                          type="radio" 
                          name="inlineRadioOptions" 
                          id="inlineRadio1" 
                          value="MALE" 
                          onClick={e => setGender(e.target.value)}/>
                        <label className="form-check-label" for="inlineRadio1">Man</label>
                      </div>
                      <div className="form-check form-check-inline">
                        <input 
                          className="form-check-input" 
                          type="radio" 
                          name="inlineRadioOptions" 
                          id="inlineRadio2" 
                          value="FEMALE" 
                          onClick={e => setGender(e.target.value)}/>
                        <label className="form-check-label" for="inlineRadio2">Woman</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="mt-3">
                <button type="submit" className="btn btn-primary btn-block">Register Now</button>
                <Link to="/login" className="btn btn-outline-primary btn-block" >Back to Login</Link>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Register
