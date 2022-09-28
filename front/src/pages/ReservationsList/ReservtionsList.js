// react-icons
import { GrClose } from "react-icons/gr";
import { AiOutlineRight } from "react-icons/ai";

import "./ReservationsList.css";
import React from "react";
import { useNavigate } from "react-router-dom";
import useDateFormat from "../../hooks/useDateFormat";
import useFetch from "../../hooks/useFetch";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const ReservationsList = () => {
  const navigate = useNavigate();
  const dateFormat = useDateFormat();

  const { data, loading, error } = useFetch(`/member/reservation`);

  if (loading) {
    return <Loading />;
  }
  if (error) {
    return <Error />;
  }

  return (
    <div className="rezlist_container">
      <div className="rezlist_header">
        <h2>나의 예약 내역</h2>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <div className="rezlist_main">
        {data &&
          data.map((item) => (
            <div
              className="single_rez"
              key={item.reservNum}
              onClick={() => {
                navigate(`/reservation/${item.reservNum}`);
              }}
            >
              <div className="single_rez_header">
                <p>{item.name}</p>
                <div>
                  <p>예약 상세</p>
                  <AiOutlineRight
                    onClick={() => {
                      navigate(`/reservation/${item.reservNum}`);
                    }}
                    className="rez_icons icon-href"
                    size={19}
                  />
                </div>
              </div>
              <div className="single_rez_main">
                <div>
                  <p>예약번호</p>
                  <p>{item.reservNum}</p>
                </div>
                <div>
                  <p>예약구역</p>
                  <p>{item.number}</p>
                </div>
                <div className="rez_time">
                  <div>
                    <p>입 차</p>
                    <p>{dateFormat(item.parkingStartTime)}</p>
                  </div>
                  <div>
                    <p>출 차</p>
                    <p>{dateFormat(item.parkingEndTime)}</p>
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
