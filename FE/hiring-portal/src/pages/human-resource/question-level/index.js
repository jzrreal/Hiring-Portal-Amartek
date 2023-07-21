import React from 'react'
import Navbar from "../../../components/navbar";
import Sidebar from "../../../components/sidebar";
import Footer from "../../../components/footer";
import { useNavigate } from 'react-router-dom';

function Index() {
  const navigate = useNavigate();
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
                <h1 className="m-0">List of Question Level</h1>
              </div>
              <div className="col-sm-6">
                <ol className="breadcrumb float-sm-right">
                  <li className="breadcrumb-item"><a href="#" onClick={() => navigate("/dashboard/human-resource")}>Dashboard</a></li>
                  <li className="breadcrumb-item active">Question Level</li>
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
                  <a className="btn btn-primary mb-3" data-toggle="modal" data-target="#addModal"><i className="fas fa-plus mr-2"></i> New Question Level</a>
                  <table id="example1" className="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Name</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>Human Resource</td>
                        <td>
                          <button className="btn btn-sm btn-warning mr-2" data-toggle="modal" data-target="#editModal"><i className="fas fa-pencil-alt"></i></button>
                          <button className="btn btn-sm btn-danger"><i className="fas fa-trash-alt"></i></button>
                        </td>
                      </tr>
                      <tr>
                        <td>Trainner</td>
                        <td>
                          <button className="btn btn-sm btn-warning mr-2" data-toggle="modal" data-target="#editModal"><i className="fas fa-pencil-alt"></i></button>
                          <button className="btn btn-sm btn-danger"><i className="fas fa-trash-alt"></i></button>
                        </td>
                      </tr>
                      <tr>
                        <td>Applicant</td>
                        <td>
                          <button className="btn btn-sm btn-warning mr-2" data-toggle="modal" data-target="#editModal"><i className="fas fa-pencil-alt"></i></button>
                          <button className="btn btn-sm btn-danger"><i className="fas fa-trash-alt"></i></button>
                        </td>
                      </tr>
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

      {/* Modal Add*/}
      <div class="modal fade" id="addModal" tabindex="-1" aria-labelledby="addModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="addModalLabel">Create New Qustion Level</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <form>
                <div class="form-group">
                  <label for="name">Name</label>
                  <input type="text" class="form-control" id="name" placeholder="Qustion Level Name" />
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div >
      {/* Modal Add*/}

      {/* Modal Edit*/}
      <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="editModalLabel">Detail Qustion Level</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <form>
                <div class="form-group">
                  <label for="name">Name</label>
                  <input type="text" class="form-control" id="name" placeholder="Qustion Level Name" />
                </div>
              </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
          </div>
        </div>
      </div >
      {/* Modal Edit*/}
    </>
  )
}

export default Index