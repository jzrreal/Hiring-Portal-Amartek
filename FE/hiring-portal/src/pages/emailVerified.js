import React from 'react'
import { Link, NavLink, useNavigate } from 'react-router-dom'

export default function EmailVerified() {
  const navigate = useNavigate();
  return (
    <div class="lockscreen-wrapper">
      <div class="lockscreen-logo">
        <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
      </div>
      <div class="help-block text-center my-5">
        <h1 className="font-weight-bold">Email verification successfull!</h1>
        <a href={`http://localhost:3000/login`} className="btn btn-primary mt-3">Back to Login</a>
      </div>
      <div class="lockscreen-footer text-center">
        <strong>Copyright &copy; 2023 <a href="https://www.amartek.id/">Bumi Amartha Teknologi Mandiri</a></strong>
        <div>All rights reserved.</div>
      </div>
    </div>
  )
}