import React from "react";
import "./Reservation.css";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import { GrClose } from "react-icons/gr";
import ReservationInner from "../../component/Reservation/ReservationInner";
import ReservationCaution from "../../component/Reservation/ReservationCaution";

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
        <h2>나의 예약 내역</h2>
        <GrClose
          className="r_closebtn"
          size={22}
          onClick={() => navigate(`/`)}
        />
      </div>
      <div className="reservation_main">
        <ReservationInner />
        <ReservationCaution />
      </div>
      <div className="reservation_footer">
        <button>예약 취소</button>
      </div>
    </div>
  );
};

export default Reservation;
