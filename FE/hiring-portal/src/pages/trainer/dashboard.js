import React, { useEffect, useState } from "react";
import { useOutletContext, NavLink } from "react-router-dom";
import axios from "axios";
import dateFormat from "dateformat";

import Navbar from "../../components/navbar";
import Sidebar from "../../components/sidebar";
import Footer from "../../components/footer";

function Dashboard() {
  const [total_easy_questions, set_total_easy_questions] = useState(0);
  const [total_medium_questions, set_total_medium_questions] = useState(0);
  const [total_hard_questions, set_total_hard_questions] = useState(0);
  const [data_question_responses, set_question_responses] = useState([{}]);
  const token = useOutletContext()

  useEffect(() => {
    axios({
      method: "GET",
      url: process.env.REACT_APP_API_URL + "/api/dashboards",
      headers: {
        Authorization: "Bearer " + token
      }
    })
      .then(response => {
        set_total_easy_questions(response.data.data.total_easy_questions);
        set_total_medium_questions(response.data.data.total_medium_questions);
        set_total_hard_questions(response.data.data.total_hard_questions);
        set_question_responses(response.data.data.question_responses);
      })
      .catch(err => {
        set_total_easy_questions(0);
        set_total_medium_questions(0);
        set_total_hard_questions(0);
      })
  }, []);

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
            <div className="row mb-2">
              <div className="col-sm-6">
                <h1>Dashboard</h1>
              </div>
            </div>
          </div>
          {/* Content Header */}
        </section>

        {/* Main Content */}
        <section className="content">
          <div className="row">
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box">
                <span className="info-box-icon bg-info elevation-1"><i className="fas fa-list-ol"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Total Questions</span>
                  <span className="info-box-number">
                    0
                  </span>
                </div>
              </div>
            </div>
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-success elevation-1"><i className="fas fa-list-ol"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Questions Easy</span>
                  <span className="info-box-number">{total_easy_questions}</span>
                </div>
              </div>
            </div>
            <div className="clearfix hidden-md-up"></div>
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-warning elevation-1"><i className="fas fa-list-ol"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Questions Medium</span>
                  <span className="info-box-number">{total_medium_questions}</span>
                </div>
              </div>
            </div>
            <div className="col-12 col-sm-6 col-md-3">
              <div className="info-box mb-3">
                <span className="info-box-icon bg-danger elevation-1"><i className="fas fa-list-ol"></i></span>
                <div className="info-box-content">
                  <span className="info-box-text">Questions Hard</span>
                  <span className="info-box-number">{total_hard_questions}</span>
                </div>
              </div>
            </div>
          </div>
          <div className="row mt-2">
            <div className="col">
              <div className="card">
                <div className="card-body">
                  <div className="d-flex justify-content-between">
                    <h5><i className="fas fa-list-ol mr-2"></i> New Questions</h5>
                    <NavLink to="/trainer/question" className="btn btn-sm btn-primary">See More Question</NavLink>
                  </div>
                  <div className="table-responsive mt-3">
                    <table className="table table-bordered tabled-striped table-hover">
                      <thead>
                        <tr>
                          <th>Question</th>
                          <th>Segment</th>
                          <th>Question Level</th>
                          <th>Created At</th>
                        </tr>
                      </thead>
                      <tbody>
                        {data_question_responses.map((data) => {
                          return (
                            <tr>
                              <td className="text-capitalize">{data.question}</td>
                              <td className="text-capitalize">{data.segment}</td>
                              <td className="text-capitalize">{data.question_level}</td>
                              <td className="text-capitalize">{dateFormat(data.created_at, "dd mmmm yyyy")}</td>
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
  );
}

export default Dashboard;
