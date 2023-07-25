import React, { useEffect, useState } from 'react'
import axios from 'axios';

export default function EmailVerification() {

  const token = new URLSearchParams(window.location.search).get("token")

  const [contentTitle, setContentTitle] = useState("")
  
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
