import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from "./pages/login";
import Register from "./pages/register";
import NotFound from "./pages/notFound";

// Human Resource
import Dashboard from "./pages/human-resource/dashboard";
import Profile from "./pages/human-resource/profile";
// Role
import IndexRole from './pages/human-resource/role';
import AddRole from './pages/human-resource/role/add';
import EditRole from './pages/human-resource/role/edit';
// Role
// Candidate Profile
import IndexCandidateProfile from './pages/human-resource/candidate-profile';
import DetailCandidateProfile from './pages/human-resource/candidate-profile/detail';
import EditCandidateProfile from './pages/human-resource/candidate-profile/edit';
// Candidate Profile
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
import EmailVerified from './pages/emailVerified';
// Human Resource

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<Login />} />
        <Route path='/login' element={<Login />} />
        <Route path='/register' element={<Register />} />
        <Route path='*' element={<NotFound />} />
        <Route path='/email-verified' element={<EmailVerified />} />

        {/* Human Resource */}
        <Route path='human-resource'>
          <Route path='dashboard' element={<Dashboard />} />
          <Route path='profile' element={<Profile />} />
          <Route path='role'>
            <Route index element={<IndexRole />} />
            <Route path='add' element={<AddRole />} />
            <Route path='edit/:id' element={<EditRole />} />
          </Route>
          <Route path='applicant'>
            <Route index element={<IndexCandidateProfile />} />
            <Route path='detail/:id' element={<DetailCandidateProfile />} />
            <Route path='edit/:id' element={<EditCandidateProfile />} />
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
        </Route>
        {/* Human Resource */}
      </Routes>
    </Router>
  );
}

export default App;
