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
    const [jobResponse, setJobResponse] = useState({
        title: '', description: '', requirements: '',
        job_level: '', job_function: '',
        open_until: '', vacancy: '', closed: '', job_function_id: 0, job_level_id: 0
    })

    const [jobLevels, setJobLevels] = useState([])
    const [jobFunctions, setJobFunctions] = useState([])

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
            setJobResponse(response.data.data);
            // console.log(response.data.data)
        })
        .catch(function (error) {
            console.log(error);
        });

        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-levels"
        })
        .then((response) => {
            // console.log(response.data.data)
            setJobLevels(response.data.data)
            jobResponse.job_level_id = 1
        });

        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/job-functions"
        })
        .then((response) => {
            // console.log(response.data.data)
            setJobFunctions(response.data.data)
            jobResponse.job_function_id = 1
            // delete jobResponse["job_function"]
        })
    }, [])

    const jobLevelOptions = (jobLevel) => {
        return (
            <option>{jobLevel.name}</option>
        );
    }

    const jobFunctionOptions = (jobFunction) => {
        return (
            <option>{jobFunction.name}</option>
        );
    }

    // Edit Data
    function handleSubmit(e) {
        // e.preventDefault()

        jobResponse.job_level_id = jobLevels.find(({name}) => name===jobResponse.job_level).id
        jobResponse.job_function_id = jobFunctions.find(({name}) => name===jobResponse.job_function).id
        // setJobResponse({...jobResponse, job_level_id: jobResponse.job_level},)
        // delete jobResponse["job_level"]
        // delete jobResponse["job_function"]
        // setJobResponse({...jobResponse, job_function_id: jobFunctions.find(({name}) => name===jobResponse.job_function).id})
        delete jobResponse["job_level"]
        delete jobResponse["job_function"]
        console.log(jobResponse);
        // console.log(jobLevels);
        // setJobRequest(jobResponse)
        
        // setJobRequest({...jobRequest, job_level: jobLevels.find(({name}) => name===jobResponse.job_level).id})
        // setJobRequest({...jobRequest, job_function: jobFunctions.find(({name}) => name===jobResponse.job_function).id})
        // console.log(jobRequest);
        axios({
            method: "PUT",
            url: process.env.REACT_APP_API_URL + "/api/job-posts/" + id,
            data: jobResponse
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success update data'
            }),
            navigate('/human-resource/job-post', { replace: false })
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
                                    <div className="card mb-4" hidden>
                                        <div className="card-body">
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="id">ID Job Post</label>
                                                        <input type="text" className="form-control" id="id" value={jobResponse.id} readOnly />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="updated_at">Update At</label>
                                                        <input type="text" className="form-control" id="updated_at" value={jobResponse.updated_at} readOnly />
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
                                                        <input type="text" className="form-control" id="title" value={jobResponse.title} onChange={e => setJobResponse({ ...jobResponse, title: e.target.value })} placeholder="Title Job Name" />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Job Level</label>
                                                        <select className='form-control' id='job_level' value={jobResponse.job_level} onChange={e => setJobResponse({ ...jobResponse, job_level: jobLevels.find(({name}) => name===e.target.value).name})} >
                                                            {jobLevels.map(jobLevelOptions)}
                                                        </select>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="title">Job Function</label>
                                                        <select className='form-control' id='job_level' value={jobResponse.job_function} onChange={e => setJobResponse({ ...jobResponse, job_function: jobFunctions.find(({name}) => name===e.target.value).name })} >
                                                            {jobFunctions.map(jobFunctionOptions)}
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-group">
                                                <label for="description">Desctiption Job</label>
                                                <textarea className="form-control" id="description" value={jobResponse.description} onChange={e => setJobResponse({ ...jobResponse, description: e.target.value })} />
                                            </div>
                                            <div className="form-group">
                                                <label for="requirements">Requirement Job</label>
                                                <textarea className="form-control" id="requirements" value={jobResponse.requirements} onChange={e => setJobResponse({ ...jobResponse, requirements: e.target.value })} />
                                            </div>
                                            <div className='row'>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="vacancy">Vacancy</label>
                                                        <input type="number" className="form-control" id="vacancy" value={jobResponse.vacancy} onChange={e => setJobResponse({ ...jobResponse, vacancy: e.target.value })} />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="open_until">Open Until</label>
                                                        <input type="date" className="form-control" id="open_until" value={jobResponse.open_until} onChange={e => setJobResponse({ ...jobResponse, open_until: e.target.value })} />
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="open_until">Open Until</label>
                                                        <select className='form-control' id='job_level' value={jobResponse.closed} onChange={e => setJobResponse({ ...jobResponse, closed: e.target.value })} >
                                                            <option value={true} >True</option>
                                                            <option value={false}>False</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/job-post" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                                                <button type='submit' className="btn btn-primary">Save changes</button>
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