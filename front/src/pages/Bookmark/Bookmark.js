// react-icons
import { GrClose } from "react-icons/gr";
import { FaTrashAlt } from "react-icons/fa";

import "./Bookmark.css";
import axios from "../../apis/axios";
import React, { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import RandomImg from "../../assets/parkinglot/RandomImg";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const Bookmark = () => {
  const { auth } = useContext(AuthContext);
  const navigate = useNavigate();
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [del, setDel] = useState("");

  useEffect(() => {
    axios
      .get(
        `/bookmark`,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((res) => {
        setData(res.data);
        setLoading(false);
      })
      .catch((err) => {
        setError(err);
      });
  }, [auth, del]);

  const handleOffBookmark = (pkId) => {
    axios
      .delete(
        `/bookmark/${pkId}`,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
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
            onClick={() => navigate(`/find`)}
          />
        </div>
        <div className="bookmark_main">
          {data.length === 0 ? (
            <div>
              <p>즐겨찾기가 없습니다.</p>
            </div>
          ) : (
            <>
              {data &&
                data.map((bookmark) => (
                  <div className="bookmark" key={bookmark.parkingId}>
                    <RandomImg size={"bookmark_size"} />
                    <div>
                      <p
                        onClick={() =>
                          navigate(`/parking/bk/${bookmark.parkingId}`)
                        }
                      >
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
            </>
          )}
        </div>
      </div>
    </>
  );
};

export default Bookmark;
