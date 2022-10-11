// react-icons
import { GrClose } from "react-icons/gr";
import { AiOutlineRight } from "react-icons/ai";

import "./ReservationsList.css";
import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import useDateFormat from "../../hooks/useDateFormat";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";
import { AuthContext } from "../../context/AuthContext";
import axios from "../../apis/axios";

const ReservationsList = () => {
  const navigate = useNavigate();
  const dateFormat = useDateFormat();

  // const { data, loading, error } = useFetch(`/member/reservation`);

  const { auth } = useContext(AuthContext);
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get(
        `/member/reservation`,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
      });
  }, [auth]);

  return (
    <>
      {loading && <Loading />}
      {error && <Error />}
      <div className="rezlist_container">
        <div className="rezlist_header">
          <h2>나의 예약 내역</h2>
          <GrClose
            className="closebtn"
            size={22}
            onClick={() => navigate(`/find`)}
          />
        </div>
        <div className="rezlist_main">
          {data.length === 0 ? (
            <div>
              <p>예약이 없습니다.</p>
            </div>
          ) : (
            <>
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
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default ReservationsList;
