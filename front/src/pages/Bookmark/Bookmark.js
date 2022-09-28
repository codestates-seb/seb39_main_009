import React from "react";
import { useNavigate } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import "./Bookmark.css";
import RandomImg from "../../assets/parkinglot/RandomImg";
import { GrClose } from "react-icons/gr";
import { FaTrashAlt } from "react-icons/fa";
import Loading from "../../component/Loading/Loading";

const Bookmark = () => {
  const navigate = useNavigate();

  const { data, loading } = useFetch(`/bookmark`);
  console.log(data);

  return (
    <>
      {loading ? (
        <Loading />
      ) : (
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
        </div>
      )}
    </>
  );
};

export default Bookmark;
