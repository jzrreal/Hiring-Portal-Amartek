import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

function Add() {
    const navigate = useNavigate()
    const [inputData, setInputData] = useState({
        title: '', description: '', requirement: '',
        post_at: '', open_until: '', updated_at: '',
        vacancy: '', closed: '', jobLevel: '', jobFunction: '', user: ''
    })

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
    function handleSubmit() {
        axios({
            method: "POST",
            url: process.env.REACT_APP_API_URL + "/api/job-posts",
            data: inputData
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success save data'
            }),
            navigate('/human-resource/job-post', { replace: true })
        ).catch(function (error) { console.log(error); })
    }

    return (
        <>
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
                                    <h1 className="m-0">Create a New Job Post</h1>
                                </div>
                                <div className="col-sm-6">
                                    <ol className="breadcrumb float-sm-right">
                                        <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                                        <li className="breadcrumb-item"><NavLink to="/human-resource/job-post">Job Post</NavLink></li>
                                        <li className="breadcrumb-item active">Add Job Post</li>
                                    </ol>
                                </div>
                            </div>
                        </div>
                    </div>
                    {/* Content Header */}

                    {/* Main Content */}
                    <section className="content">
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <form onSubmit={handleSubmit}>
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Title Job</label>
                                                        <input type="text" className="form-control" id="title" onChange={e => setInputData({ ...inputData, title: e.target.value })} placeholder="Title Job Name" />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Job Level</label>
                                                        <select className='form-control' id='job_level' onChange={e => setInputData({ ...inputData, job_level: e.target.value })} >
                                                            <option>Select Job Level</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Job Function</label>
                                                        <select className='form-control' id='job_level' onChange={e => setInputData({ ...inputData, job_fucntion: e.target.value })} >
                                                            <option>Select Job Function</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-group">
                                                <label for="description">Desctiption Job</label>
                                                <textarea className="form-control" id="description" onChange={e => setInputData({ ...inputData, description: e.target.value })} placeholder="Description Job Name" />
                                            </div>
                                            <div className="form-group">
                                                <label for="requirement">Requirement Job</label>
                                                <textarea className="form-control" id="requirement" onChange={e => setInputData({ ...inputData, requirement: e.target.value })} placeholder="Requirement Job Name" />
                                            </div>
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="open_until">Open Until</label>
                                                        <input type="date" className="form-control" id="open_until" onChange={e => setInputData({ ...inputData, open_until: e.target.value })} />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="closed">Job Close</label>
                                                        <input type="date" className="form-control" id="closed" onChange={e => setInputData({ ...inputData, closed: e.target.value })} />
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/job-post" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                                                <button className="btn btn-primary">Save changes</button>
                                            </div>
                                        </form>
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
        </>
    )
}

export default Add