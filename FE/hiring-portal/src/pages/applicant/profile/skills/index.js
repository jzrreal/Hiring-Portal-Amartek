import { React } from "react";
import { NavLink } from "react-router-dom";

import Navbar from "../../../../components/navbar";
import Sidebar from "../../../../components/sidebar";
import Footer from "../../../../components/footer";
import MenuApplicantProfile from "../../../../components/menu-profile-applicant";

function Index() {
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
        <section className="content-header">
          <div className="container-fluid">
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>Profile</h1>
              </div>
              <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item"><NavLink to="/applicant/dashboard">Dashboard</NavLink></li>
                  <li className="breadcrumb-item"><NavLink to="/applicant/profile/personal-information">Profile</NavLink></li>
                  <li className="breadcrumb-item active">Skills</li>
                </ol>
              </div>
            </div>
          </div>
          {/* Content Header */}
        </section>

        {/* Main Content */}
        <section className="content">
          <div className="container-fluid">
            <div className="row">
              <div className="col-md-3">
                <div className="card card-primary">
                  <div className="card-header">
                    <h3 className="card-title">Menu Profile</h3>
                  </div>
                  <div className="card-body">
                    <MenuApplicantProfile />
                  </div>
                </div>
              </div>
              <div className="col-md-9">
                <div className="card">
                  <div className="card-body">
                    <div className="d-flex justify-content-between align-items-center">
                      <h5 className="font-weight-bold m-0">Your Skills</h5>
                      <NavLink to="/applicant/profile/skills/add" className="btn btn-outline-primary"><i className="fas fa-plus mr-2"></i> Add Data</NavLink>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h5 className="font-weight-bold m-0">Basic</h5>
                    <div className="my-3">
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h5 className="font-weight-bold m-0">Novice</h5>
                    <div className="my-3">
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h5 className="font-weight-bold m-0">Indermediate</h5>
                    <div className="my-3">
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h5 className="font-weight-bold m-0">Advance</h5>
                    <div className="my-3">
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h5 className="font-weight-bold m-0">Expert</h5>
                    <div className="my-3">
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                      <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section >
        {/* Main Contet */}
      </div >
      {/* Content */}

      {/* Footer */}
      <Footer />
      {/* Footer */}
    </div >
  );
}

export default Index;
