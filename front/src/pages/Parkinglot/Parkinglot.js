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
          <button onClick={() => navigate(`/parking/${pkId}/sector`)}>
            예약하기
          </button>
        </div>
      ) : null}
    </div>
  );
};

export default Parkinglot;
