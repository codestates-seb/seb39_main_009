import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
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
              <p>This is SideBar</p>
              <GrClose
                className="closebtn"
                size={22}
                onClick={handleSideClose}
              />
            </div>

            <div className="userInfo">
              {localStorage.Token ? (
                <>
                  <p>{data.nickname}</p>
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
              {localStorage.Token ? (
                <>
                  <div className="check_reservation">
                    <p>예약 내역</p>
                    <div>
                      <p>{data.NumOfReserv}</p>
                      <p>건</p>
                      <AiOutlineRight className="icons icon-href" size={22} />
                    </div>
                  </div>
                  <div className="check_point">
                    <p>충전금</p>
                    <div>
                      <p>{data.point}</p>
                      <p>원</p>
                    </div>
                  </div>
                </>
              ) : (
                <>
                  <div className="check_reservation">
                    <p>예약 내역</p>
                    <div>
                      <p>0</p>
                      <p>건</p>
                      <AiOutlineRight className="icons icon-href" size={22} />
                    </div>
                  </div>
                  <div className="check_point">
                    <p>충전금</p>
                    <div>
                      <p>0</p>
                      <p>원</p>
                    </div>
                  </div>
                </>
              )}
              <div className="side_bookmark">
                <BsFillBookmarkStarFill className="icons" size={22} />
                <p
                  onClick={() => {
                    navigate(`/bookmark`);
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
              <p>제휴 문의</p>
              <p>1:1 문의</p>
              {localStorage.Token ? (
                <>
                  <div className="side_logout" onClick={sideLogout}>
                    <BiLogOut className="icons" size={22} />
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

const data = {
  nickname: "김파킹",
  email: "aaa@aaa.com",
  NumOfReserv: 1,
  point: 25000,
};
