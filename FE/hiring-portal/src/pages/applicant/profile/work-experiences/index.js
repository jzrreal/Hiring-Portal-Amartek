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
                  <li className="breadcrumb-item active">Work Experiences</li>
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
                    <div className="d-flex justify-content-between align-items-center mb-3">
                      <h5 className="font-weight-bold m-0">Your Work Experiences</h5>
                      <NavLink to="/applicant/profile/personal-information/edit" className="btn btn-outline-primary"><i className="fas fa-plus mr-2"></i> Add Data</NavLink>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <div class="timeline timeline-inverse">
                      <div class="time-label pt-4">
                        <span class="text-primary border border-primary px-3">2022 - 2023</span>
                      </div>
                      <div>
                        <i class="fas fa-briefcase bg-info"></i>
                        <div class="timeline-item">
                          <span class="time text-primary font-weight-bold">
                            <i class="far fa-user mr-2"></i> Front End Developer
                          </span>
                          <h3 class="timeline-header text-primary font-weight-bold">Bumi Amartha Teknologi Mandiri</h3>
                          <div class="timeline-body">
                            Etsy doostang zoodles disqus groupon greplin
                            oooj voxy zoodles, weebly ning heekya handango
                            imeem plugg dopplr jibjab, movity jajah plickers
                            sifteo edmodo ifttt zimbra. Babblely odeo
                            kaboodle quora plaxo ideeli hulu weebly
                            balihoo...
                          </div>
                        </div>
                      </div>
                      <div class="time-label pt-4">
                        <span class="text-primary border border-primary px-3">2021 - 2022</span>
                      </div>
                      <div>
                        <i class="fas fa-briefcase bg-info"></i>
                        <div class="timeline-item">
                          <span class="time text-primary font-weight-bold">
                            <i class="far fa-user mr-2"></i> Front End Developer
                          </span>
                          <h3 class="timeline-header text-primary font-weight-bold">Mandiri Internasional Teknologi</h3>
                          <div class="timeline-body">
                            Etsy doostang zoodles disqus groupon greplin
                            oooj voxy zoodles, weebly ning heekya handango
                            imeem plugg dopplr jibjab, movity jajah plickers
                            sifteo edmodo ifttt zimbra. Babblely odeo
                            kaboodle quora plaxo ideeli hulu weebly
                            balihoo...
                          </div>
                        </div>
                      </div>
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
