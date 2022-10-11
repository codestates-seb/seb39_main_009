// react-icons
import { AiOutlineLeft } from "react-icons/ai";
import { GrClose } from "react-icons/gr";

import "./Reservation.css";
import axios from "../../apis/axios";
import React, { useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import ReservationInner from "../../component/Reservation/ReservationInner";
import ReservationCaution from "../../component/Reservation/ReservationCaution";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";
import { AuthContext } from "../../context/AuthContext";

const Reservation = () => {
  const navigate = useNavigate();
  const { reservId } = useParams();
  const { auth } = useContext(AuthContext);

  const { data, loading, error } = useFetch(`/member/reservation/${reservId}`);

  if (loading) {
    return <Loading />;
  }
  if (error) {
    return <Error />;
  }

  const handleCancel = () => {
    if (window.confirm(`예약을 취소하시겠습니까?`)) {
      axios
        .delete(
          `/pay/${reservId}`,
          {
            headers: {
              "Content-Type": "application/json",
              authorization: auth,
            },
          },
          { withCredentials: true }
        )
        .then(navigate(`/`))
        .catch((err) => {
          console.log(err);
        });
    }
  };

  return (
    <div className="reservation_container">
      <div className="reservation_header">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(`/reservation`);
          }}
        />
        <h2>나의 예약 내역</h2>
        <GrClose
          className="r_closebtn"
          size={22}
          onClick={() => navigate(`/find`)}
        />
      </div>
      <div className="reservation_main">
        <ReservationInner data={data} reservId={reservId} />
        <div className="reservCaution">
          <ReservationCaution />
        </div>
      </div>
      <div className="reservation_footer">
        <button onClick={handleCancel}>예약 취소</button>
      </div>
    </div>
  );
};

export default Reservation;
