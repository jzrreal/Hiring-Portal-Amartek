import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from "./pages/login";
import Register from "./pages/register";
import NotFound from "./pages/notFound";
// Human Resource
import Dashboard from "./pages/human-resource/dashboard";
import IndexRole from './pages/human-resource/role';
import IndexCandidateProfile from './pages/human-resource/candidate-profile';
import IndexSkillLevel from './pages/human-resource/skill-level';
import IndexQuestionLevel from './pages/human-resource/question-level';
import IndexJobLevel from './pages/human-resource/job-level';
import IndexJobFunction from './pages/human-resource/job-function';
import EmailVerification from './pages/emailVerification';
// Human Resource

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
        <Route path='human-resource'>
          <Route path='dashboard' element={<Dashboard />} />
          <Route path='role' element={<IndexRole />} />
          <Route path='aplicant-profile' element={<IndexCandidateProfile />} />
          <Route path='skill-level' element={<IndexSkillLevel />} />
          <Route path='question-level' element={<IndexQuestionLevel />} />
          <Route path='job-level' element={<IndexJobLevel />} />
          <Route path='job-function' element={<IndexJobFunction />} />
        </Route>
        {/* Human Resource */}
      </Routes>
    </Router>
  );
}

export default App;
