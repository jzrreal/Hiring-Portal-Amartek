import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext } from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

function Add() {
    const navigate = useNavigate()
    const [inputData, setInputData] = useState({ name: '', point: '' })
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

    // Add Data
    function handleSubmit(e) {
        e.preventDefault()
        axios({
            method: "POST",
            url: process.env.REACT_APP_API_URL + "/api/question-levels",
            headers: {
                Authorization: "Bearer " + token
            },
            data: inputData
        }).then(response => {
            if (response.data.status === 200) {
                Toast.fire({
                    icon: "success",
                    title: response.data.message
                })
                navigate("/human-resource/question-level", { replace: false })
            }
        }).catch((error) => {
            Toast.fire({
                icon: "error",
                title: error.response.data.message
            })
        });
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
                <div className="content-header">
                    <div className="container-fluid">
                        <div className="row mb-2">
                            <div className="col-sm-6">
                                <h1 className="m-0">Create a New Question Level</h1>
                            </div>
                            <div className="col-sm-6">
                                <ol className="breadcrumb float-sm-right">
                                    <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                                    <li className="breadcrumb-item"><NavLink to="/human-resource/question-level">Question Level</NavLink></li>
                                    <li className="breadcrumb-item active">Add Question Level</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Content Header */}

                {/* Main Content */}
                <section className="content">
                    <div className="container-fluid">
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <form onSubmit={handleSubmit}>
                                            <div className="form-group">
                                                <label for="name">Name</label>
                                                <input type="text" className="form-control" id="name" onChange={e => setInputData({ ...inputData, name: e.target.value })} placeholder="Question Level Name" />
                                            </div>
                                            <div className="form-group">
                                                <label for="point">Point</label>
                                                <input type="number" className="form-control" id="point" onChange={e => setInputData({ ...inputData, point: e.target.value })} placeholder="Point" />
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/question-level" type="button" className="btn btn-secondary mr-2">Back</NavLink>
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
            <Footer />
            {/* Footer */}
        </div>
    )
}

export default Add