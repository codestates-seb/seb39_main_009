import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
import useFetch from "../../useFetch";
import "./SideBar.css";
import { GrClose } from "react-icons/gr";
import { AiOutlineRight } from "react-icons/ai";
import { BsFillBookmarkStarFill } from "react-icons/bs";
import { BiLogOut } from "react-icons/bi";

const SideBar = ({ show, setShow, handleSideClose, handlelogOut }) => {
  const sidebarBackRef = useRef();
  const navigate = useNavigate();

  const sideLogout = () => {
    handlelogOut();
    navigate(`/`);
  };

  const { data } = useFetch(`/api/member`);

  return (
    <>
      {show ? (
        <>
          <div
            className="sidebar_back"
            ref={sidebarBackRef}
            onClick={(e) => {
              if (sidebarBackRef.current === e.target) {
                setShow(false);
              }
            }}
          />
          <div className="sidebar">
            <div className="sidebar_header">
              <p>주 차 G O</p>
              <GrClose
                className="closebtn"
                size={22}
                onClick={handleSideClose}
              />
            </div>
            <div className="userInfo">
              {localStorage.authorization ? (
                <>
                  <p
                    onClick={() => {
                      navigate(`/mypage/${data.id}`);
                      setShow(!show);
                    }}
                  >
                    {data.name}
                  </p>
                  <p>{data.email}</p>
                </>
              ) : (
                <>
                  <p
                    onClick={() => {
                      navigate(`/login`);
                      setShow(!show);
                    }}
                  >
                    Login
                  </p>
                  <p>로그인 해주세요!</p>
                </>
              )}
            </div>
            <div className="mainMenu_back" />
            <div className="mainMenu">
              {localStorage.authorization ? (
                <>
                  <div className="check_reservation">
                    <p>예약 내역</p>
                    <div>
                      <p>{data.numOfReserv}</p>
                      <p>건</p>
                      <AiOutlineRight
                        onClick={() => {
                          navigate(`/reservation`);
                          setShow(!show);
                        }}
                        className="side_icons icon-href"
                        size={22}
                      />
                    </div>
                  </div>
                  <div className="check_point">
                    <p>충전금</p>
                    <div>
                      <p>{data.point === null ? 0 : data.point}</p>
                      <p>원</p>
                    </div>
                  </div>
                  <div className="side_bookmark">
                    <BsFillBookmarkStarFill className="side_icons" size={22} />
                    <p
                      onClick={() => {
                        navigate(`/bookmark`);
                        setShow(!show);
                      }}
                    >
                      즐겨찾기
                    </p>
                  </div>
                </>
              ) : (
                <>
                  <div className="check_reservation">
                    <p>예약 내역</p>
                    <div>
                      <p>0</p>
                      <p>건</p>
                      <AiOutlineRight
                        onClick={() => {
                          alert("로그인이 필요합니다.");
                          navigate(`/login`);
                          setShow(!show);
                        }}
                        className="side_icons icon-href"
                        size={22}
                      />
                    </div>
                  </div>
                  <div className="check_point">
                    <p>충전금</p>
                    <div>
                      <p>0</p>
                      <p>원</p>
                    </div>
                  </div>
                  <div className="side_bookmark">
                    <BsFillBookmarkStarFill className="side_icons" size={22} />
                    <p
                      onClick={() => {
                        alert("로그인이 필요합니다.");
                        navigate(`/login`);
                        setShow(!show);
                      }}
                    >
                      즐겨찾기
                    </p>
                  </div>
                </>
              )}
            </div>
            <div className="subMenu">
              <p
                onClick={() => {
                  navigate(`/notice`);
                  setShow(!show);
                }}
              >
                공지사항
              </p>
              <p
                onClick={() => {
                  navigate(`/partnership`);
                  setShow(!show);
                }}
              >
                제휴 문의
              </p>
              <p
                onClick={() => {
                  navigate(`/question`);
                  setShow(!show);
                }}
              >
                1:1 문의
              </p>
              {localStorage.authorization ? (
                <>
                  <div className="side_logout" onClick={sideLogout}>
                    <BiLogOut className="side_icons" size={22} />
                    <p>로그아웃</p>
                  </div>
                </>
              ) : null}
            </div>
          </div>
        </>
      ) : null}
    </>
  );
};

export default SideBar;

// const data = {
//   id: "1",
//   name: "김파킹",
//   email: "aaa@aaa.com",
//   NumOfReserv: 1,
//   point: 25000,
// };
