import { useEffect, useState, React } from 'react'
import {NavLink, useOutletContext} from 'react-router-dom';
import axios from 'axios';

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Index() {
  const [data, setData] = useState([{}]);
  const token = useOutletContext();

  // Get Data
  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/applicants",
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
                  <h1 className="m-0">List of Applicant</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                    <li className="breadcrumb-item active">Applicant</li>
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
                    <table id="example1" className="table table-bordered table-striped table-hover">
                      <thead>
                        <tr>
                          <th>Fullname</th>
                          <th>Gender</th>
                          <th>Email</th>
                          <th>Phone</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {data.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.full_name}</td>
                              <td className="text-capitalize">{data.gender}</td>
                              <td>{data.email}</td>
                              <td className="text-capitalize">{data.phone}</td>
                              <td>
                                <NavLink to={`/human-resource/applicant/detail/${data.id}`} className="btn btn-sm btn-info mr-2"><i className="fas fa-eye"></i></NavLink>
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