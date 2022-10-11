// react-icons
import { GrClose } from "react-icons/gr";
import { BsFillPencilFill } from "react-icons/bs";

import "./Pay.css";
import axios from "../../apis/axios";
import React, { useContext, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import { ReservContext } from "../../context/ReservContext";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";
import useDateFormat from "../../hooks/useDateFormat";
import useGetTime from "../../hooks/useGetTime";
import ReservationCaution from "../../component/Reservation/ReservationCaution";
import CarNumChange from "../../component/Modal/CarNumChange";
import { CarNumContext } from "../../context/CarNumContext";
import { AuthContext } from "../../context/AuthContext";

const Pay = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);
  const { pkId, reservId } = useParams();
  const { reserv, setReserv } = useContext(ReservContext);
  const { newCarNum, setNewCarNum } = useContext(CarNumContext);
  const dateFormat = useDateFormat();
  const getTime = useGetTime();
  const [modal, setModal] = useState(false);
  const [check, setCheck] = useState(false);

  const toggleModal = () => {
    setModal(!modal);
  };

  const handleReserv = () => {
    if (check === false) {
      alert(
        `주의사항에 동의하지 않으시면, 예약이 불가합니다.\n확인 후 체크 해주세요.`
      );
      return false;
    } else {
      axios
        .post(
          `/pay/${reservId}`,
          {
            headers: {
              "Content-Type": "application/json",
              authorization: auth,
            },
          },
          { withCredentials: true }
        )
        .then(alert(`감사합니다.\n예약, 결제가 완료되었습니다!`))
        .then(navigate(`/reservation/${reservId}`))
        .catch((err) => {
          console.log(err);
        });
      localStorage.removeItem("reserv");
      setReserv({});
      setNewCarNum("");
    }
  };

  const handleCancel = () => {
    if (window.confirm(`결제을 취소하시겠습니까?`)) {
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
        .then((res) => {
          navigate(`/find`);
          setReserv({});
          setNewCarNum("");
        })
        .catch((err) => {
          console.log(err);
        });
    }
  };

  const { data, loading, error } = useFetch(
    `parking/${pkId}/calculation?parkingStartDateTime=${reserv.parkingStartDateTime}&parkingEndDateTime=${reserv.parkingEndDateTime}`
  );

  return (
    <>
      {loading && <Loading />}
      {error && <Error />}
      <div className="pay_container">
        <div className="pay_header">
          <h2>결 제</h2>
          <GrClose className="closebtn" size={22} onClick={handleCancel} />
        </div>
        <div className="pay_main">
          <div className="pay_info">
            <p>
              {getTime(data.parkingStartDateTime, data.parkingEndDateTime)} 예약
            </p>
            <div>
              <p>예약시간</p>
              <div>
                <p>{dateFormat(data.parkingStartDateTime)}</p>
                <p>~ {dateFormat(data.parkingEndDateTime)}</p>
              </div>
            </div>
            <div>
              <p>차량번호</p>
              <p>{newCarNum === "" ? data.carNumber : newCarNum}</p>
              <BsFillPencilFill className="penIcon" onClick={toggleModal} />
              <CarNumChange
                modal={modal}
                setModal={setModal}
                toggleModal={toggleModal}
              />
            </div>
          </div>
          <div className="payCount">
            <p>결제금액</p>
            <p>{data.price} P</p>
          </div>
          <div className="payCaution">
            <ReservationCaution />
          </div>
          <div className="payTerm">
            <input
              type="checkbox"
              name="term_check"
              onClick={() => setCheck(!check)}
            />
            <p>모두 동의합니다.</p>
          </div>
          <button className="paybtn" onClick={handleReserv}>
            결제하기
          </button>
        </div>
      </div>
    </>
  );
};

export default Pay;
