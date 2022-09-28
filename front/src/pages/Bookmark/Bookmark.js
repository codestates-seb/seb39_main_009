// react-icons
import { GrClose } from "react-icons/gr";
import { FaTrashAlt } from "react-icons/fa";

import "./Bookmark.css";
import { axiosPrivate } from "../../apis/axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import RandomImg from "../../assets/parkinglot/RandomImg";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const Bookmark = () => {
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [del, setDel] = useState("");

  const getBookmark = () => {
    axiosPrivate
      .get(`/bookmark`)
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
      });
  };

  useEffect(() => {
    getBookmark();
  }, [del]);

  const handleOffBookmark = (pkId) => {
    axiosPrivate
      .delete(`/bookmark/${pkId}`)
      .then(() => setDel(pkId))
      .catch((err) => {
        setError(err);
      });
  };

  return (
    <>
      {loading && <Loading />}
      {error && <Error />}
      <div className="bookmark_container">
        <div className="bookmark_header">
          <h2>즐겨찾기</h2>
          <GrClose
            className="closebtn"
            size={22}
            onClick={() => navigate(`/`)}
          />
        </div>
        <div className="bookmark_main">
          {data &&
            data.map((bookmark) => (
              <div className="bookmark" key={bookmark.parkingId}>
                <RandomImg size={"bookmark_size"} />
                <div>
                  <p onClick={() => navigate(`/parking/${bookmark.parkingId}`)}>
                    {bookmark.name}
                  </p>
                  <p>{bookmark.address}</p>
                </div>
                <FaTrashAlt
                  className="delbtn"
                  size={19}
                  onClick={() => handleOffBookmark(bookmark.parkingId)}
                />
              </div>
            ))}
        </div>
      </div>
    </>
  );
};

export default Bookmark;
