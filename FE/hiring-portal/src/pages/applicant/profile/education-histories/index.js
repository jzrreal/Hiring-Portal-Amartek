import { React, useEffect, useState } from "react";
import {NavLink, useNavigate, useOutletContext} from "react-router-dom";
import axios from "axios";

import Navbar from "../../../../components/navbar";
import Sidebar from "../../../../components/sidebar";
import Footer from "../../../../components/footer";
import MenuApplicantProfile from "../../../../components/menu-profile-applicant";
import Swal from "sweetalert2";

function Index() {
    const navigate = useNavigate();
    const [data, setData] = useState([{}]);
    const token = useOutletContext();
    const [trigger, setTrigger] = useState(false);

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

    }, [trigger])

    // Delete Data
    function deleteData(id) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            confirmButtonText: 'Yes, Delete Now!'
        }).then((result) => {
            if (result.isConfirmed) {
                axios({
                    method: "DELETE",
                    url: process.env.REACT_APP_API_URL + "/api/education-histories/" + id,
                    headers: {
                        Authorization: "Bearer " + token
                    }
                }).then(
                    Toast.fire({
                        icon: 'success',
                        title: 'Success delete data'
                    }),
                    setTrigger(!trigger),
                    navigate('/applicant/profile/education-histories', { replace: false })
                ).catch(function (error) { console.log(error); })
            }
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
                                    <li className="breadcrumb-item active">Education Histories</li>
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
                                                    <button onClick={() => deleteData(data.historiesId)} className="btn btn-sm btn-outline-danger"><i className="fas fa-trash-alt mr-2"></i> Delete Data</button>
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
