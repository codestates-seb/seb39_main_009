// react-icons
import { AiFillStar, AiOutlineStar } from "react-icons/ai";

import "./../../pages/Parkinglot/Parkinglot.css";
import axios from "../../apis/axios";
import React, { useState, useContext } from "react";
import { AuthContext } from "../../context/AuthContext";

const ParkingBookmark = ({ pkId }) => {
  const { auth } = useContext(AuthContext);
  const [bookmark, setBookmark] = useState(false);

  // 즐겨찾기 받아오기: onClick시 즐겨찾기 post요청 + 즐겨찾기 state true
  const handleGetBookmark = () => {
    axios
      .get(
        `/bookmarkCheck/${pkId}`,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((res) => {
        if (res.data.bookmark) {
          setBookmark(true);
        } else {
          setBookmark(false);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 즐겨찾기 활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state true
  const handleOnBookmark = () => {
    axios
      .post(
        `/bookmark`,
        { id: pkId },
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then(() => setBookmark(true))
      .catch((err) => {
        console.log(err);
      });
  };

  // 즐겨찾기 비활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state false
  const handleOffBookmark = () => {
    axios
      .delete(
        `/bookmark/${pkId}`,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then(() => setBookmark(false))
      .catch((err) => {
        console.log(err);
      });
  };

  handleGetBookmark();

  return (
    <div className="starIcon">
      {bookmark ? (
        <AiFillStar size={24} onClick={handleOffBookmark} />
      ) : (
        <AiOutlineStar size={24} onClick={handleOnBookmark} />
      )}
    </div>
  );
};

export default ParkingBookmark;
