import {React, useEffect, useState} from 'react'
import {NavLink, useNavigate, useOutletContext} from "react-router-dom";
import Swal from "sweetalert2";
import axios from "axios";
import Navbar from "../../../../components/navbar";
import Sidebar from "../../../../components/sidebar";
import MenuApplicantProfile from "../../../../components/menu-profile-applicant";
import Footer from "../../../../components/footer";


function Edit() {
    const navigate = useNavigate()
    const [data, setData] = useState({});
    const token = useOutletContext();

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

    // Add Data
    function handleSubmit(e) {
        e.preventDefault()
        data.gender = data.gender.toUpperCase();
        delete data.id;
        console.log(data);
        axios({
            method: "PUT",
            url: process.env.REACT_APP_API_URL + "/api/applicants",
            headers: {
                Authorization: "Bearer " + token
            },
            data: data
        }).then(response => {
                if (response.data.status === 200) {
                    Toast.fire({
                        icon: 'success',
                        title: response.data.message
                    });
                    navigate('/applicant/profile/personal-information', {replace: false})
                }
            }
        ).catch(function (error) {
            Toast.fire({
                icon: "error",
                title: error.response.data.message
            })
        })
    }

    return (
        <div className="wrapper">
            {/* Navbar */}
            <Navbar/>
            {/* Navbar */}

            {/* Sidebar */}
            <Sidebar/>
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
                                    <li className="breadcrumb-item"><NavLink
                                        to="/applicant/dashboard">Dashboard</NavLink></li>
                                    <li className="breadcrumb-item"><NavLink
                                        to="/applicant/profile/personal-information">Profile</NavLink></li>
                                    <li className="breadcrumb-item"><NavLink
                                        to="/applicant/profile/personal-information">Personal Information</NavLink></li>
                                    <li className="breadcrumb-item active">Edit Personal Information</li>
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
                                        <MenuApplicantProfile/>
                                    </div>
                                </div>
                            </div>
                            <div className="col-md-9">
                                <div className="card">
                                    <div className="card-body">
                                        <div className="d-flex justify-content-between align-items-center">
                                            <h5 className="font-weight-bold m-0">Edit Personal Information</h5>
                                        </div>
                                    </div>
                                </div>
                                <div className="card">
                                    <div className="card-body">
                                        <form onSubmit={handleSubmit}>
                                            <div className="form-group">
                                                <label for="full_name">Fullname</label>
                                                <input type="text" className="form-control" id="full_name"
                                                       value={data.full_name}
                                                       onChange={e => setData({...data, full_name: e.target.value})}/>
                                            </div>
                                            <div className="form-group">
                                                <label for="email">Email</label>
                                                <input type="text" className="form-control" id="email"
                                                       value={data.email}
                                                       onChange={e => setData({...data, email: e.target.value})}
                                                       readOnly/>
                                            </div>
                                            <div className="form-group">
                                                <label for="birth_date">Birth Date</label>
                                                <input type="date" className="form-control" id="birth_date"
                                                       value={data.birth_date}
                                                       onChange={e => setData({...data, birth_date: e.target.value})}/>
                                            </div>
                                            <div className="form-group">
                                                <label for="phone_number">Phone Number</label>
                                                <input type="text" className="form-control" id="phone_number"
                                                       value={data.phone}
                                                       onChange={e => setData({...data, phone: e.target.value})}/>
                                            </div>
                                            <div className="form-group">
                                                <label for="summary">Summary</label>
                                                <textarea className="form-control" id="summary" value={data.summary}
                                                          onChange={e => setData({...data, summary: e.target.value})}/>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/applicant/profile/personal-information" type="button"
                                                         className="btn btn-secondary mr-2">Back</NavLink>
                                                <button className="btn btn-primary">Save changes</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                {/* Main Contet */}
            </div>
            {/* Content */}

            {/* Footer */}
            <Footer/>
            {/* Footer */}
        </div>
    );
}

export default Edit