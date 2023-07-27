import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from "./pages/login";
import Register from "./pages/register";
import NotFound from "./pages/notFound";

// Human Resource
import DashboardHumanResource from "./pages/human-resource/dashboard";
// Role
import IndexRole from './pages/human-resource/role';
import AddRole from './pages/human-resource/role/add';
import EditRole from './pages/human-resource/role/edit';
// Role
// Candidate Profile
import IndexCandidateProfile from './pages/human-resource/candidate-profile';
import DetailCandidateProfile from './pages/human-resource/candidate-profile/detail';
// Candidate Profile
// Applicant Status
import IndexApplicantStatus from './pages/human-resource/applicant-status';
import AddApplicantStatus from './pages/human-resource/applicant-status/add';
import EditApplicantStatus from './pages/human-resource/applicant-status/edit';
// Applicant Status
// Skill Level
import IndexSkillLevel from './pages/human-resource/skill-level';
import AddSkillLevel from './pages/human-resource/skill-level/add';
import EditSkillLevel from './pages/human-resource/skill-level/edit';
// Skill Level
// Question Level
import IndexQuestionLevel from './pages/human-resource/question-level';
import AddQuestionLevel from './pages/human-resource/question-level/add';
import EditQuestionLevel from './pages/human-resource/question-level/edit';
// Question Level
// Job Level
import IndexJobLevel from './pages/human-resource/job-level';
import AddJobLevel from './pages/human-resource/job-level/add';
import EditJobLevel from './pages/human-resource/job-level/edit';
// Job Level
// Job Function
import IndexJobFunction from './pages/human-resource/job-function';
import AddJobFunction from './pages/human-resource/job-function/add';
import EditJobFunction from './pages/human-resource/job-function/edit';
// Job Function
// Test Parameter
import IndexTestParameter from './pages/human-resource/test-parameter';
import AddTestParameter from './pages/human-resource/test-parameter/add';
import EditTestParameter from './pages/human-resource/test-parameter/edit';
// Test Parameter

// Job Post
import IndexJobPost from './pages/human-resource/job-post';
import DetailJobPost from './pages/human-resource/job-post/detail';
import DetailApplicantJobPost from './pages/human-resource/job-post/detailApplicant';
import AddJobPost from './pages/human-resource/job-post/add';
import EditJobPost from './pages/human-resource/job-post/edit';
// Job Post
// Human Resource

// Trainer
import DashboardTrainer from "./pages/trainer/dashboard";
// Question
import IndexQuestion from './pages/trainer/question';
import AddQuestion from './pages/trainer/question/add';
import EditQuestion from './pages/trainer/question/edit';
// Question
// Trainer

// Aplicant
import DashboardApplicant from "./pages/applicant/dashboard";
import ProfileApplicant from "./pages/applicant/profile";
// Aplicant

// Auth
import EmailVerification from './pages/emailVerification';
import HumanResourceProtection from './util/HumanResourceProtection';
import TrainerProtection from './util/TrainerProtection';
import ApplicantProtection from './util/ApplicantProtection';
// Auth

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='*' element={<NotFound />} />
        <Route path='/email-verification' element={<EmailVerification />} />

        {/* Human Resource */}
        <Route path='human-resource' element={<HumanResourceProtection />}>
          <Route path='dashboard' element={<DashboardHumanResource />} />
          {/* Master Data */}
          <Route path='role'>
            <Route index element={<IndexRole />} />
            <Route path='add' element={<AddRole />} />
            <Route path='edit/:id' element={<EditRole />} />
          </Route>
          <Route path='applicant'>
            <Route index element={<IndexCandidateProfile />} />
            <Route path='detail/:id' element={<DetailCandidateProfile />} />
          </Route>
          <Route path='applicant-status'>
            <Route index element={<IndexApplicantStatus />} />
            <Route path='add' element={<AddApplicantStatus />} />
            <Route path='edit/:id' element={<EditApplicantStatus />} />
          </Route>
          <Route path='skill-level'>
            <Route index element={<IndexSkillLevel />} />
            <Route path='add' element={<AddSkillLevel />} />
            <Route path='edit/:id' element={<EditSkillLevel />} />
          </Route>
          <Route path='question-level'>
            <Route index element={<IndexQuestionLevel />} />
            <Route path='add' element={<AddQuestionLevel />} />
            <Route path='edit/:id' element={<EditQuestionLevel />} />
          </Route>
          <Route path='job-level'>
            <Route index element={<IndexJobLevel />} />
            <Route path='add' element={<AddJobLevel />} />
            <Route path='edit/:id' element={<EditJobLevel />} />
          </Route>
          <Route path='job-Function'>
            <Route index element={<IndexJobFunction />} />
            <Route path='add' element={<AddJobFunction />} />
            <Route path='edit/:id' element={<EditJobFunction />} />
          </Route>
          <Route path='test-parameter'>
            <Route index element={<IndexTestParameter />} />
            <Route path='add' element={<AddTestParameter />} />
            <Route path='edit/:id' element={<EditTestParameter />} />
          </Route>
          {/* Master Data */}
          {/* Job */}
          <Route path='job-post'>
            <Route index element={<IndexJobPost />} />
            <Route path='add' element={<AddJobPost />} />
            <Route path='detail/:id' element={<DetailJobPost />} />
            <Route path='detail/applicant/:id' element={<DetailApplicantJobPost />} />
            <Route path='edit/:id' element={<EditJobPost />} />
          </Route>
          {/* Job */}
        </Route>
        {/* Human Resource */}

        {/* Trainer */}
        <Route path='trainer' element={<TrainerProtection />}>
          <Route path='dashboard' element={<DashboardTrainer />} />
          {/* Question */}
          <Route path='question'>
            <Route index element={<IndexQuestion />} />
            <Route path='add' element={<AddQuestion />} />
            <Route path='edit/:id' element={<EditQuestion />} />
          </Route>
          {/* Question */}
        </Route>
        {/* Trainer */}

        {/* Aplicant */}
        <Route path='applicant' element={<ApplicantProtection />}>
          <Route path='dashboard' element={<DashboardApplicant />} />
          <Route path='profile' element={<ProfileApplicant />} />
        </Route>
        {/* Aplicant */}
      </Routes>
    </Router >
  );
}

export default App;
