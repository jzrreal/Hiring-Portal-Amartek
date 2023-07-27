import { React, useEffect, useState } from "react";
import { NavLink } from "react-router-dom";
import axios from "axios";
import dateFormat from 'dateformat';

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";

function Profile() {
    const [dataProfile, setDataProfile] = useState({});
    const [dataEducation, setDataEducation] = useState([{}]);

    // Get Profile
    useEffect(() => {
        axios({
            method: "GET",
            url: process.env.REACT_APP_API_URL + "/api/profiles/applicants",
            headers: {
                Authorization: "Bearer " + localStorage.getItem("authToken")
            }
        })
            .then(response => {
                setDataProfile(response.data.data)
                axios({
                    method: "GET",
                    url: process.env.REACT_APP_API_URL + "/api/education-histories/applicants/" + response.data.data.id,
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("authToken")
                    }
                })
                    .then(response => {
                        setDataEducation(response.data.data)
                    })
            })
            console.log(dataProfile);
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
                <section className="content-header">
                    <div className="container-fluid">
                        <div class="row mb-2">
                            <div class="col-sm-6">
                                <h1>Profile</h1>
                            </div>
                            <div class="col-sm-6">
                                <ol class="breadcrumb float-sm-right">
                                    <li class="breadcrumb-item">
                                        <NavLink to="/human-resource/dashboard">Dashboard</NavLink>
                                    </li>
                                    <li class="breadcrumb-item active">Profile</li>
                                </ol>
                            </div>
                        </div>
                    </div>
                    {/* Content Header */}
                </section>

                {/* Main Content */}
                <section class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-3">
                                <div class="card card-primary card-outline">
                                    <div class="card-body box-profile">
                                        <div class="text-center">
                                            <img
                                                class="profile-user-img img-fluid img-circle"
                                                src="/assets/dist/img/user2-160x160.jpg"
                                                alt="User profile picture"
                                            />
                                        </div>
                                        <h3 class="profile-username text-center">
                                            {dataProfile.full_name}
                                        </h3>
                                        <p class="text-muted text-center">{dataProfile.email}</p>
                                    </div>
                                </div>
                                <div class="card card-primary">
                                    <div class="card-header">
                                        <h3 class="card-title">About Me</h3>
                                    </div>
                                    <div class="card-body">
                                        <span className="text-muted">
                                            <i class="fas fa-venus mr-1"></i> Gender
                                        </span>
                                        <p class="font-weight-bold"> {dataProfile.gender} </p>
                                        <hr />
                                        <span className="text-muted">
                                            <i class="fas fa-venus mr-1"></i> Birth Date
                                        </span>
                                        <p class="font-weight-bold"> {dateFormat(dataProfile.birth_date, "dd mmmm yyyy")} </p>
                                        <hr />
                                        <span className="text-muted">
                                            <i class="fas fa-user mr-1"></i> Age
                                        </span>
                                        <p class="font-weight-bold">{dataProfile.age} Years</p>
                                        <hr />
                                        <span className="text-muted">
                                            <i class="fas fa-book mr-1"></i> Last Education
                                        </span>
                                        <p class="font-weight-bold m-0">
                                            {dataProfile.last_education}
                                        </p>
                                        <hr />
                                        <span className="text-muted">
                                            <i class="fas fa-phone mr-1"></i> Phone Number
                                        </span>
                                        <p class="font-weight-bold">{dataProfile.phone}</p>
                                        <hr />
                                        <span className="text-muted">
                                            <i class="fas fa-list mr-1"></i> Summary
                                        </span>
                                        <p class="font-weight-bold">
                                            {dataProfile.summary}
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="card">
                                    <div class="card-header p-2">
                                        <ul class="nav nav-pills">
                                            <li class="nav-item">
                                                <a
                                                    class="nav-link active"
                                                    href="#education-histories"
                                                    data-toggle="tab"
                                                >
                                                    Education Histories
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a class="nav-link" href="#skills" data-toggle="tab">
                                                    Skills
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a
                                                    class="nav-link"
                                                    href="#work-experiences"
                                                    data-toggle="tab"
                                                >
                                                    Work Experiences
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a
                                                    class="nav-link"
                                                    href="#certificates"
                                                    data-toggle="tab"
                                                >
                                                    Certificates
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a
                                                    class="nav-link"
                                                    href="#achievments"
                                                    data-toggle="tab"
                                                >
                                                    Achievments
                                                </a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content">
                                            <div class="tab-pane active" id="education-histories">
                                                <ul class="products-list product-list-in-card px-2">
                                                    {dataEducation.map((data) => {
                                                        return (
                                                            <li class="item py-3">
                                                                <div class="product-img">
                                                                    <img
                                                                        src="/assets/dist/img/default-150x150.png"
                                                                        alt="Product Image"
                                                                        class="img-size-50"
                                                                    />
                                                                </div>
                                                                <div class="product-info">
                                                                    <h6 class="product-title m-0">
                                                                        {data.name}
                                                                        <span class="badge badge-primary px-2 py-2 float-right">
                                                                            {data.yearStart} - {data.yearEnd}
                                                                        </span>
                                                                    </h6>
                                                                    <span class="text-muted">{data.level}</span>
                                                                    <span class="text-muted mx-2">-</span>
                                                                    <span class="text-muted">{data.major}</span>
                                                                </div>
                                                            </li>
                                                        );
                                                    })}
                                                </ul>
                                            </div>
                                            <div class="tab-pane" id="skills">
                                                <div id="basic-skill">
                                                    <h6 className="font-weight-bold">Basic</h6>
                                                    <div className="my-3">
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                                                    </div>
                                                    <hr />
                                                </div>
                                                <div id="novice-skill">
                                                    <h6 className="font-weight-bold">Novice</h6>
                                                    <div className="my-3">
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                                                    </div>
                                                    <hr />
                                                </div>
                                                <div id="indermediate-skill">
                                                    <h6 className="font-weight-bold">Indermediate</h6>
                                                    <div className="my-3">
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                                                    </div>
                                                    <hr />
                                                </div>
                                                <div id="advance-skill">
                                                    <h6 className="font-weight-bold">Advance</h6>
                                                    <div className="my-3">
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                                                    </div>
                                                    <hr />
                                                </div>
                                                <div id="expert-skill">
                                                    <h6 className="font-weight-bold">Expert</h6>
                                                    <div className="my-3">
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Java</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Springboot</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">Next JS</span>
                                                        <span className="btn btn-sm btn-outline-primary rounded-pill px-3 py-1 mr-3">React JS</span>
                                                    </div>
                                                    <hr />
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="work-experiences">
                                                <div class="timeline timeline-inverse">
                                                    <div class="time-label pt-4">
                                                        <span class="text-primary border border-primary px-3">2022 - 2023</span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-briefcase bg-info"></i>
                                                        <div class="timeline-item">
                                                            <span class="time text-primary font-weight-bold">
                                                                <i class="far fa-user mr-2"></i> Front End Developer
                                                            </span>
                                                            <h3 class="timeline-header text-primary font-weight-bold">Bumi Amartha Teknologi Mandiri</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin
                                                                oooj voxy zoodles, weebly ning heekya handango
                                                                imeem plugg dopplr jibjab, movity jajah plickers
                                                                sifteo edmodo ifttt zimbra. Babblely odeo
                                                                kaboodle quora plaxo ideeli hulu weebly
                                                                balihoo...
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="time-label pt-4">
                                                        <span class="text-primary border border-primary px-3">2021 - 2022</span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-briefcase bg-info"></i>
                                                        <div class="timeline-item">
                                                            <span class="time text-primary font-weight-bold">
                                                                <i class="far fa-user mr-2"></i> Front End Developer
                                                            </span>
                                                            <h3 class="timeline-header text-primary font-weight-bold">Mandiri Internasional Teknologi</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin
                                                                oooj voxy zoodles, weebly ning heekya handango
                                                                imeem plugg dopplr jibjab, movity jajah plickers
                                                                sifteo edmodo ifttt zimbra. Babblely odeo
                                                                kaboodle quora plaxo ideeli hulu weebly
                                                                balihoo...
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="certificates">
                                                <ul class="products-list product-list-in-card px-2">
                                                    <li class="item py-3">
                                                        <div class="product-img">
                                                            <img
                                                                src="/assets/dist/img/default-150x150.png"
                                                                alt="Product Image"
                                                                class="img-size-50"
                                                            />
                                                        </div>
                                                        <div class="product-info">
                                                            <h6 class="product-title m-0">
                                                                Certificate Front End Developer with Node JS & React JS
                                                                <span class="badge badge-primary px-2 py-2 float-right">
                                                                    2023
                                                                </span>
                                                            </h6>
                                                            <span class="text-muted">Build With Angga</span>
                                                        </div>
                                                    </li>
                                                    <li class="item py-3">
                                                        <div class="product-img">
                                                            <img
                                                                src="/assets/dist/img/default-150x150.png"
                                                                alt="Product Image"
                                                                class="img-size-50"
                                                            />
                                                        </div>
                                                        <div class="product-info">
                                                            <h6 class="product-title m-0">
                                                                Certificate of Junior Web Programming
                                                                <span class="badge badge-primary px-2 py-2 float-right">
                                                                    2022
                                                                </span>
                                                            </h6>
                                                            <span class="text-muted">Dicoding</span>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="tab-pane" id="achievments">
                                                <ul class="products-list product-list-in-card px-2">
                                                    <li class="item py-3">
                                                        <div class="product-img">
                                                            <img
                                                                src="/assets/dist/img/default-150x150.png"
                                                                alt="Product Image"
                                                                class="img-size-50"
                                                            />
                                                        </div>
                                                        <div class="product-info">
                                                            <h6 class="product-title m-0">
                                                                Best Web Designner 2023
                                                                <span class="badge badge-primary px-2 py-2 float-right">
                                                                    2023
                                                                </span>
                                                            </h6>
                                                            <span class="text-muted">Politeknik Negeri Indramayu</span>
                                                        </div>
                                                    </li>
                                                    <li class="item py-3">
                                                        <div class="product-img">
                                                            <img
                                                                src="/assets/dist/img/default-150x150.png"
                                                                alt="Product Image"
                                                                class="img-size-50"
                                                            />
                                                        </div>
                                                        <div class="product-info">
                                                            <h6 class="product-title m-0">
                                                                Winner 1st Hackathon of Business Solution
                                                                <span class="badge badge-primary px-2 py-2 float-right">
                                                                    2022
                                                                </span>
                                                            </h6>
                                                            <span class="text-muted">Politeknik Negeri Riau</span>
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                {/* Main Contet */}
            </div >
            {/* Content */}

            {/* Footer */}
            <Footer />
            {/* Footer */}
        </div >
    );
}

export default Profile;
