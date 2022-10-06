import React from "react";
import { Route, Routes } from "react-router-dom";
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
import Review from "./pages/Review/Review";
import ParkSearch from "./pages/ParkSearch/ParkSearch";
import EditReview from "./pages/Review/Editreview";
import ParkReservation from "./pages/ParkReservation/ParkReservation";
import WriteReview from "./pages/Review/WriteReview";
import Pay from "./pages/Pay/Pay";
import AreaparkModal from "./component/Modal/ParkSearchModal/AreaparkModal";
import HealthCheck from "./test/HealthCheck";


const Router = () => {
  return (
                  <Routes>
                    {/* 로딩컴포넌트 - 임시 메인 */}
                    <Route path="/" element={<HealthCheck />} />
                    {/* <Route path="/" element={<Firstpage />} /> */}
                    {/* 로그인창 */}
                    <Route path="/login" element={<Login />} />
                    {/* 회원가입창 */}
                    <Route path="/join" element={<SignUp />} />
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
                      element={<WriteReview />}
                    />
                    {/* 리뷰수정페이지 */}
                    <Route
                      path="/parking/:pkId/review/:reviewId"
                      element={<EditReview />}
                    />
                    {/* 개인정보수정페이지 */}
                    <Route path="/mypage/:id/edit" element={<Editmypage />} />
                    {/* 주차장 조건검색 페이지 */}
                    <Route path="/find" element={<ParkSearch />} />
                    {/* 주차장 예약페이지 */}
                    <Route
                      path="/parking/:id/sector"
                      element={<ParkReservation />}
                    />
                    {/* 주차장 지역 조건검색페이지 */}
                    <Route path="/find/location" element={<AreaparkModal />} />
                    {/* 결제 페이지 :reservId로 변경예정 */}
                    <Route path="/pay/reservId" element={<Pay />} />
                    {/* 첫번쨰페이지 */}
                    {/* <Route path="/firstpage" element={<Firstpage />} /> */}
                  </Routes>
  );
};

export default Router;
