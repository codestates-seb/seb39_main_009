import React from "react";
import "./Reservation.css";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import { GrClose } from "react-icons/gr";

const Reservation = () => {
  const navigate = useNavigate();

  return (
    <div className="reservation_container">
      <div className="reservation_header">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <p>나의 예약 내역</p>
        <GrClose
          className="r_closebtn"
          size={22}
          onClick={() => navigate(`/`)}
        />
      </div>
      <div className="reservation_main">예약자 정보</div>
    </div>
  );
};

export default Reservation;
