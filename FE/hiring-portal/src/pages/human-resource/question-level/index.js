import { useEffect, useState, React } from 'react'
import { NavLink, useNavigate, useOutletContext } from 'react-router-dom';
import axios from 'axios';
import Swal from 'sweetalert2'

import DataTable from "react-data-table-component";
import DataTableExtensions from "react-data-table-component-extensions";
import "react-data-table-component-extensions/dist/index.css";

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

  useEffect(() => {
    getData()
  }, [])

  // Get Data
  function getData() {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/question-levels",
      headers: {
        Authorization: "Bearer " + token
      }
    }).then(function (response) {
      setData(response.data.data);
    }).catch(function (error) {
      console.log(error);
    });
  }

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
          url: process.env.REACT_APP_API_URL + "/api/question-levels/" + id,
          headers: {
            Authorization: "Bearer " + token
          }
        }).then(
          Toast.fire({
            icon: 'success',
            title: 'Success delete data'
          }),
          getData(),
          navigate('/human-resource/question-level', { replace: false })
        ).catch(function (error) { console.log(error); })
      }
    })
  }

  const columns = [
    {
      name: "Name",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.name}</span>
        )
      }
    },
    {
      name: "Point",
      sortable: true,
      selector: row => {
        return (
          <span className="text-capitalize text-md">{row.point + " Point"}</span>
        )
      }
    },
    {
      name: "Actions",
      selector: row => {
        return (
          <>
            <NavLink to={`/human-resource/question-level/edit/${row.questionLevelId}`} className="btn btn-sm btn-warning mr-2"><i className="fas fa-pencil-alt"></i></NavLink>
            <button onClick={() => deleteData(row.questionLevelId)} className="btn btn-sm btn-danger"><i className="fas fa-trash-alt"></i></button>
          </>
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
                <NavLink to="/human-resource/question-level/add" className="btn btn-primary"><i className="fas fa-plus mr-2"></i> New Question Level</NavLink>
              </div>
              <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item"><NavLink to="/human-resource/dashboard">Dashboard</NavLink></li>
                  <li className="breadcrumb-item active">Question Level</li>
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
                    <DataTableExtensions {...tableData} filterDigit={0} export={false} print={false} filterPlaceholder="Seacrh by Name">
                      <DataTable
                        columns={columns}
                        data={data}
                        fixedHeader={true}
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
  )
}

export default Index