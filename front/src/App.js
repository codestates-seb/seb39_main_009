import "./layout.css"; // 레이아웃 CSS 입니다. Don't touch !
import "./App.css"; // 비어있으니 레이아웃 외 CSS 추가변경 원하시면 이곳에서 수정해주세요 !
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useEffect } from "react";
import useRefreshToken from "./hooks/useRefreshToken";
import Header from "./component/Header/Header";
import HealthCheck from "./test/HealthCheck";
import SignUp from "./pages/signup/Signup";
import Login from "./pages/login/Login";
import Welcome from "./pages/signup/Welcome";
import Notice from "./pages/Notice/Notice";
import Bookmark from "./pages/Bookmark/Bookmark";
import Mypage from "./pages/mypage/Mypage";
import Editmypage from "./pages/mypage/Editmypage";
import ReservationsList from "./pages/ReservationsList/ReservtionsList";
import Reservation from "./pages/ReservationsList/Reservation";
import Partnership from "./pages/Partnership/Partnership";
import Question from "./pages/Question/Question";
import Parkinglot from "./pages/Parkinglot/Parkinglot";
import ParkingMap from "./pages/Parkinglot/ParkingMap";
import Review from "./pages/Review/Review";
import Editreview from "./pages/Review/Editreview";
import AfterLogin from "./pages/login/AfterLogin";

function App() {
  const onSilentRefresh = useRefreshToken();

  // 새로고침시에도 OnSilentRefresh함수가 작동할 수 있게 만드는 것
  useEffect(() => {
    setInterval(onSilentRefresh, 360000);
  });

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
            <div>
              {/* ↓ 아래 main div 안에 페이지 추가해주시면 됩니다. */}
              <Routes>
                {/* 로딩컴포넌트 - 임시 메인 */}
                <Route path="/" element={<HealthCheck />} />
                {/* 로그인창 */}
                <Route
                  path="/login"
                  element={
                    !localStorage.authorization ? <Login /> : <AfterLogin />
                  }
                />
                {/* 회원가입창 */}
                <Route
                  path="/join"
                  element={
                    !localStorage.authorization ? <SignUp /> : <AfterLogin />
                  }
                />
                {/* 개인정보수정페이지 */}
                <Route path="/mypage/:id/edit" element={<Editmypage />} />
                {/* welcome창 */}
                <Route path="/welcome" element={<Welcome />} />
                {/* 마이페이지 */}
                <Route path="/mypage/:id" element={<Mypage />} />
                {/* 주차장 정보 조회*/}
                <Route path="/parking/:pkId" element={<Parkinglot />} />
                {/* 주차장 지도*/}
                <Route path="/parking/:pkId/map" element={<ParkingMap />} />
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
                {/* 리뷰페이지 */}
                <Route path="/parking/:pkId/review" element={<Review />} />
                {/* 리뷰작성페이지 */}
                <Route
                  path="/parking/:pkId/review/write"
                  element={<Editreview />}
                />
                {/* 개인정보수정페이지 */}
                <Route path="/mypage/:id/edit" element={<Editmypage />} />
              </Routes>
            </div>
          </div>
        </div>
      </div>
    </Router>
  );
}
export default App;
