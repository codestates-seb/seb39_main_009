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
import Notice from "./pages/Notice/Notice";
import Bookmark from "./pages/Bookmark/Bookmark";
import Mypage from "./pages/mypage/Mypage";
import Editmypage from "./pages/mypage/Editmypage";
import ReservationsList from "./pages/ReservationsList/ReservtionsList";
import Reservation from "./pages/ReservationsList/Reservation";
import JoinPresenter from "./pages/signup/JoinPresenter";
import Partnership from "./pages/Partnership/Partnership";
import Question from "./pages/Question/Question";

function App() {
  // 로그아웃
  const handlelogOut = () => {
    localStorage.removeItem("authorization");
    localStorage.removeItem("refreshtoken");
  };

  return (
    <Router>
      <div className="container">
        <div className="side">프로젝트 소개 구역</div>
        <div className="main_container">
          <div className="header">
            <Header handlelogOut={handlelogOut} />
          </div>
          <div className="main">
            <>
              {/* ↓ 아래 main div 안에 페이지 추가해주시면 됩니다. */}
              <Routes>
                {/* 로딩컴포넌트 */}
                <Route path="/loading" element={<Loading />} />
                {/* 로딩컴포넌트 - 임시 메인 */}
                <Route path="/" element={<HealthCheck />} />
                {/* 로그인창 */}
                <Route path="/login" element={<Login />} />
                {/* 회원가입창 */}
                <Route path="/join" element={<SignUp />} />
                {/* 개인정보수정페이지 */}
                <Route path="/mypage/{id}/edit" element={<Editmypage />} />
                {/* 정보동의페이지 */}
                <Route path="/agmtconf" element={<JoinPresenter />} />
                {/* 서비스 이용약관 */}
                <Route path="/agmtconf/service" element={<ServiceTerm />} />
                {/* 개인정보 이용 동의 약관 */}
                <Route path="/agmtconf/psinfo" element={<PsInfoTerm />} />
                {/* 마케팅 이벤트 정보 동의 약관 */}
                <Route path="/agmtconf/event" element={<EventTerm />} />
                {/* welcome창 */}
                <Route path="/welcome" element={<Welcome />} />
                {/* 마이페이지 */}
                <Route path="/mypage/:id" element={<Mypage />} />
                {/* 주차장 정보 조회 테스트 후 id -> :id 수정 예정*/}
                <Route path="/parking/id" element={<ReservationsList />} />
                {/* 나의 예약 목록*/}
                <Route path="/reservation" element={<ReservationsList />} />
                {/* 예약 상세 조회 */}
                <Route path="/reservation/:id" element={<Reservation />} />
                {/* 공지사항 */}
                <Route path="/notice" element={<Notice />} />
                {/* 즐겨찾기 */}
                <Route path="/bookmark" element={<Bookmark />} />
                {/* 제휴문의 */}
                <Route path="/partnership" element={<Partnership />} />
                {/* 1:1문의 */}
                <Route path="/question" element={<Question />} />
              </Routes>
            </>
          </div>
        </div>
      </div>
    </Router>
  );
}
export default App;
