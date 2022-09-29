// react-icons
import { AiOutlineLeft } from "react-icons/ai";

import "./Parkinglot.css";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import { Map, MapMarker } from "react-kakao-maps-sdk"; //카카오 지도 미들웨어
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const ParkingMap = () => {
  const navigate = useNavigate();
  const { pkId } = useParams();
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
        <p>{data.name}</p>
      </div>
      <div className="parkingmap_main">
        <div className="parkingmap_address">
          <p>위치</p>
          <p>{data.address}</p>
        </div>
        <Map
          center={{ lat: data.lat, lng: data.lng }}
          style={{ width: "100%", height: "50%" }}
        >
          <MapMarker position={{ lat: data.lat, lng: data.lng }} />
        </Map>
      </div>
    </div>
  );
};

export default ParkingMap;
