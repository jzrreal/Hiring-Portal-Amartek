import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2'

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Index() {
  const navigate = useNavigate();
  const [data, setData] = useState([{}]);
  const token = useOutletContext();

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
      url: process.env.REACT_APP_API_URL + "/api/job-levels",
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

  // Delete Data
  function deleteData(id) {
    Swal.fire({
      title: 'Are you sure?',
      text: "You won't be able to revert this!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      confirmButtonText: 'Yes, Delete Now!'
    }).then((result) => {
      if (result.isConfirmed) {
        axios({
          method: "DELETE",
          url: process.env.REACT_APP_API_URL + "/api/job-levels/" + id,
          headers: {
            Authorization: "Bearer " + token
          }
        }).then(
          Toast.fire({
            icon: 'success',
            title: 'Success delete data'
          }),
          setData(data),
          navigate('/human-resource/job-level', { replace: false })
        ).catch(function (error) { console.log(error); })
      }
    })
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
                  <h1 className="m-0">List of Job Level</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                    <li className="breadcrumb-item active">Job Level</li>
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
                    <NavLink to="/human-resource/job-level/add" className="btn btn-primary mb-3"><i className="fas fa-plus mr-2"></i> New Job Level</NavLink>
                    <table id="example1" className="table table-bordered table-striped table-hover">
                      <thead>
                        <tr>
                          <th>Name</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {data.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.name}</td>
                              <td>
                                <NavLink to={`/human-resource/job-level/edit/${data.id}`} className="btn btn-sm btn-warning mr-2"><i className="fas fa-pencil-alt"></i></NavLink>
                                <button onClick={() => deleteData(data.id)} className="btn btn-sm btn-danger"><i className="fas fa-trash-alt"></i></button>
                              </td>
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
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

export default Index