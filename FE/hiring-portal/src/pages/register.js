import React from 'react'

function Register() {
  return (
    <div className="hold-transition login-page">
      <div className="login-box" style={{ width: 800 }}>
        <div className="login-logo">
          <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
        </div>
        <div className="card mt-5">
          <div className="card-body login-card-body">
            <p className="login-box-msg">Register a new applicant</p>
            <form action="../../index3.html" method="POST">
              <div className="row">
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputEmail1">Fullname</label>
                    <div className="input-group mb-3">
                      <input type="text" className="form-control" placeholder="Fullname" />
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
                      <input type="email" className="form-control" placeholder="Email" />
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
                      <input type="password" className="form-control" placeholder="Password" />
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
                      <input type="text" className="form-control" placeholder="Phone Number" />
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
                      <input type="date" className="form-control" />
                    </div>
                  </div>
                </div>
                <div className="col">
                  <div className="form-group">
                    <label for="exampleInputPassword1">Gender</label>
                    <div className="input-group mb-3">
                      <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1" />
                        <label className="form-check-label" for="inlineRadio1">Man</label>
                      </div>
                      <div className="form-check form-check-inline">
                        <input className="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2" />
                        <label className="form-check-label" for="inlineRadio2">Woman</label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div className="mt-3">
                <button type="submit" className="btn btn-primary btn-block">Register Now</button>
                <a href="/login" className="btn btn-outline-primary btn-block" >Back to Login</a>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default Register