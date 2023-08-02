import { useEffect, useState, React } from 'react'
import { NavLink, useParams, useOutletContext } from 'react-router-dom';
import axios from 'axios';
import dateFormat from 'dateformat';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Edit() {
    const { id } = useParams();
    const [data, setData] = useState([])
    const token = useOutletContext();

    // Get Data
    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/applicants/" + id,
            headers: {
                Authorization: "Bearer " + token
            }
        })
            .then(function (response) {
                setData(response.data.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }, [])

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
                                <h1 className="m-0">Detail Applicant</h1>
                            </div>
                            <div className="col-sm-6">
                                <ol className="breadcrumb float-sm-right">
                                    <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                                    <li className="breadcrumb-item"><NavLink to="/human-resource/applicant">Applicant</NavLink></li>
                                    <li className="breadcrumb-item active">Detail Applicant</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                {/* Content Header */}

                {/* Main Content */}
                <section className="content">
                    <div className="container">
                        <div className="row">
                            <div className="col-12">
                                <div className="card">
                                    <div className="card-body">
                                        <form>
                                            <div className="form-group">
                                                <label for="name">ID Applicant</label>
                                                <input type="text" className="form-control" id="id" value={data.id} readOnly />
                                            </div>
                                            <div className="form-group">
                                                <label for="fullname">Fullname</label>
                                                <input type="text" className="form-control" id="fullname" value={data.full_name} readOnly />
                                            </div>
                                            <div className="form-group">
                                                <label for="gender">Gender</label>
                                                <input type="text" className="form-control" id="gender" value={data.gender} readOnly />
                                            </div>
                                            <div className="form-group">
                                                <label for="email">Email</label>
                                                <input type="email" className="form-control" id="email" value={data.email} readOnly />
                                            </div>
                                            <div className="form-group">
                                                <label for="phone">Phone Number</label>
                                                <input type="text" className="form-control" id="phone" value={data.phone} readOnly />
                                            </div>
                                            <div className="form-group">
                                                <label for="birthdate">Birth Date</label>
                                                <input type="text" className="form-control" id="birthdate" value={dateFormat(data.birth_date, "dd mmmm yyyy")} readOnly />
                                            </div>
                                            <div className="form-group">
                                                <label for="summary">Summary</label>
                                                <textarea className="form-control" id="summary" value={data.summary} readOnly />
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/applicant" type="button" className="btn btn-secondary mr-2">Back</NavLink>
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

export default Edit