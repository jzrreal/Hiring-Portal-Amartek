import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { Link } from 'react-router-dom';

export default function EmailVerification() {

  const token = new URLSearchParams(window.location.search).get("token")

  const [contentTitle, setContentTitle] = useState("")
  const [email, setEmail] = useState("")
  
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

  if (window.location.href === "http://localhost:3000/email-verification") {
    return (
      <div className="hold-transition login-page">
          <div className="login-box">
              <div className="login-logo">
                  <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
              </div>
              <div className="card mt-5">
                  <div className="card-body login-card-body">
                      <p className="login-box-msg">Sign In to start your session</p>

                      <form method="POST" onSubmit="">
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
                          <button type="submit" className="btn btn-primary btn-block">Sign In</button>
                          <a href="/register" className="btn btn-outline-primary btn-block">Register</a>
                      </form>
                      <div>
                          <Link to="/register">Resend email verification?</Link>
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
