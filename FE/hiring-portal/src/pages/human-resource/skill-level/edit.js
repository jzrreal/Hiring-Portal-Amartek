import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import Swal from 'sweetalert2';

function Edit() {
    const navigate = useNavigate()
    const { id } = useParams();
    const [data, setData] = useState([])

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
            url: "http://localhost:8080/api/skill-levels/" + id,
        })
            .then(function (response) {
                setData(response.data.data);
            })
            .catch(function (error) {
                console.log(error);
            });
    }, [])

    // Edit Data
    function handleSubmit() {
        axios({
            method: "PUT",
            url: "http://localhost:8080/api/skill-levels/" + id,
            data: data
        }).then(
            Toast.fire({
                icon: 'success',
                title: 'Success update data'
            }),
            navigate('/human-resource/skill-level', { replace: true })
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
                                    <h1 className="m-0">Edit Skill Level</h1>
                                </div>
                                <div className="col-sm-6">
                                    <ol className="breadcrumb float-sm-right">
                                        <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                                        <li className="breadcrumb-item"><NavLink to="/human-resource/skill-level">Skill Level</NavLink></li>
                                        <li className="breadcrumb-item active">Edit Skill Level</li>
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
                                            <div className="form-group">
                                                <label for="name">ID Skill Level</label>
                                                <input type="text" className="form-control" id="id" value={data.id} onChange={e => setData({ ...data, id: e.target.value })} />
                                            </div>
                                            <div className="form-group">
                                                <label for="name">Name</label>
                                                <input type="text" className="form-control" id="name" value={data.name} onChange={e => setData({ ...data, name: e.target.value })} />
                                            </div>
                                            <div className="float-right">
                                                <NavLink to="/human-resource/skill-level" type="button" className="btn btn-secondary mr-2">Back</NavLink>
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

export default Edit