import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

function Add() {
    const navigate = useNavigate()
    const [inputData, setInputData] = useState({ name: '' })

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
            url: process.env.REACT_APP_API_URL + "/api/questions",
            data: inputData
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success save data'
            }),
            navigate('/trainer/question', { replace: true })
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
                                    <h1 className="m-0">Create a New Question</h1>
                                </div>
                                <div className="col-sm-6">
                                    <ol className="breadcrumb float-sm-right">
                                        <li className="breadcrumb-item"><NavLink to="/trainer/dashboard">Dashboard</NavLink></li>
                                        <li className="breadcrumb-item"><NavLink to="/trainer/question">Question</NavLink></li>
                                        <li className="breadcrumb-item active">Add Question</li>
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
                                                        <label for="segment">Segment</label>
                                                        <select className="form-control" id="segment" onChange={e => setInputData({ ...inputData, segment: e.target.value })}>
                                                            <option>Select Segment</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label for="question_level">Qustion Level</label>
                                                        <select className="form-control" id="question_level" onChange={e => setInputData({ ...inputData, question_level: e.target.value })}>
                                                            <option>Select Question Level</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-group">
                                                <label for="question">Question</label>
                                                <textarea className="form-control" id="question" onChange={e => setInputData({ ...inputData, question: e.target.value })} placeholder="Set Question"></textarea>
                                            </div>
                                            <div className="form-group">
                                                <label for="question">Choice</label>
                                                <div className='row'>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice'/>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice'/>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice'/>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice'/>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice'/>
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/trainer/question" type="button" className="btn btn-secondary mr-2">Back</NavLink>
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