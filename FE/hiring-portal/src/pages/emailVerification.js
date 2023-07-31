import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link } from 'react-router-dom';
import Swal from 'sweetalert2';

export default function EmailVerification() {

  const token = new URLSearchParams(window.location.search).get("token")

  const [contentTitle, setContentTitle] = useState("")
  const [email, setEmail] = useState("")
  const [cooldown, setCooldown] = useState(0)

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
  
  useEffect(()=>{
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/auth/verify-email?token=" + token
    })
    .then(response => {
      console.log(response)
      setContentTitle(response.data.message)
    })
    .catch(err => {
      setContentTitle(err.response.data.message)
    })
  }, []);

  const submitHandler = (e) => {
    e.preventDefault();
    if(cooldown===0) {
      axios({
        method: "POST",
        url: process.env.REACT_APP_API_URL + "/api/auth/resend-verification",
        data: {email}
      })
      .then((response) => {
        Toast.fire({
          icon: 'error',
          title: response.data.data.message
        })
        var timeLeft = 30
        var timer = setInterval(() => {
          if(timeLeft===0) {
            clearInterval(timer)
          }
          setCooldown(timeLeft)
          timeLeft -= 1
        }, 1000)
      })
      .catch((err) => {
        Toast.fire({
          icon: 'error',
          title: err.response.data.message
        })
      })
    }
  }

  if (window.location.href === "http://localhost:3000/email-verification") {
    return (
      <div className="hold-transition login-page">
          <div className="login-box">
              <div className="login-logo">
                  <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
              </div>
              <div className="card mt-5">
                  <div className="card-body login-card-body">
                      <p className="login-box-msg">Enter your email</p>

                      <form onSubmit={submitHandler} method="POST">
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
                          <button type="submit" className={`btn btn-primary btn-block ${cooldown!==0?"disabled":""}`}>{`Request verification ${cooldown===0?"":cooldown}`}</button>
                          {/* <a href="/register" className="btn btn-outline-primary btn-block">Back to login</a> */}
                      </form>
                      <div className='py-2'>
                          <Link to="/login">Back to login</Link>
                      </div>
                  </div>
              </div>
          </div>
      </div>
    )
  }

  else {

    return (
      <div className="lockscreen-wrapper">
        <div className="lockscreen-logo">
          <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
        </div>
        <div className="help-block text-center my-5">
          <h1 className="font-weight-bold">{contentTitle}</h1>
          <a href={`http://localhost:3000/login`} className="btn btn-primary mt-3">Back to Login</a>
        </div>
        <div className="lockscreen-footer text-center">
          <strong>Copyright &copy; 2023 <a href="https://www.amartek.id/">Bumi Amartha Teknologi Mandiri</a></strong>
          <div>All rights reserved.</div>
        </div>
      </div>
    )
  }
}
