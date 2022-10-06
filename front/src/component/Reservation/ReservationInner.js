import "../../pages/ReservationDetail/Reservation.css";
import React from "react";
import useGetTime from "../../hooks/useGetTime";
import useDateFormat from "../../hooks/useDateFormat";
import { useNavigate } from "react-router-dom";

const ReservationInner = ({ data, reservId }) => {
  const navigate = useNavigate();
  const dateFormat = useDateFormat();
  const getTime = useGetTime();

  return (
    <div className="reservation_inner">
      <div>
        <p>예약자 정보</p>
        <div>
          <p>예약자</p>
          <p>{data.email}</p>
        </div>
        <div>
          <p>연락처</p>
          <p>{data.phoneNumber}</p>
        </div>
      </div>
      <div>
        <p>예약 내용</p>
        <div>
          <p>예약번호</p>
          <p>{reservId}</p>
        </div>
        <div>
          <p>주차장</p>
          <p
            className="parkinglink"
            onClick={() => navigate(`/parking/${data.parkingId}`)}
          >
            {data.parkingName}
          </p>
        </div>
        <div>
          <p>주차면</p>
          <p>{data.parkingPlaceNumber}</p>
        </div>
        <div>
          <p>기간</p>
          <div>
            <p>{dateFormat(data.parkingStartDateTime)}</p>
            <p>~ {dateFormat(data.parkingEndDateTime)}</p>
          </div>
        </div>
      </div>
      <div>
        <p>결제 금액</p>
        <div>
          <p>
            예약 요금 (
            {getTime(data.parkingStartDateTime, data.parkingEndDateTime)})
          </p>
          <p>{data.price} 포인트</p>
        </div>
      </div>
    </div>
  );
};

export default ReservationInner;
