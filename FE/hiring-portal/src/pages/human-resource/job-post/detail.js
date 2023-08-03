import { useEffect, useState, React } from 'react'
import { NavLink, useOutletContext, useParams } from 'react-router-dom';
import axios from 'axios';
import dateFormat from 'dateformat'

import DataTable from "react-data-table-component";
import DataTableExtensions from "react-data-table-component-extensions";
import "react-data-table-component-extensions/dist/index.css";

import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";

function Index() {
  const { id } = useParams();
  const [dataJobPost, setDataJobPost] = useState([{}]);
  const [dataApplicant, setDatadataApplicant] = useState([{}]);
  const token = useOutletContext();

  // Get Data
  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/job-posts/" + id,
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(function (response) {
        setDataJobPost(response.data.data);
        axios({
          method: "GET",
          url: process.env.REACT_APP_API_URL + "/api/applications/job-post/" + response.data.data.id,
          headers: {
            Authorization: "Bearer " + token
          }
        })
          .then(function (response) {
            setDatadataApplicant(response.data.data);
          })
          .catch(function (error) {
            console.log(error);
          });
      })
      .catch(function (error) {
        console.log(error);
      });
  }, [])

  const columns = [
    {
      name: "Applicant Name",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.name}</span>
        )
      }
    },
    {
      name: "Gender",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.gender}</span>
        )
      }
    },
    {
      name: "Age",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.age}</span>
        )
      }
    },
    {
      name: "School / University",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.school_name}</span>
        )
      }
    },
    {
      name: "Apply At",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{dateFormat(row.apply_date, "dd mmmm yyyy")}</span>
        )
      }
    },
    {
      name: "Status",
      sortable: true,
      selector: row => {
        return (
          row.status == "submitted" ?
            <span className="badge badge-secondary p-2" style={{ fontSize: 12 }}>{row.status}</span>
            : (row.status == "reviewed" ?
              <span className="badge badge-info p-2" style={{ fontSize: 12 }}>{row.status}</span>
              : (row.status == "test" ?
                <span className="badge badge-warning p-2" style={{ fontSize: 12 }}>{row.status}</span>
                : (row.status == "rejected" ?
                  <span className="badge badge-danger p-2" style={{ fontSize: 12 }}>{row.status}</span>
                  : (row.status == "passed" ?
                    <span className="badge badge-success p-2" style={{ fontSize: 12 }}>{row.status}</span>
                    : null
                  )
                )
              )
            )
        )
      }
    },
    {
      name: "Result Test",
      sortable: true,
      selector: row => {
        return (
          row.test_result == "Waiting" ?
            <span className="badge badge-warning p-2" style={{ fontSize: 12 }}>{row.test_result}</span>
            : (row.test_result == "Passed" ?
              <span className="badge badge-success p-2" style={{ fontSize: 12 }}>{row.test_result}</span>
              : (row.test_result == "Failed" ?
                <span className="badge badge-danger p-2" style={{ fontSize: 12 }}>{row.test_result}</span>
                : null
              )
            )
        )
      }
    },
    {
      name: "Actions",
      selector: row => {
        return (
          row.test_result == "Waiting" ?
            <span className="badge badge-warning p-2" style={{ fontSize: 12 }}>{row.test_result}</span>
            : (row.test_result == "Passed" ?
              <span className="badge badge-success p-2" style={{ fontSize: 12 }}>{row.test_result}</span>
              : (row.test_result == "Failed" ?
                <span className="badge badge-danger p-2" style={{ fontSize: 12 }}>{row.test_result}</span>
                : null
              )
            )
        )
      }
    }
  ];

  const tableData = {
    columns,
    dataApplicant
  };

  const customStyles = {
    rows: {
      style: {
        minHeight: '72px', // override the row height
      },
    },
    headCells: {
      style: {
        paddingLeft: '8px', // override the cell padding for head cells
        paddingRight: '8px',
        fontSize: '16px',
        fontWeight: 'bold'
      },
    },
    cells: {
      style: {
        paddingLeft: '8px', // override the cell padding for data cells
        paddingRight: '8px',
      },
    },
  };

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
                <h1 className="m-0">Detail of Job Post</h1>
              </div>
              <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                  <li className="breadcrumb-item"><NavLink to="/human-resource/job-post">Job Post</NavLink></li>
                  <li className="breadcrumb-item active">Detail Job Post</li>
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
                <div className="card mb-4">
                  <div className="card-body">
                    <div className='row'>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Post At</label>
                          <input type="text" className="form-control" id="id" value={dateFormat(dataJobPost.post_at, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Updated At</label>
                          <input type="text" className="form-control text-capitalize" id="id" value={dateFormat(dataJobPost.updated_at, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Vacancy</label>
                          <input type="text" className="form-control" id="id" value={dataJobPost.vacancy + " People"} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className='row'>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Job Title</label>
                          <input type="text" className="form-control" id="id" value={dataJobPost.title} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Job Function</label>
                          <input type="text" className="form-control" id="id" value={dataJobPost.job_function} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Job Level</label>
                          <input type="text" className="form-control text-capitalize" id="id" value={dataJobPost.job_level} readOnly />
                        </div>
                      </div>
                    </div>
                    <div className='row'>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Open Until</label>
                          <input type="text" className="form-control text-capitalize" id="id" value={dateFormat(dataJobPost.open_until, "dd mmmm yyyy")} readOnly />
                        </div>
                      </div>
                      <div className='col'>
                        <div className="form-group">
                          <label for="id">Closed</label>
                          {
                            (dataJobPost.closed === null || dataJobPost.closed === false) ?
                              <input type="text" className="form-control bg-success" id="id" value="Open" />
                              : <input type="text" className="form-control bg-danger" id="id" value="Closed" />
                          }
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="card">
                  <div className="card-body">
                    <h4 className="mb-3">List of Applicant</h4>
                    {/* <DataTableExtensions {...tableData} filterDigit={0} export={false} print={false} filterPlaceholder="Seacrh by Name">
                      <DataTable
                        columns={columns}
                        data={dataApplicant}
                        fixedHeader={true}
                        striped={true}
                        pagination
                        highlightOnHover
                        customStyles={customStyles}
                      />
                    </DataTableExtensions> */}
                    <table id="example1" className="table table-bordered table-striped table-hover">
                      <thead>
                        <tr>
                          <th>Applicant Name</th>
                          <th>Gender</th>
                          <th>Age</th>
                          <th>School / University</th>
                          <th>Apply At</th>
                          <th>Status</th>
                          <th>Test Result</th>
                          {
                            dataJobPost.closed === true ? null : <th>Actions</th>
                          }
                        </tr>
                      </thead>
                      <tbody>
                        {dataApplicant.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.name}</td>
                              <td className="text-capitalize">{data.gender}</td>
                              <td className="text-capitalize">{data.age} Years</td>
                              <td className="text-capitalize">{data.school_name} - {data.major}</td>
                              <td className="text-capitalize">{dateFormat(data.apply_date, "dd mmmm yyyy")}</td>
                              <td className="text-capitalize">
                                {
                                  data.status == "submitted" ?
                                    <span className="badge badge-secondary">{data.status}</span>
                                    : (data.status == "reviewed" ?
                                      <span className="badge badge-info">{data.status}</span>
                                      : (data.status == "test" ?
                                        <span className="badge badge-warning">{data.status}</span>
                                        : (data.status == "rejected" ?
                                          <span className="badge badge-danger">{data.status}</span>
                                          : (data.status == "passed" ?
                                            <span className="badge badge-success">{data.status}</span>
                                            : null
                                          )
                                        )
                                      )
                                    )
                                }
                              </td>
                              <td className="text-capitalize">
                                {
                                  data.test_result == "Waiting" ?
                                    <span className="badge badge-warning">{data.test_result}</span>
                                    : (data.test_result == "Passed" ?
                                      <span className="badge badge-success">{data.test_result}</span>
                                      : (data.test_result == "Failed" ?
                                        <span className="badge badge-danger">{data.test_result}</span>
                                        : null
                                      )
                                    )
                                }
                              </td>
                              {
                                dataJobPost.closed === true ?
                                  null
                                  :
                                  <td>
                                    <NavLink to={`/human-resource/job-post/detail/applicant/${data.job_application_id}`} className="btn btn-sm btn-info mr-2"><i className="fas fa-eye"></i></NavLink>
                                  </td>
                              }
                            </tr>
                          );
                        })}
                      </tbody>
                    </table>
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

export default Index