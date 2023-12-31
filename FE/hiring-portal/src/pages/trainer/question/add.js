import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext } from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

function Add() {
    const navigate = useNavigate()

    const [choice1, setChoice1] = useState("")
    const [bool1, setBool1] = useState(false)
    const [choice2, setChoice2] = useState("")
    const [bool2, setBool2] = useState(false)
    const [choice3, setChoice3] = useState("")
    const [bool3, setBool3] = useState(false)
    const [choice4, setChoice4] = useState("")
    const [bool4, setBool4] = useState(false)
    const [choice5, setChoice5] = useState("")
    const [bool5, setBool5] = useState(false)

    const [inputData, setInputData] = useState({
        question: '', segment: 'DATABASE', question_level_id: 1,
        choice_requests: [
            { choice: "", correct: false },
            { choice: "", correct: false },
            { choice: "", correct: false },
            { choice: "", correct: false },
            { choice: "", correct: false }
        ]
    })

    const [questionLevels, setQuestionLevels] = useState([{}])
    const token = useOutletContext()

    const questionLevelList = (questionLevel, index) => {
        return (
            <option key={index} className="text-capitalize">{questionLevel.name}</option>
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

    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/question-levels",
            headers: {
                Authorization: "Bearer " + token
            }
        })
            .then(response => {
                setQuestionLevels(response.data.data)
            })
    }, [])

    // Add Data
    function handleSubmit() {
        // manual karena pusing
        inputData.choice_requests[0].choice = choice1;
        inputData.choice_requests[1].choice = choice2;
        inputData.choice_requests[2].choice = choice3;
        inputData.choice_requests[3].choice = choice4;
        inputData.choice_requests[4].choice = choice5;

        inputData.choice_requests[0].correct = bool1;
        inputData.choice_requests[1].correct = bool2;
        inputData.choice_requests[2].correct = bool3;
        inputData.choice_requests[3].correct = bool4;
        inputData.choice_requests[4].correct = bool5;

        axios({
            method: "POST",
            url: process.env.REACT_APP_API_URL + "/api/questions",
            headers: {
                Authorization: "Bearer " + token
            },
            data: inputData
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success save data'
            }),
            navigate('/trainer/question', { replace: false })
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
                                                        <label htmlFor="segment">Segment</label>
                                                        <select className="form-control" id="segment" onChange={e => setInputData({ ...inputData, segment: e.target.value })}>
                                                            <option value="DATABASE">Database</option>
                                                            <option value="BASIC_PROGRAMMING">Basic Programming</option>
                                                            <option value="LOGIKA_MATEMATIKA">Logika Matematika</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div className='col'>
                                                    <div className="form-group">
                                                        <label htmlFor="question_level">Question Level</label>
                                                        <select className="form-control text-capitalize" id="question_level" onChange={e => setInputData({ ...inputData, question_level_id: questionLevels.find(({ name }) => name === e.target.value).questionLevelId })}>
                                                            {questionLevels.map(questionLevelList)}
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="form-group">
                                                <label htmlFor="question">Question</label>
                                                <textarea className="form-control" id="question" onChange={e => setInputData({ ...inputData, question: e.target.value })} placeholder="Set Question"></textarea>
                                            </div>
                                            <div className="form-group">
                                                <label htmlFor="question">Choice</label>
                                                <div className='row'>
                                                    {/* {inputData.choice_requests.map(choicesList)} */}
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice' onChange={e => setChoice1(e.target.value)} />
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" onChange={e => { setBool1(true); setBool2(false); setBool3(false); setBool4(false); setBool5(false) }} />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice' onChange={e => setChoice2(e.target.value)} />
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" onChange={e => { setBool1(false); setBool2(true); setBool3(false); setBool4(false); setBool5(false) }} />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice' onChange={e => setChoice3(e.target.value)} />
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" onChange={e => { setBool1(false); setBool2(false); setBool3(true); setBool4(false); setBool5(false) }} />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice' onChange={e => setChoice4(e.target.value)} />
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" onChange={e => { setBool1(false); setBool2(false); setBool3(false); setBool4(true); setBool5(false) }} />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <input className='form-control mb-3' placeholder='Set Choice' onChange={e => setChoice5(e.target.value)} />
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="radio" name="radio1" onChange={e => { setBool1(false); setBool2(false); setBool3(false); setBool4(false); setBool5(true) }} />
                                                            <label class="form-check-label">True</label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/trainer/question" type="button" className="btn btn-secondary mr-2">Back</NavLink>
                                                <button type='submit' className="btn btn-primary">Save changes</button>
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