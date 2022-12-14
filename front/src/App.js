import "./layout.css"; // 레이아웃 CSS 입니다. Don't touch !
import "./App.css"; // 비어있으니 레이아웃 외 CSS 추가변경 원하시면 이곳에서 수정해주세요 !
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { useEffect, useState } from "react";
import useRefreshToken from "./hooks/useRefreshToken";
import useLocalStorage from "./hooks/useLocalStorage";
import { AuthContext } from "./context/AuthContext";
import { UserIdContext } from "./context/UserIdContext";
import { ReservContext } from "./context/ReservContext";
import Header from "./component/Header/Header";
import SignUp from "./pages/signup/Signup";
import Login from "./pages/login/Login";
import Welcome from "./pages/signup/Welcome";
import Notice from "./pages/Notice/Notice";
import Bookmark from "./pages/Bookmark/Bookmark";
import Mypage from "./pages/mypage/Mypage";
import Editmypage from "./pages/mypage/Editmypage";
import ReservationsList from "./pages/ReservationsList/ReservtionsList";
import Reservation from "./pages/ReservationDetail/Reservation";
import Partnership from "./pages/Partnership/Partnership";
import Question from "./pages/Question/Question";
import Parkinglot from "./pages/Parkinglot/Parkinglot";
import ParkingMap from "./pages/Parkinglot/ParkingMap";
import ParkSearch from "./pages/ParkSearch/ParkSearch";
import EditReview from "./pages/Review/Editreview";
import ParkReservation from "./pages/ParkReservation/ParkReservation";
import WriteReview from "./pages/Review/WriteReview";
import Pay from "./pages/Pay/Pay";
import AreaparkModal from "./component/Modal/ParkSearchModal/AreaparkModal";
import Outlayout from "./Outlayout/Outlayout";
// import HealthCheck from "./test/HealthCheck";
import ParkinglotBk from "./pages/Parkinglot/ParkinglotBk";
import { CarNumContext } from "./context/CarNumContext";

function App() {
  const [auth, setAuth] = useLocalStorage("auth", "");
  const [userId, setUserId] = useLocalStorage("userId", "");
  const [reserv, setReserv] = useLocalStorage("reserv", {});
  const [newCarNum, setNewCarNum] = useState("");

  const onSilentRefresh = useRefreshToken();
  useEffect(() => {
    setInterval(onSilentRefresh, 360000);
  });

  // 로그아웃
  const handlelogOut = () => {
    localStorage.removeItem("authorization");
    localStorage.removeItem("refreshtoken");
    localStorage.removeItem("auth");
    setAuth({});
    localStorage.removeItem("userId");
    setUserId({});
    localStorage.removeItem("reserv");
    setReserv({});
  };

  return (
    <AuthContext.Provider value={{ auth, setAuth }}>
      <UserIdContext.Provider value={{ userId, setUserId }}>
        <ReservContext.Provider value={{ reserv, setReserv }}>
          <CarNumContext.Provider value={{ newCarNum, setNewCarNum }}>
            <Router>
              <div className="container">
                <Outlayout />
                <div className="main_container">
                  <div className="header">
                    <Header handlelogOut={handlelogOut} />
                  </div>
                  <div className="main">
                    <div>
                      {/* ↓ 아래 main div 안에 페이지 추가해주시면 됩니다. */}
                      <Routes>
                        {/* 로딩컴포넌트 - 임시 메인 */}
                        <Route path="/" element={<Login />} />
                        {/* 로그인창 */}
                        <Route path="/login" element={<Login />} />
                        {/* 회원가입창 */}
                        <Route path="/join" element={<SignUp />} />
                        {/* 개인정보수정페이지 */}
                        <Route
                          path="/mypage/:id/edit"
                          element={<Editmypage />}
                        />
                        {/* welcome창 */}
                        <Route path="/welcome" element={<Welcome />} />
                        {/* 마이페이지 */}
                        <Route path="/mypage/:id" element={<Mypage />} />
                        {/* 주차장 정보 조회*/}
                        <Route path="/parking/:pkId" element={<Parkinglot />} />
                        {/* 주차장 정보 조회_즐겨찾기*/}
                        <Route
                          path="/parking/bk/:pkId/"
                          element={<ParkinglotBk />}
                        />
                        {/* 주차장 지도*/}
                        <Route
                          path="/parking/:pkId/map"
                          element={<ParkingMap />}
                        />
                        {/* 나의 예약 목록*/}
                        <Route
                          path="/reservation"
                          element={<ReservationsList />}
                        />
                        {/* 예약 상세 조회 */}
                        <Route
                          path="/reservation/:reservId"
                          element={<Reservation />}
                        />
                        {/* 공지사항 */}
                        <Route path="/notice" element={<Notice />} />
                        {/* 즐겨찾기 */}
                        <Route path="/bookmark" element={<Bookmark />} />
                        {/* 제휴문의 */}
                        <Route path="/partnership" element={<Partnership />} />
                        {/* 1:1문의 */}
                        <Route path="/question" element={<Question />} />
                        {/* 리뷰작성페이지 */}
                        <Route
                          path="/parking/:pkId/review/write"
                          element={<WriteReview />}
                        />
                        {/* 리뷰수정페이지 */}
                        <Route
                          path="/parking/:pkId/review/:reviewId"
                          element={<EditReview />}
                        />
                        {/* 개인정보수정페이지 */}
                        <Route
                          path="/mypage/:id/edit"
                          element={<Editmypage />}
                        />
                        {/* 주차장 조건검색 페이지 */}
                        <Route path="/find" element={<ParkSearch />} />
                        {/* 주차장 예약페이지 */}
                        <Route
                          path="/parking/:pkId/sector"
                          element={<ParkReservation />}
                        />
                        {/* 주차장 지역 조건검색페이지 */}
                        <Route
                          path="/find/location"
                          element={<AreaparkModal />}
                        />
                        {/* 결제 페이지 */}
                        <Route path="/pay/:pkId/:reservId" element={<Pay />} />
                      </Routes>
                    </div>
                  </div>
                </div>
              </div>
            </Router>
          </CarNumContext.Provider>
        </ReservContext.Provider>
      </UserIdContext.Provider>
    </AuthContext.Provider>
  );
}
export default App;
