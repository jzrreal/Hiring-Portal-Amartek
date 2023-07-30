import { React } from 'react'
import { Link } from 'react-router-dom'

function MenuApplicantProfile() {
    return (
        <ul class="nav flex-column">
            <li class="nav-item pt-0 pb-3">
                <Link to="/applicant/profile/personal-information" class="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Personal Information</Link>
            </li>
            <li class="nav-item py-3">
                <Link to="/applicant/profile/education-histories" class="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Education Histories</Link>
            </li>
            <li class="nav-item py-3">
                <Link to="/applicant/profile/skilss" class="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Skills</Link>
            </li>
            <li class="nav-item py-3">
                <Link to="/applicant/profile/work-experiences" class="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Work Experiences</Link>
            </li>
            <li class="nav-item py-3">
                <Link to="/applicant/profile/certificates" class="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Certificates</Link>
            </li>
            <li class="nav-item py-3">
                <Link to="/applicant/profile/achievments" class="nav-link text-muted px-0"><i className="fas fa-user mr-2"></i> Achievments</Link>
            </li>
        </ul>
    )
}

export default MenuApplicantProfile