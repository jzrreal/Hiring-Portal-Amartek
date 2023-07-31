import React from 'react'
import { Link, useNavigate } from 'react-router-dom'

function NotFound() {
  // Title Page
  document.title = "404 Page Not Found | Hiring Portal";
  // Title Page
  const navigate = useNavigate();
  return (
    <div class="lockscreen-wrapper">
      <div class="lockscreen-logo">
        <img src="https://www.amartek.id/i/logo/weblogo-amartek.png" />
      </div>
      <div class="help-block text-center my-5">
        <h1 className="font-weight-bold">404 Page Not Found</h1>
        <div>
          We could not find the page you were looking for. Meanwhile, you may return to previous page.
        </div>
        <Link className="btn btn-primary mt-3" onClick={() => navigate(-1)}>Back Previous Page</Link>
      </div>
      <div class="lockscreen-footer text-center">
        <span>Copyright &copy; 2023 <a href="https://www.amartek.id/" className="font-weight-bold">Bumi Amartha Teknologi Mandiri</a></span>
        <div>All rights reserved.</div>
      </div>
    </div>
  )
}

export default NotFound