import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import dateFormat from 'dateformat';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

function Edit() {
    const navigate = useNavigate()
    const { id } = useParams();
    const [data, setData] = useState([])
    const [jobLevels, setJobLevels] = useState([]);
    const [jobFunction, setJobFunction] = useState([]);

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

    const jobLevelDropdown = (value) => {
        return (
            <option className="text-capitalize">{value.name}</option>
        )
    }

    const jobFunctionDropdown = (value) => {
        return (
            <option className="text-capitalize">{value.name}</option>
        )
    }

    // Get Data
    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-posts/" + id,
        })
            .then(function (response) {
                setData(response.data.data);
            })
            .catch(function (error) {
                console.log(error);
            })
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-levels"
        })
            .then((response) => {
                setJobLevels(response.data.data)
            })

        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-functions"
        })
            .then((response) => {
                setJobFunction(response.data.data)
            })
    }, [])

    // Edit Data
    function handleSubmit(e) {
        e.preventDefault()
        axios({
            method: "PUT",
            url: process.env.REACT_APP_API_URL + "/api/job-posts/" + id,
            data: data
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success update data'
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
                                    <h1 className="m-0">Edit Job Post</h1>
                                </div>
                                <div className="col-sm-6">
                                    <ol className="breadcrumb float-sm-right">
                                        <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                                        <li className="breadcrumb-item"><NavLink to="/human-resource/job-post">Job Post</NavLink></li>
                                        <li className="breadcrumb-item active">Edit Job Post</li>
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
                                <form onSubmit={handleSubmit}>
                                    <div className="card mb-4">
                                        <div className="card-body">
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="id">ID Job Post</label>
                                                        <input type="text" className="form-control" id="id" value={data.id} readOnly />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="updated_at">Update At</label>
                                                        <input type="text" className="form-control" id="updated_at" value={dateFormat(data.updated_at, "dd mmmm yyyy")} readOnly />
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div className="card">
                                        <div className="card-body">
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Title Job</label>
                                                        <input type="text" className="form-control" id="title" value={data.title} onChange={e => setData({ ...data, title: e.target.value })} placeholder="Title Job Name" />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="job_level">Job Level</label>
                                                        <select className='form-control text-capitalize' id='job_level' value={data.job_level} onChange={e => setData({ ...data, job_level_id: jobLevels.find(({ name }) => name === e.target.value).id })} >
                                                            {jobLevels.map(jobLevelDropdown)}
                                                        </select>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="job_function">Job Function</label>
                                                        <select className='form-control text-capitalize' id='job_function' value={data.job_function} onChange={e => setData({ ...data, job_function_id: jobFunction.find(({ name }) => name === e.target.value).id })} >
                                                            {jobFunction.map(jobFunctionDropdown)}
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-group">
                                                <label for="description">Desctiption Job</label>
                                                <textarea className="form-control" id="description" value={data.description} onChange={e => setData({ ...data, description: e.target.value })} />
                                            </div>
                                            <div className="form-group">
                                                <label for="requirements">Requirement Job</label>
                                                <textarea className="form-control" id="requirements" value={data.requirements} onChange={e => setData({ ...data, requirements: e.target.value })} />
                                            </div>
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="vacancy">Vacancy</label>
                                                        <input type="number" className="form-control" id="vacancy" value={data.vacancy} onChange={e => setData({ ...data, vacancy: e.target.value })} />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="open_until">Open Until</label>
                                                        <input type="date" className="form-control" id="open_until" value={data.open_until} onChange={e => setData({ ...data, open_until: e.target.value })} />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="closed">Job Close</label>
                                                        <input type="date" className="form-control" id="closed" value={data.closed} onChange={e => setData({ ...data, closed: e.target.value })} />
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/job-post" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                                                <button className="btn btn-primary">Save changes</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
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

export default Edit