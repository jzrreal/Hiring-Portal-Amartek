import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext, useParams } from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

export default function Edit() {
    const navigate = useNavigate()
    const { id } = useParams();
    const token = useOutletContext()

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

    const [inputData, setInputData] = useState({})

    const [questionLevels, setQuestionLevels] = useState([{}])

    const questionLevelList = (questionLevel, index) => {
        return (
            <option key={index}>{questionLevel.name}</option>
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

    // Get Data
    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/questions/" + id,
            headers: {
                Authorization: "Bearer " + token
            }
        })
            .then(function (response) {
                setInputData(response.data.data);
                setChoices(response.data.data.choices)
                setBool(response.data.data.choices)

            })
            .catch(function (error) {
                console.log(error);
            });

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

    function setChoices(questionResponseList) {
        setChoice1(questionResponseList[0].choice)
        setChoice2(questionResponseList[1].choice)
        setChoice3(questionResponseList[2].choice)
        setChoice4(questionResponseList[3].choice)
        setChoice5(questionResponseList[4].choice)
    }

    function setBool(questionResponseList) {
        setBool1(questionResponseList[0].correct)
        setBool2(questionResponseList[1].correct)
        setBool3(questionResponseList[2].correct)
        setBool4(questionResponseList[3].correct)
        setBool5(questionResponseList[4].correct)
    }

    // Edit Data
    const handleSubmit = (e) => {
        e.preventDefault()

        inputData.choices[0].choice = choice1;
        inputData.choices[1].choice = choice2;
        inputData.choices[2].choice = choice3;
        inputData.choices[3].choice = choice4;
        inputData.choices[4].choice = choice5;

        inputData.choices[0].correct = bool1;
        inputData.choices[1].correct = bool2;
        inputData.choices[2].correct = bool3;
        inputData.choices[3].correct = bool4;
        inputData.choices[4].correct = bool5;

        inputData.question_level_id = questionLevels.find(({ name }) => name === inputData.question_level).questionLevelId
        inputData.choice_requests = inputData.choices

        axios({
            method: "PUT",
            url: process.env.REACT_APP_API_URL + "/api/questions/" + id,
            data: inputData,
            headers: {
                Authorization: "Bearer " + token
            }
        }).
            then((reso) => {
                console.log(reso);
                Toast.fire({
                    icon: 'success',
                    title: 'Success update data'
                })
                navigate('/trainer/question', { replace: false })
            }
            )
            .catch(function (error) { console.log(error); })
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
                        <div className="container-fluid">
                            <div className="row">
                                <div className="col-12">
                                    <div className="card">
                                        <div className="card-body">
                                            <form onSubmit={handleSubmit}>
                                                <div className='row'>
                                                    <div className='col'>
                                                        <div className="form-group">
                                                            <label htmlFor="segment">Segment</label>
                                                            <select className="form-control" id="segment" value={inputData.segment} onChange={e => setInputData({ ...inputData, segment: e.target.value })}>
                                                                <option value="DATABASE">Database</option>
                                                                <option value="BASIC_PROGRAMMING">Basic Programming</option>
                                                                <option value="LOGIKA_MATEMATIKA">Logika Matematika</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div className='col'>
                                                        <div className="form-group">
                                                            <label htmlFor="question_level">Question Level</label>
                                                            <select className="form-control" id="question_level" value={inputData.question_level} onChange={e => setInputData({ ...inputData, question_level: questionLevels.find(({ name }) => name === e.target.value).name })}>
                                                                {questionLevels.map(questionLevelList)}
                                                            </select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div className="form-group">
                                                    <label htmlFor="question">Question</label>
                                                    <textarea className="form-control" id="question" value={inputData.question} onChange={e => setInputData({ ...inputData, question: e.target.value })} placeholder="Set Question"></textarea>
                                                </div>
                                                <div className="form-group">
                                                    <label htmlFor="question">Choice</label>
                                                    <div className='row'>
                                                        {/* {inputData.choice_requests.map(choicesList)} */}
                                                        <div className='col'>
                                                            <input className='form-control mb-3' placeholder='Set Choice' value={choice1} onChange={e => setChoice1(e.target.value)} />
                                                            <div className="form-check">
                                                                <input className="form-check-input" type="radio" name="radio1" checked={bool1} onChange={e => { setBool1(true); setBool2(false); setBool3(false); setBool4(false); setBool5(false) }} />
                                                                <label className="form-check-label">True</label>
                                                            </div>
                                                        </div>
                                                        <div className='col'>
                                                            <input className='form-control mb-3' placeholder='Set Choice' value={choice2} onChange={e => setChoice2(e.target.value)} />
                                                            <div className="form-check">
                                                                <input className="form-check-input" type="radio" name="radio1" checked={bool2} onChange={e => { setBool1(false); setBool2(true); setBool3(false); setBool4(false); setBool5(false) }} />
                                                                <label className="form-check-label">True</label>
                                                            </div>
                                                        </div>
                                                        <div className='col'>
                                                            <input className='form-control mb-3' placeholder='Set Choice' value={choice3} onChange={e => setChoice3(e.target.value)} />
                                                            <div className="form-check">
                                                                <input className="form-check-input" type="radio" name="radio1" checked={bool3} onChange={e => { setBool1(false); setBool2(false); setBool3(true); setBool4(false); setBool5(false) }} />
                                                                <label className="form-check-label">True</label>
                                                            </div>
                                                        </div>
                                                        <div className='col'>
                                                            <input className='form-control mb-3' placeholder='Set Choice' value={choice4} onChange={e => setChoice4(e.target.value)} />
                                                            <div className="form-check">
                                                                <input className="form-check-input" type="radio" name="radio1" checked={bool4} onChange={e => { setBool1(false); setBool2(false); setBool3(false); setBool4(true); setBool5(false) }} />
                                                                <label className="form-check-label">True</label>
                                                            </div>
                                                        </div>
                                                        <div className='col'>
                                                            <input className='form-control mb-3' placeholder='Set Choice' value={choice5} onChange={e => setChoice5(e.target.value)} />
                                                            <div className="form-check">
                                                                <input className="form-check-input" type="radio" name="radio1" checked={bool5} onChange={e => { setBool1(false); setBool2(false); setBool3(false); setBool4(false); setBool5(true) }} />
                                                                <label className="form-check-label">True</label>
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
