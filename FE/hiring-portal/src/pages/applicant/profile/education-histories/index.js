import { React, useEffect, useState } from "react";
import { NavLink, useOutletContext } from "react-router-dom";
import axios from "axios";

import Navbar from "../../../../components/navbar";
import Sidebar from "../../../../components/sidebar";
import Footer from "../../../../components/footer";
import MenuApplicantProfile from "../../../../components/menu-profile-applicant";

function Index() {
    const [data, setData] = useState([{}]);
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
                axios({
                    method: "GET",
                    url: process.env.REACT_APP_API_URL + "/api/education-histories/applicants/" + response.data.data.id,
                    headers: {
                        Authorization: "Bearer " + token
                    }
                })
                    .then(response => {
                        setData(response.data.data)
                    })
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
                                    <li class="breadcrumb-item active">Education Histories</li>
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
                                        <div className="d-flex justify-content-between align-items-center">
                                            <h5 className="font-weight-bold m-0">Your Education Histories</h5>
                                            <NavLink to="/applicant/profile/education-histories/add" className="btn btn-outline-primary"><i className="fas fa-plus mr-2"></i> Add Data</NavLink>
                                        </div>
                                    </div>
                                </div>
                                {data.map((data) => {
                                    return (
                                        <div className="card">
                                            <div className="card-body">
                                                <div className="d-flex justify-content-between align-items-center">
                                                    <h6 className="font-weight-bold m-0">{data.name} <small className="text-muted ml-1" style={{ fontSize: 14 }}>({data.yearStart} - {data.yearEnd})</small></h6>
                                                    {/* <NavLink to={`/applicant/profile/education-histories/edit/${data.historiesId}`} className="btn btn-sm btn-warning"><i className="fas fa-edit mr-2"></i> Edit Data</NavLink> */}
                                                    <NavLink className="btn btn-sm btn-warning"><i className="fas fa-edit mr-2"></i> Edit Data</NavLink>
                                                </div>
                                                <span>{data.level} - {data.major}</span>
                                            </div>
                                        </div>
                                    );
                                })}
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
