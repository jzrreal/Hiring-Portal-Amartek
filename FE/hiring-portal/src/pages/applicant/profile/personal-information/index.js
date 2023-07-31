import { React, useEffect, useState } from "react";
import { NavLink, useOutletContext } from "react-router-dom";
import axios from "axios";
import dateFormat from 'dateformat';

import Navbar from "../../../../components/navbar";
import Sidebar from "../../../../components/sidebar";
import Footer from "../../../../components/footer";
import MenuApplicantProfile from "../../../../components/menu-profile-applicant";

function Index() {
    const [data, setData] = useState({})
    const token = useOutletContext()

    // Get Profile
    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/profiles/applicants",
            headers: {
                Authorization: "Bearer " + token
            }
        })
            .then(response => {
                setData(response.data.data)
            })
    }, [])

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
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Profile</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item"><NavLink to="/applicant/dashboard">Dashboard</NavLink></li>
                                    <li class="breadcrumb-item"><NavLink to="/applicant/profile/personal-information">Profile</NavLink></li>
                                    <li class="breadcrumb-item active">Personal Information</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    {/* Content Header */}
                </section>

                {/* Main Content */}
                <section class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="card card-primary">
                                    <div class="card-header">
                                        <h3 class="card-title">Menu Profile</h3>
                                    </div>
                                    <div class="card-body">
                                        <MenuApplicantProfile />
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-9">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="d-flex justify-content-between align-items-center mb-3">
                                            <h5 className="font-weight-bold m-0">Your Personal Information</h5>
                                            <NavLink className="btn btn-outline-warning"><i className="fas fa-edit mr-2"></i> Edit Data</NavLink>
                                        </div>
                                        <hr />
                                        <div className="form-group">
                                            <label>Fullname</label>
                                            <input class="form-control" value={data.full_name} readOnly />
                                        </div>
                                        <div className="form-group">
                                            <label>Email</label>
                                            <input class="form-control" value={data.email} readOnly />
                                        </div>
                                        <div className="form-group">
                                            <label>Last Education</label>
                                            <input class="form-control" value={data.last_education} readOnly />
                                        </div>
                                        <div className="form-group">
                                            <label>Age</label>
                                            <input class="form-control" value={data.age + " Years"} readOnly />
                                        </div>
                                        <div className="form-group">
                                            <label>Birth Date</label>
                                            <input class="form-control" value={dateFormat(data.birth_date, "dd mmmm yyyy")} readOnly />
                                        </div>
                                        <div className="form-group">
                                            <label>Phone Number</label>
                                            <input class="form-control" value={data.phone} readOnly />
                                        </div>
                                        <div className="form-group">
                                            <label>Summary</label>
                                            <textarea class="form-control" value={data.summary} readOnly />
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
