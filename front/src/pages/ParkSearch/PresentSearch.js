import { useState } from "react";
import Presentsearchlist from "./Presentsearchlist";
import Loading from "../../component/Loading/Loading";
import { useEffect } from "react";
import "./ParkSearch.css";

const PresentSearch = () => {
  // 최근내역 받아온 데이터
  const [data, setData] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${process.env.REACT_APP_BASE_URL}/api/history`, {
      headers: {
        authorization: localStorage.getItem("authorization"),
      },
    })
      .then((response) => {
        return response.json();
      })
      .then((res) => {
        setData(res);
        // console.log("리스트 불러오기 성공");
      })
      .catch((err) => {
        // console.log("리스트 불러오기 실패");
      })
      .finally(() => {
        setLoading(false);
      }, 1000);
  }, []);

  return (
    <div className="presentsearch">
      {localStorage.getItem("authorization") ? <h2>최근 검색 주차장</h2> : " "}
      {loading && <Loading />}
      <div>
        {data &&
          data.map((data) => (
            <Presentsearchlist key={data.parkingId} data={data} />
          ))}
      </div>
    </div>
  );
};

export default PresentSearch;
