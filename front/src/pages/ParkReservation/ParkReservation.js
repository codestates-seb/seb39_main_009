//react-icons
import { GrClose } from "react-icons/gr";

import "./ParkReservation.css";
import { axiosPrivate } from "../../apis/axios";
import React, { useContext, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import { ReservContext } from "../../context/ReservContext";
import Error from "../../component/Error/Error";
import Loading from "../../component/Loading/Loading";

const ParkReservation = () => {
  const navigate = useNavigate();
  const { pkId } = useParams();
  const { reserv, setReserv } = useContext(ReservContext);
  const [validSpot, setValidSpot] = useState(0);

  const { data, loading, error } = useFetch(
    `/parking/${pkId}/reservation?parkingStartDateTime=${reserv.parkingStartDateTime}&parkingEndDateTime=${reserv.parkingEndDateTime}`
  );

  const handleSelect = (e) => {
    setValidSpot(e.target.value);
  };

  let reservData = {
    parkingStartDateTime: reserv.parkingStartDateTime,
    parkingEndDateTime: reserv.parkingEndDateTime,
    number: validSpot,
  };

  const handleReserv = () => {
    axiosPrivate
      .post(`/parking/${pkId}/reservation`, reservData)
      .then((res) => {
        navigate(`/pay/${pkId}/${res.data.reservNum}`);
      })
      .then()
      .catch((err) => {
        console.log(err);
        navigate(`/`);
      });
  };

  const handleCancel = () => {
    alert(`예약을 취소하시겠습니까?`);
    localStorage.removeItem("reserv");
    setReserv({});
    navigate(`/`);
  };

  return (
    <>
      {loading && <Loading />}
      {error && <Error />}
      <div className="preserv_container">
        <div className="preserv_header">
          <h2>결 제</h2>
          <GrClose className="closebtn" size={22} onClick={handleCancel} />
        </div>
        <div className="preserv_main">
          <img alt="주차장배치도" src={data.imageURL} width={"400px"} />
          <select onChange={handleSelect}>
            <option value="" selected>
              자리를 선택해주세요.
            </option>
            {data.validNum &&
              data.validNum.map((item, i) => (
                <option key={i} value={item.number}>
                  {item.number}
                </option>
              ))}
          </select>
          <button onClick={handleReserv}>다음 →</button>
        </div>
      </div>
    </>
  );
};

export default ParkReservation;