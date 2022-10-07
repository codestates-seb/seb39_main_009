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
  const [validSpot, setValidSpot] = useState("");

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
    if (validSpot === "") {
      alert(`자리를 선택해주세요.`);
      return false;
    } else {
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
    }
  };

  const handleCancel = () => {
    if (window.confirm(`예약을 취소하시겠습니까?`)) {
      localStorage.removeItem("reserv");
      setReserv({});
      navigate(`/find`);
    }
  };

  const goBack = () => {
    localStorage.removeItem("reserv");
    setReserv({});
    navigate(`/find`);
  };

  return (
    <>
      {loading && <Loading />}
      {error && <Error />}
      <div className="preserv_container">
        <div className="preserv_header">
          <h2>주차 자리 선택</h2>
          <GrClose className="closebtn" size={22} onClick={handleCancel} />
        </div>
        <div className="preserv_main">
          <div className="preserv_main_t">
            <h4>[ 배치도 ]</h4>
            <p>{data.parkingName}</p>
          </div>
          <div className="img_box">
            <img alt="주차장배치도" src={data.imageURL} width={"365px"} />
          </div>
          <select onChange={handleSelect}>
            <option value="" selected>
              {data.validNum && data.validNum.length !== 0
                ? "자리를 선택해주세요."
                : "현재 예약 가능한 자리가 없습니다."}
            </option>
            {data.validNum &&
              data.validNum.map((item, i) => (
                <option key={i} defaultValue={item.number} value={item.number}>
                  {item.number}번
                </option>
              ))}
          </select>
          {data.validNum && data.validNum.length !== 0 ? (
            <>
              <button onClick={handleReserv}>다음 →</button>
            </>
          ) : (
            <>
              <button onClick={goBack}>← 뒤로</button>
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default ParkReservation;
