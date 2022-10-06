// react-icons
import { AiOutlineLeft } from "react-icons/ai";
import { GrClose } from "react-icons/gr";

import "./Reservation.css";
import { axiosPrivate } from "../../apis/axios";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import ReservationInner from "../../component/Reservation/ReservationInner";
import ReservationCaution from "../../component/Reservation/ReservationCaution";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const Reservation = () => {
  const navigate = useNavigate();
  const { reservId } = useParams();

  const { data, loading, error } = useFetch(`/member/reservation/${reservId}`);

  if (loading) {
    return <Loading />;
  }
  if (error) {
    return <Error />;
  }

  const handleCancel = () => {
    alert(`예약을 취소하시겠습니까?`);
    axiosPrivate
      .delete(`/pay/${data.reservationId}`)
      .then(navigate(`/reservation`))
      .catch((err) => {
        console.log(err);
      });
  };

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
        <ReservationInner data={data} reservId={reservId} />
        <ReservationCaution />
      </div>
      <div className="reservation_footer">
        <button onClick={handleCancel}>예약 취소</button>
      </div>
    </div>
  );
};

export default Reservation;
