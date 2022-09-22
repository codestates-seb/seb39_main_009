import React, { useState } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../useFetch";
import "./Parkinglot.css";
import RandomImg from "../../assets/parkinglot/RandomImg";
import { AiOutlineLeft, AiFillStar, AiOutlineStar } from "react-icons/ai";
import Loading from "../../component/Loading/Loading";

const Parkinglot = () => {
  const navigate = useNavigate();

  // useParam 사용하여 주차장id를 얻어 api 호출 후 데이터 받아오기.
  const { pkId } = useParams();
  const { data, loading } = useFetch(`/api/parking/${pkId}`);

  const [bookmark, setBookmark] = useState(false);

  // 즐겨찾기 활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state true
  const handleOnBookmark = () => {
    axios.post(`/api/bookmark`, { id: pkId }).catch((err) => {
      console.log(`bookmarkOnErr : ${err}`);
    });
    setBookmark(true);
  };
  // 즐겨찾기 비활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state false
  const handleOffBookmark = () => {
    axios.delete(`/api/bookmark/${pkId}`).catch((err) => {
      console.log(`bookmarkOffErr : ${err}`);
    });
    setBookmark(false);
  };

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
            <p>주차장 정보</p>
          </div>
          <div className="parkinglot_main">
            <RandomImg size={"pakinglot_size"} />
            <div className="starIcon">
              {bookmark ? (
                <AiFillStar size={24} onClick={handleOffBookmark} />
              ) : (
                <AiOutlineStar size={24} onClick={handleOnBookmark} />
              )}
            </div>
            <div>
              <p>{data.name}</p>
              <p>{data.address}</p>
            </div>
            <button
              onClick={() => {
                navigate(`/parking/${pkId}/map`);
              }}
            >
              지도보기
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default Parkinglot;

// const data = {
//   name: "안나주차장",
//   address: "주소",
//   weekdayOpen: "09:00:00",
//   weekdayClose: "22:00:00",
//   tel: "010-0000-0000",
//   price: 1000,
//   capacity: 20,
//   satOpen: "08:00:00",
//   satClose: "23:00:00",
//   sunOpen: "08:00:00",
//   sunClose: "23:50:00",
//   partnership: true,
//   type: "타입",
//   dayMax: 20000,
//   LAT: 37.492182,
//   LNG: 127.03804,
//   parkingMap: "주차배치도 주소",
// };
