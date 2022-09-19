import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "./Bookmark.css";
import RandomImg from "../../assets/parkinglot/RandomImg";
import Loading from "../../component/Loading/Loading";
import { GrClose } from "react-icons/gr";
import { FaTrashAlt } from "react-icons/fa";

const Bookmark = () => {
  const navigate = useNavigate();

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    setLoading(false);
  }, []);

  return (
    <div className="bookmark_container">
      {loading ? (
        <Loading />
      ) : (
        <>
          <div className="bookmark_header">
            <p>즐겨찾기</p>
            <GrClose
              className="closebtn"
              size={22}
              onClick={() => navigate(-1)}
            />
          </div>
          <div className="bookmark_main">
            {data &&
              data.map((bookmark) => (
                <div className="bookmark" key={bookmark.id}>
                  <RandomImg size={"bookmark_size"} />
                  <div>
                    <p onClick={() => navigate(`/parking/${bookmark.id}`)}>
                      {bookmark.name}
                    </p>
                    <p>{bookmark.address}</p>
                  </div>
                  <FaTrashAlt
                    className="delbtn"
                    size={19}
                    onClick={() => console.log(`delete`)}
                  />
                </div>
              ))}
          </div>
        </>
      )}
    </div>
  );
};

export default Bookmark;

const data = [
  {
    id: 1,
    name: "안나주차장",
    address: "서울시 강남구 압구정동 123-45 어쩌구빌딩 지하 1층",
  },
  {
    id: 2,
    name: "우종주차장",
    address: "서울시 강남구 논현동 678-9",
  },
  {
    id: 3,
    name: "효영주차장",
    address: "서울시 강남구 청담동 123-45",
  },
  {
    id: 4,
    name: "민준주차장",
    address: "서울시 강남구 삼성동 678-9",
  },
];
