import React, { useState } from 'react'
import { useNavigate, Link } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2';
import redirectManager from '../service/redirectManager';

function Login() {
    const navigate = useNavigate();

    const [email, setEmail] = useState()
    const [password, setPassword] = useState()

    const body = {
        email: email,
        password: password
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
    
      const handleSubmit = e => {
        e.preventDefault()
    
        axios({
          method: "POST",
          url: process.env.REACT_APP_API_URL + "/api/auth/login",
          data: body
          })
        .then(response => {
            if(response.data.status === 200){
                localStorage.setItem("authToken", response.data.data)

                axios({
                    method: "GET",
                    url: process.env.REACT_APP_API_URL + "/api/profiles",
                    headers: {
                        Authorization : "Bearer " + localStorage.getItem("authToken")
                    }
                })
                .then(response => {
                    localStorage.setItem("role", response.data.data.role)
                    navigate(redirectManager(localStorage.getItem("role")))
                })
            }
        })
        .catch((error) => {
        Toast.fire({
            icon:"error",
            title: error.response.data.message
            })
        });
      }

    return (
        <div className="hold-transition login-page">
            <div className="login-box">
                <div className="login-logo">
                    <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
                </div>
                <div className="card mt-5">
                    <div className="card-body login-card-body">
                        <p className="login-box-msg">Sign In to start your session</p>

                        <form method="POST" onSubmit={handleSubmit}>
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
                            <div className="form-group">
                                <label for="exampleInputEmail1">Password</label>
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

export default Login