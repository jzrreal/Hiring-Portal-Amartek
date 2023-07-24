import React from 'react'

function Login() {
    return (
        <div className="hold-transition login-page">
            <div className="login-box">
                <div className="login-logo">
                    <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
                </div>
                <div className="card mt-5">
                    <div className="card-body login-card-body">
                        <p className="login-box-msg">Sign In to start your session</p>
                        <form action="../../index3.html" method="POST">
                            <div className="form-group">
                                <label for="exampleInputEmail1">Email</label>
                                <div className="input-group mb-3">
                                    <input type="email" className="form-control" placeholder="Email" />
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
                                    <input type="password" className="form-control" placeholder="Password" />
                                    <div className="input-group-append">
                                        <div className="input-group-text">
                                            <span className="fas fa-lock"></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <a href="/human-resource/dashboard" type="submit" className="btn btn-primary btn-block">Sign In</a>
                            <a href="/register" className="btn btn-outline-primary btn-block">Register</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Login