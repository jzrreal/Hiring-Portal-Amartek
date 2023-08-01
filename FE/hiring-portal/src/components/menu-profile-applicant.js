import { React } from 'react'
import { Link } from 'react-router-dom'

function MenuApplicantProfile() {
    return (
        <ul className="nav flex-column">
            <li className="nav-item pt-0 pb-3">
                <Link to="/applicant/profile/personal-information" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Personal Information</Link>
            </li>
            <li className="nav-item py-3">
                <Link to="/applicant/profile/education-histories" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Education Histories</Link>
            </li>
            <li className="nav-item py-3">
                <Link to="/applicant/profile/skilss" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Skills</Link>
            </li>
            <li className="nav-item py-3">
                <Link to="/applicant/profile/work-experiences" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Work Experiences</Link>
            </li>
            <li className="nav-item py-3">
                <Link to="/applicant/profile/certificates" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Certificates</Link>
            </li>
            <li className="nav-item py-3">
                <Link to="/applicant/profile/achievments" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Achievments</Link>
            </li>
        </ul>
    )
}

export default MenuApplicantProfile