import { React } from 'react'
import { NavLink } from 'react-router-dom'

function MenuApplicantProfile() {
    return (
        <ul className="nav flex-column">
            <li className="nav-item pt-0 pb-3">
                <NavLink to="/applicant/profile/personal-information" className="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Personal Information</NavLink>
            </li>
            <li className="nav-item py-3">
                <NavLink to="/applicant/profile/education-histories" className="nav-link text-muted px-0"><i className="fas fa-graduation-cap mr-2"></i> Education Histories</NavLink>
            </li>
            <li className="nav-item py-3">
                <NavLink to="/applicant/profile/skills" className="nav-link text-muted px-0"><i className="fas fa-rocket mr-2"></i> Skills</NavLink>
            </li>
            <li className="nav-item py-3">
                <NavLink to="/applicant/profile/work-experiences" className="nav-link text-muted px-0"><i className="fas fa-briefcase mr-2"></i> Work Experiences</NavLink>
            </li>
            <li className="nav-item py-3">
                <NavLink to="/applicant/profile/certificates" className="nav-link text-muted px-0"><i className="fas fa-certificate mr-2"></i> Certificates</NavLink>
            </li>
            <li className="nav-item py-3">
                <NavLink to="/applicant/profile/achievments" className="nav-link text-muted px-0"><i className="fas fa-award mr-2"></i> Achievments</NavLink>
            </li>
        </ul>
    )
}

export default MenuApplicantProfile