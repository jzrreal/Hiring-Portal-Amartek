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
        title: '', description: '', requirements: '',
        job_level_id: 1, job_function_id: 1,
        open_until: '', vacancy: ''
    })

    const [jobLevels, setJobLevels] = useState([]);
    const [jobFunction, setJobFunction] = useState([]);

    const jobLevelDropdown = (value) => {
        return (
            <option>{value.name}</option>
        )
    }

    const jobFunctionDropdown = (value) => {
        return (
            <option>{value.name}</option>
        )
    }

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
            headers: {
                Authorization : "Bearer " + localStorage.getItem("authToken")
            },
            data: inputData
        })
        .then( (response) => {
            Toast.fire({
                icon: 'success',
                title: 'Success save data'
        })
        }
        )
        .catch(function (error) { console.log(error); })
    }

    useEffect( () => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-levels"
        })
        .then((response) => {
            console.log(response.data.data)
            setJobLevels(response.data.data)
        })

        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-functions"
        })
        .then((response) => {
            console.log(response.data.data)
            setJobFunction(response.data.data)
        })
    }, [])

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
                                                        <input type="text" className="form-control" id="title" onChange={e => setInputData({ ...inputData, title: e.target.value })}/>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Job Level</label>
                                                        <select className='form-control' id='job_level' onChange={e => setInputData({...inputData, job_level_id: jobLevels.find(({name}) => name===e.target.value).id}) } >
                                                            {jobLevels.map(jobLevelDropdown)}
                                                        </select>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Job Function</label>
                                                        <select className='form-control' id='job_level' onChange={e => setInputData({ ...inputData, job_function_id: jobFunction.find( ({name}) => name===e.target.value ).id })} >
                                                            {jobFunction.map(jobFunctionDropdown)}
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
                                                <textarea className="form-control" id="requirement" onChange={e => setInputData({ ...inputData, requirements: e.target.value })} placeholder="Requirement Job Name" />
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
                                                        <label for="vacancy">Vacancy</label>
                                                        <input type="number" className="form-control" id="vacancy" onChange={e => setInputData({ ...inputData, vacancy: e.target.value })} />
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/job-post" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                                                <button type="submit" className="btn btn-primary">Save changes</button>
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