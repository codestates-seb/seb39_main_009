// react-icons
import { AiOutlineRight } from "react-icons/ai";
import { BsFillBookmarkStarFill } from "react-icons/bs";
import { BiLogOut } from "react-icons/bi";

import "./SideBar.css";
import axios from "../../apis/axios";
import React, { useContext, useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import Error from "../Error/Error";

const SideBarAfter = ({ show, setShow, handleSideClose, handlelogOut }) => {
  const sidebarBackRef = useRef();
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);
  const [data, setData] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    axios
      .get(
        `/member`,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((response) => {
        setData(response.data);
      })
      .catch((err) => {
        setError(err);
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
      }, 1000);
  }, [show, auth]);

  const sideLogout = () => {
    handlelogOut();
    navigate(`/`);
  };

  return (
    <>
      {loading && ""}
      {error && <Error />}
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
                  navigate(`/mypage/${data.id}`);
                  setShow(!show);
                }}
              >
                {data.name}
              </p>
              <p>{data.email}</p>
            </div>
            <div className="mainMenu_back" />
            <div className="mainMenu">
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
                  <p>
                    {data.point === null
                      ? 0
                      : data.point
                          .toString()
                          .replace(/\B(?=(\d{3})+(?!\d))/g, ",")}
                  </p>
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
              <div className="side_logout" onClick={sideLogout}>
                <BiLogOut className="side_icons" size={22} />
                <p>로그아웃</p>
              </div>
            </div>
          </div>
        </>
      ) : null}
    </>
  );
};

export default SideBarAfter;
