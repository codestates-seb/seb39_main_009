import React from "react";
import "./ReservationsList.css";
import { useNavigate } from "react-router-dom";
import { GrClose } from "react-icons/gr";
import { AiOutlineRight } from "react-icons/ai";

const ReservationsList = () => {
  const DateFormat = (fulldate) => {
    const putZero = (d) => {
      if (d.length === 1) {
        return `0${d}`;
      } else {
        return `${d}`;
      }
    };

    let year = String(new Date(fulldate).getFullYear());
    let month = putZero(String(new Date(fulldate).getMonth()));
    let date = putZero(String(new Date(fulldate).getDate()));
    let hours = putZero(String(new Date(fulldate).getHours()));
    let minutes = putZero(String(new Date(fulldate).getMinutes()));

    return `${year}.${month}.${date} ${hours}:${minutes}`;
  };

  const navigate = useNavigate();
  return (
    <div className="rezlist_container">
      <div className="rezlist_header">
        <p>나의 예약 내역</p>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <div className="rezlist_main">
        {data &&
          data.map((rez) => (
            <div className="single_rez" key={rez.reservNum}>
              <div className="single_rez_header">
                <p>{rez.name}</p>
                <div>
                  <p>예약 상세</p>
                  <AiOutlineRight
                    onClick={() => {
                      navigate(`/reservation/${rez.reservNum}`);
                    }}
                    className="rez_icons icon-href"
                    size={19}
                  />
                </div>
              </div>
              <div className="single_rez_main">
                <div>
                  <p>예약번호</p>
                  <p>{rez.reservNum}</p>
                </div>
                <div>
                  <p>예약구역</p>
                  <p>{rez.number}</p>
                </div>
                <div className="rez_time">
                  <div>
                    <p>입 차</p>
                    <p>{DateFormat(rez.parkingStartTime)}</p>
                  </div>
                  <div>
                    <p>출 차</p>
                    <p>{DateFormat(rez.parkingEndTime)}</p>
                  </div>
                </div>
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default ReservationsList;

const data = [
  {
    name: "안나주차장",
    number: 2,
    reservNum: 4,
    parkingStartTime: "2022-09-25T15:30:00",
    parkingEndTime: "2022-09-25T17:30:00",
  },
  {
    name: "우종주차장",
    number: 3,
    reservNum: 1,
    parkingStartTime: "2022-09-26T10:00:00",
    parkingEndTime: "2022-09-26T13:30:00",
  },
];
