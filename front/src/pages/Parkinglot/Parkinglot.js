// react-icons
import { AiOutlineLeft } from "react-icons/ai";

import "./Parkinglot.css";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";
import ParkingBookmark from "../../component/Parkinglot/ParkingBookmark";
import ParkingTabs from "../../component/Parkinglot/ParkingTabs/ParkingTabs";

const Parkinglot = () => {
  const { pkId } = useParams();
  const navigate = useNavigate();

  const { data, loading, error } = useFetch(`/parking/${pkId}`);

  if (loading) {
    return <Loading />;
  }
  if (error) {
    return <Error />;
  }

  return (
    <div className="parkinglot_container">
      <div className="parkinglot_header">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <h2>주차장 정보</h2>
      </div>
      <div className="parkinglot_title">
        <ParkingBookmark pkId={pkId} />
        <div>
          <p>{data.name}</p>
          <p>{data.address}</p>
          <p>{data.tel}</p>
        </div>
        <button
          onClick={() => {
            navigate(`/parking/${pkId}/map`);
          }}
        >
          지도보기
        </button>
      </div>
      <div className="parkinglot_main">
        <ParkingTabs data={data} />
      </div>
      {data.partnership ? (
        <div className="parkinglot_footer">
          <button>예약하기</button>
        </div>
      ) : null}
    </div>
  );
};

export default Parkinglot;

// const data = {
//   name: "가짜주차장",
//   address: "역삼 3가",
//   weekdayOpen: "09:00",
//   weekdayClose: "18:00",
//   tel: "010-1111-1111",
//   basicTime: 30,
//   basicCharge: 500,
//   addUnitTime: 5,
//   addUnitCharge: 150,
//   capacity: 10,
//   satOpen: "09:00",
//   satClose: "18:00",
//   sunOpen: "09:00",
//   sunClose: "18:00",
//   partnership: true,
//   parkingSeparation: "민영",
//   parkingType: "노외",
//   spacial_management: "경차, 장애인, 유공자, 자녀",
//   parkingChargeInfo: "혼합",
//   methodPay: "신용카드",
//   dayMaxPrice: 10000,
//   latitude: 37.492182,
//   longitude: 127.03804,
//   parkingMap: "https://parking-go-image.s3.ap-northeast-2.amazonaws.com/2.jpeg",
// };
