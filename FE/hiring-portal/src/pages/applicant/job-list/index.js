import { useEffect, useState, React } from 'react'
import { NavLink, useOutletContext } from 'react-router-dom';
import axios from 'axios';
import dateFormat from 'dateformat'

import DataTable from "react-data-table-component";
import DataTableExtensions from "react-data-table-component-extensions";
import "react-data-table-component-extensions/dist/index.css";

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
      url: process.env.REACT_APP_API_URL + "/api/job-posts",
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

  const columns = [
    {
      name: "Job Title",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.title}</span>
        )
      }
    },
    {
      name: "Job Level",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.job_level}</span>
        )
      }
    },
    {
      name: "Job Function",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.job_function}</span>
        )
      }
    },
    {
      name: "Open Until",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{dateFormat(row.open_until, "dd mmmm yyyy")}</span>
        )
      }
    },
    {
      name: "Actions",
      selector: row => {
        return (
          <NavLink to={`/applicant/job-list/detail/${row.id}`} className="btn btn-sm btn-info mr-2"><i className="fas fa-eye"></i></NavLink>
        )
      }
    }
  ];

  const tableData = {
    columns,
    data
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
                  <h1 className="m-0">List of Job</h1>
                </div>
                <div className="col-sm-6">
                  <ol className="breadcrumb float-sm-right">
                    <li className="breadcrumb-item"><NavLink to="/applicant/dashboard">Dashboard</NavLink></li>
                    <li className="breadcrumb-item active">Job List</li>
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
                      <DataTableExtensions {...tableData} filterDigit={0} export={false} print={false} filterPlaceholder="Seacrh by Name">
                        <DataTable
                          columns={columns}
                          data={data}
                          striped={true}
                          pagination
                          highlightOnHover
                          customStyles={customStyles}
                        />
                      </DataTableExtensions>
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

export default Index