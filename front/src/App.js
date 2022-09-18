import "./layout.css"; // 레이아웃 CSS 입니다. Don't touch !
import "./App.css"; // 비어있으니 레이아웃 외 CSS 추가변경 원하시면 이곳에서 수정해주세요 !
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Header from "./component/Header/Header";
import HealthCheck from "./test/HealthCheck";
import Loading from "./component/Loading/Loading";
import ServiceTerm from "./pages/Terms/ServiceTerm";
import PsInfoTerm from "./pages/Terms/PsInfoTerm";
import EventTerm from "./pages/Terms/EventTerm";
import SignUp from "./pages/signup/Signup";
import Login from "./pages/login/Login";
import Welcome from "./pages/signup/Welcome";

function App() {
  return (
    <Router>
      <div className="container">
        <div className="side">프로젝트 소개 구역</div>
        <div className="main_container">
          <div className="header">
            <Header />
          </div>
          <div className="main">
            {/* ↓ 아래 main div 안에 페이지 추가해주시면 됩니다. */}
            <Routes>
              <Route path="/" element={<HealthCheck />} />
              {/* 회원가입창 */}
              <Route path="/join" element={<SignUp />} />
              {/* welcome창 */}
              <Route path="/welcome" element={<Welcome />} />

              {/* 로그인창 */}
              <Route path="/login" element={<Login />} />
              {/* 로딩창 */}
              <Route path="/loading" element={<Loading />} />
              {/* 서비스 이용약관 */}
              <Route path="/agmtconf/service" element={<ServiceTerm />} />
              {/* 개인정보 이용 동의 약관 */}
              <Route path="/agmtconf/psinfo" element={<PsInfoTerm />} />
              {/* 마케팅 이벤트 정보 동의 약관 */}
              <Route path="/agmtconf/event" element={<EventTerm />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
  );
}
export default App;
