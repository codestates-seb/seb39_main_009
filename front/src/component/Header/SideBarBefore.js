// react-icons
import { AiOutlineRight } from "react-icons/ai";
import { BsFillBookmarkStarFill } from "react-icons/bs";

import "./SideBar.css";
import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";

const SideBarBefore = ({ show, setShow, handleSideClose }) => {
  const sidebarBackRef = useRef();
  const navigate = useNavigate();

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
              <img
                alt="logo"
                src={process.env.PUBLIC_URL + "/juagologo.png"}
                width={"100px"}
              />

              {/* <GrClose
                className="closebtn"
                size={22}
                onClick={handleSideClose}
              /> */}
            </div>
            <div className="userInfo">
              <p
                onClick={() => {
                  navigate(`/login`);
                  setShow(!show);
                }}
              >
                Login
              </p>
              <p>로그인 해주세요!</p>
            </div>
            <div className="mainMenu_back" />
            <div className="mainMenu">
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
            </div>
          </div>
        </>
      ) : null}
    </>
  );
};

export default SideBarBefore;

// const data = {
//   id: "1",
//   name: "김파킹",
//   email: "aaa@aaa.com",
//   NumOfReserv: 1,
//   point: 25000,
// };
