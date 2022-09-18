import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
import "./SideBar.css";
import { GrClose } from "react-icons/gr";
import { AiOutlineRight } from "react-icons/ai";
import { BsFillBookmarkStarFill } from "react-icons/bs";
import { BiLogOut } from "react-icons/bi";

const SideBar = ({ show, setShow, handleSideClose }) => {
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
              <p>This is SideBar</p>
              <GrClose
                className="closebtn"
                size={22}
                onClick={handleSideClose}
              />
            </div>
            <div className="userInfo">
              <p>UserName</p>
              <p>UserEmail@domain.com</p>
            </div>
            <div className="mainMenu_back" />
            <div className="mainMenu">
              <div className="check_reservation">
                <p>예약 내역</p>
                <div>
                  <p>N</p>
                  <p>건</p>
                  <AiOutlineRight className="icons icon-href" size={22} />
                </div>
              </div>
              <div className="check_point">
                <p>충전금</p>
                <div>
                  <p>nnn,nnn</p>
                  <p>원</p>
                </div>
              </div>
              <div className="bookmark">
                <BsFillBookmarkStarFill className="icons" size={22} />
                <p>즐겨찾기</p>
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
              <div className="side_logout">
                <BiLogOut className="icons" size={22} />
                <p>로그아웃</p>
              </div>
            </div>
          </div>
        </>
      ) : null}
    </>
  );
};

export default SideBar;
