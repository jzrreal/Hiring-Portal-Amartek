import React from "react";
import { NavLink } from "react-router-dom";

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";

function Profile() {
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
                                    <li class="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
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
                                            <img class="profile-user-img img-fluid img-circle"
                                                src="/assets/dist/img/user2-160x160.jpg"
                                                alt="User profile picture" />
                                        </div>
                                        <h3 class="profile-username text-center">Alexander Pierce</h3>
                                        <p class="text-muted text-center">Human Resource</p>
                                    </div>
                                </div>
                                <div class="card card-primary">
                                    <div class="card-header">
                                        <h3 class="card-title">About Me</h3>
                                    </div>
                                    <div class="card-body">
                                        <span className="text-muted"><i class="fas fa-venus mr-1"></i> Gender</span>
                                        <p class="font-weight-bold">
                                            Male
                                        </p>
                                        <hr />
                                        <span className="text-muted"><i class="fas fa-user mr-1"></i> Age</span>
                                        <p class="font-weight-bold">
                                            21 Years
                                        </p>
                                        <hr />
                                        <span className="text-muted"><i class="fas fa-book mr-1"></i> Last Education</span>
                                        <p class="font-weight-bold">
                                            Institute Technology Bandung
                                        </p>
                                        <hr />
                                        <span className="text-muted"><i class="fas fa-map-marker-alt mr-1"></i> Location</span>
                                        <p class="font-weight-bold">Bandung, East Java</p>
                                        <hr />
                                        <span className="text-muted"><i class="fas fa-phone mr-1"></i> Phone Number</span>
                                        <p class="font-weight-bold">+62 895 1439 1356</p>
                                        <hr />
                                        <span className="text-muted"><i class="fas fa-list mr-1"></i> Summary</span>
                                        <p class="font-weight-bold">lorem50</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-9">
                                <div class="card">
                                    <div class="card-header p-2">
                                        <ul class="nav nav-pills">
                                            <li class="nav-item"><a class="nav-link active" href="#education-histories" data-toggle="tab">Education Histories</a></li>
                                            <li class="nav-item"><a class="nav-link" href="#skills" data-toggle="tab">Skills</a></li>
                                            <li class="nav-item"><a class="nav-link" href="#work-experiences" data-toggle="tab">Work Experiences</a></li>
                                            <li class="nav-item"><a class="nav-link" href="#certificates" data-toggle="tab">Certificates</a></li>
                                            <li class="nav-item"><a class="nav-link" href="#achievments" data-toggle="tab">Achievments</a></li>
                                        </ul>
                                    </div>
                                    <div class="card-body">
                                        <div class="tab-content">
                                            <div class="tab-pane" id="education-histories">
                                                <div class="timeline timeline-inverse">
                                                    <div class="time-label">
                                                        <span class="bg-primary px-3 py-2">
                                                            2018 - 2022
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-briefcase bg-primary"></i>
                                                        <div class="timeline-item">
                                                            <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                                                weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                                                jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                                                quora plaxo ideeli hulu weebly balihoo...
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-primary btn-sm">Read more</a>
                                                                <a href="#" class="btn btn-danger btn-sm">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-user bg-info"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                                                            <h3 class="timeline-header border-0"><a href="#">Sarah Young</a> accepted your friend request
                                                            </h3>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-comments bg-warning"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                                            <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                                            <div class="timeline-body">
                                                                Take me to your leader!
                                                                Switzerland is small and neutral!
                                                                We are more like Germany, ambitious and misunderstood!
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-warning btn-flat btn-sm">View comment</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="time-label">
                                                        <span class="bg-success">
                                                            3 Jan. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-camera bg-purple"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 2 days ago</span>

                                                            <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                                            <div class="timeline-body">
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="far fa-clock bg-gray"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="skills">
                                                <div class="timeline timeline-inverse">
                                                    <div class="time-label">
                                                        <span class="bg-danger">
                                                            10 Feb. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-envelope bg-primary"></i>
                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 12:05</span>
                                                            <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                                                weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                                                jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                                                quora plaxo ideeli hulu weebly balihoo...
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-primary btn-sm">Read more</a>
                                                                <a href="#" class="btn btn-danger btn-sm">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-user bg-info"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                                                            <h3 class="timeline-header border-0"><a href="#">Sarah Young</a> accepted your friend request
                                                            </h3>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-comments bg-warning"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                                            <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                                            <div class="timeline-body">
                                                                Take me to your leader!
                                                                Switzerland is small and neutral!
                                                                We are more like Germany, ambitious and misunderstood!
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-warning btn-flat btn-sm">View comment</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="time-label">
                                                        <span class="bg-success">
                                                            3 Jan. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-camera bg-purple"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 2 days ago</span>

                                                            <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                                            <div class="timeline-body">
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="far fa-clock bg-gray"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="work-experiences">
                                                <div class="timeline timeline-inverse">
                                                    <div class="time-label">
                                                        <span class="bg-danger">
                                                            10 Feb. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-envelope bg-primary"></i>
                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 12:05</span>
                                                            <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                                                weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                                                jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                                                quora plaxo ideeli hulu weebly balihoo...
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-primary btn-sm">Read more</a>
                                                                <a href="#" class="btn btn-danger btn-sm">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-user bg-info"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                                                            <h3 class="timeline-header border-0"><a href="#">Sarah Young</a> accepted your friend request
                                                            </h3>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-comments bg-warning"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                                            <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                                            <div class="timeline-body">
                                                                Take me to your leader!
                                                                Switzerland is small and neutral!
                                                                We are more like Germany, ambitious and misunderstood!
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-warning btn-flat btn-sm">View comment</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="time-label">
                                                        <span class="bg-success">
                                                            3 Jan. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-camera bg-purple"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 2 days ago</span>

                                                            <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                                            <div class="timeline-body">
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="far fa-clock bg-gray"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="certificates">
                                                <div class="timeline timeline-inverse">
                                                    <div class="time-label">
                                                        <span class="bg-danger">
                                                            10 Feb. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-envelope bg-primary"></i>
                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 12:05</span>
                                                            <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                                                weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                                                jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                                                quora plaxo ideeli hulu weebly balihoo...
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-primary btn-sm">Read more</a>
                                                                <a href="#" class="btn btn-danger btn-sm">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-user bg-info"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                                                            <h3 class="timeline-header border-0"><a href="#">Sarah Young</a> accepted your friend request
                                                            </h3>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-comments bg-warning"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                                            <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                                            <div class="timeline-body">
                                                                Take me to your leader!
                                                                Switzerland is small and neutral!
                                                                We are more like Germany, ambitious and misunderstood!
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-warning btn-flat btn-sm">View comment</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="time-label">
                                                        <span class="bg-success">
                                                            3 Jan. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-camera bg-purple"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 2 days ago</span>

                                                            <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                                            <div class="timeline-body">
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="far fa-clock bg-gray"></i>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="achievments">
                                                <div class="timeline timeline-inverse">
                                                    <div class="time-label">
                                                        <span class="bg-danger">
                                                            10 Feb. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-envelope bg-primary"></i>
                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 12:05</span>
                                                            <h3 class="timeline-header"><a href="#">Support Team</a> sent you an email</h3>
                                                            <div class="timeline-body">
                                                                Etsy doostang zoodles disqus groupon greplin oooj voxy zoodles,
                                                                weebly ning heekya handango imeem plugg dopplr jibjab, movity
                                                                jajah plickers sifteo edmodo ifttt zimbra. Babblely odeo kaboodle
                                                                quora plaxo ideeli hulu weebly balihoo...
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-primary btn-sm">Read more</a>
                                                                <a href="#" class="btn btn-danger btn-sm">Delete</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-user bg-info"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 5 mins ago</span>

                                                            <h3 class="timeline-header border-0"><a href="#">Sarah Young</a> accepted your friend request
                                                            </h3>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-comments bg-warning"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 27 mins ago</span>

                                                            <h3 class="timeline-header"><a href="#">Jay White</a> commented on your post</h3>

                                                            <div class="timeline-body">
                                                                Take me to your leader!
                                                                Switzerland is small and neutral!
                                                                We are more like Germany, ambitious and misunderstood!
                                                            </div>
                                                            <div class="timeline-footer">
                                                                <a href="#" class="btn btn-warning btn-flat btn-sm">View comment</a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="time-label">
                                                        <span class="bg-success">
                                                            3 Jan. 2014
                                                        </span>
                                                    </div>
                                                    <div>
                                                        <i class="fas fa-camera bg-purple"></i>

                                                        <div class="timeline-item">
                                                            <span class="time"><i class="far fa-clock"></i> 2 days ago</span>

                                                            <h3 class="timeline-header"><a href="#">Mina Lee</a> uploaded new photos</h3>

                                                            <div class="timeline-body">
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                                <img src="https://placehold.it/150x100" alt="..." />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <i class="far fa-clock bg-gray"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
    );
}

export default Profile;
