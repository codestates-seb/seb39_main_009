import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../useFetch";
import { Map, MapMarker } from "react-kakao-maps-sdk";
import "./Parkinglot.css";
import { AiOutlineLeft } from "react-icons/ai";
import Loading from "../../component/Loading/Loading";

const ParkingMap = () => {
  const navigate = useNavigate();
  const { pkId } = useParams();
  const { data, loading } = useFetch(`/api/parking/${pkId}`);

  return (
    <>
      {loading ? (
        <Loading />
      ) : (
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
      )}
    </>
  );
};

export default ParkingMap;
