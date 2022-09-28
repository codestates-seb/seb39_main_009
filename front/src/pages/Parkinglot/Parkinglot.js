// react-icons
import { AiOutlineLeft, AiFillStar, AiOutlineStar } from "react-icons/ai";

import "./Parkinglot.css";
import { axiosPrivate } from "../../apis/axios";
import React, { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import RandomImg from "../../assets/parkinglot/RandomImg";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const Parkinglot = () => {
  const navigate = useNavigate();

  // useParam 사용하여 주차장id를 얻어 api 호출 후 데이터 받아오기.
  const { pkId } = useParams();
  const [bookmark, setBookmark] = useState(false);

  // 즐겨찾기 받아오기: onClick시 즐겨찾기 post요청 + 즐겨찾기 state true
  const handleGetBookmark = () => {
    axiosPrivate
      .get(`/bookmarkCheck/${pkId}`)
      .then((res) => {
        if (res.data.bookmark) {
          setBookmark(true);
        } else {
          setBookmark(false);
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // 즐겨찾기 활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state true
  const handleOnBookmark = () => {
    axiosPrivate
      .post(`/bookmark`, { id: pkId })
      .then(() => setBookmark(true))
      .catch((err) => {
        console.log(err);
      });
  };

  // 즐겨찾기 비활성화 : onClick시 즐겨찾기 post요청 + 즐겨찾기 state false
  const handleOffBookmark = () => {
    axiosPrivate
      .delete(`/bookmark/${pkId}`)
      .then(() => setBookmark(false))
      .catch((err) => {
        console.log(err);
      });
  };

  handleGetBookmark();
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
  );
};

export default Parkinglot;
