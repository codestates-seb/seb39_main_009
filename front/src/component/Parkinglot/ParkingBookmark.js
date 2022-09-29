// react-icons
import { AiFillStar, AiOutlineStar } from "react-icons/ai";

import "./../../pages/Parkinglot/Parkinglot.css";
import { axiosPrivate } from "../../apis/axios";
import React from "react";
import { useState } from "react";

const ParkingBookmark = ({ pkId }) => {
  const [bookmark, setBookmark] = useState(false);

  // 즐겨찾기 받아오기: onClick시 즐겨찾기 post요청 + 즐겨찾기 state true
  const handleGetBookmark = () => {
    axiosPrivate
      .get(`/bookmarkCheck/${pkId}`)
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
    axiosPrivate
      .post(`/bookmark`, { id: pkId })
      .then(() => setBookmark(true))
      .catch((err) => {
        console.log(err);
      });
  };

  // 즐겨찾기 비활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state false
  const handleOffBookmark = () => {
    axiosPrivate
      .delete(`/bookmark/${pkId}`)
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
