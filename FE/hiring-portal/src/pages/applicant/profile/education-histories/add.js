import { React, useEffect, useState } from "react";
import { NavLink, useNavigate, useOutletContext } from "react-router-dom";
import Swal from 'sweetalert2';
import dateFormat from 'dateformat';
import axios from "axios";

import Navbar from "../../../../components/navbar";
import Sidebar from "../../../../components/sidebar";
import Footer from "../../../../components/footer";
import MenuApplicantProfile from "../../../../components/menu-profile-applicant";

function Add() {
    const navigate = useNavigate()
    const [inputData, setInputData] = useState({});
    const token = useOutletContext()

    // Alert Toast
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

    // Add Data
    function handleSubmit(e) {
        e.preventDefault()
        console.log(inputData);
        axios({
            method: "POST",
            url: process.env.REACT_APP_API_URL + "/api/education-histories",
            headers: {
                Authorization: "Bearer " + token
            },
            data: inputData
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success save data'
            }),
            navigate('/applicant/profile/education-histories', { replace: false })
        ).catch(function (error) {
            console.log(error)
        })
    }

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
                                    <li className="breadcrumb-item"><NavLink to="/applicant/profile/education-histories">Education Histories</NavLink></li>
                                    <li className="breadcrumb-item active">Add Education Histories</li>
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
                                            <h5 className="font-weight-bold m-0">Add Education Histories</h5>
                                        </div>
                                    </div>
                                </div>
                                <div className="card">
                                    <div className="card-body">
                                        <form onSubmit={handleSubmit}>
                                            <div className="form-group">
                                                <label for="name">School / University Name</label>
                                                <input type="text" className="form-control" id="name" onChange={e => setInputData({ ...inputData, name: e.target.value })} placeholder="Set School / University Name" />
                                            </div>
                                            <div className="form-group">
                                                <label for="level">Level</label>
                                                <input type="text" className="form-control" id="level" onChange={e => setInputData({ ...inputData, level: e.target.value })} placeholder="Set Level" />
                                            </div>
                                            <div className="form-group">
                                                <label for="major">Major</label>
                                                <input type="text" className="form-control" id="major" onChange={e => setInputData({ ...inputData, major: e.target.value })} placeholder="Set Major" />
                                            </div>
                                            <div className="row">
                                                <div className="col">
                                                    <div className="form-group">
                                                        <label for="year_start">Start Year</label>
                                                        <input type="month" className="form-control" id="year_start" onChange={e => setInputData({ ...inputData, year_start: dateFormat(e.target.value, "yyyy") })} />
                                                    </div>
                                                </div>
                                                <div className="col">
                                                    <div className="form-group">
                                                        <label for="year_end">End Year</label>
                                                        <input type="month" className="form-control" id="year_end" onChange={e => setInputData({ ...inputData, year_end: dateFormat(e.target.value, "yyyy") })} />
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/applicant/profile/education-histories" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                                                <button className="btn btn-primary">Save changes</button>
                                            </div>
                                        </form>
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

export default Add;
