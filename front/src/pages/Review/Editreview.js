//react-icons
import { GrClose } from "react-icons/gr";

import axios from "../../apis/axios";
import { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import useFetch from "./../../hooks/useFetch";
import { AuthContext } from "../../context/AuthContext";
import ReviewStar from "./ReviewStar";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const EditReview = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);
  const { pkId, reviewId } = useParams();
  const [star, setStar] = useState(null);
  const [body, setBody] = useState("");

  const { data, loading, error } = useFetch(`/reviews/${pkId}/${reviewId}`);

  useEffect(() => {
    setStar(data.star);
    setBody(data.body);
  }, [data]);

  const reviewEditData = { star, body };

  const onSubmit = (e) => {
    e.preventDefault();
    axios
      .patch(
        `/reviews/${pkId}`,
        reviewEditData,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((res) => {
        alert("리뷰가 수정되었습니다.");
        navigate(`/parking/${pkId}`);
      })
      .catch((err) => {
        alert(err.response.data.message);
        navigate(`/parking/${pkId}`);
      });
  };

  return (
    <>
      {loading && <Loading />}
      {error && <Error />}
      <div className="dvwr_container">
        <div className="dvwr_header">
          <p>리뷰작성페이지</p>
          <GrClose
            className="closebtn"
            size={22}
            onClick={() => navigate(-1)}
          />
        </div>
        <div className="dvwr_main">
          <form onSubmit={onSubmit}>
            <h2>상품은 만족하셨나요?</h2>
            <ReviewStar setStar={setStar} star={star} />
            <h2>어떤 점이 좋았나요?</h2>
            <textarea
              placeholder="최소 10자 이상은 입력해주세요."
              type="text"
              value={body === undefined ? "" : body}
              onChange={(e) => setBody(e.target.value)}
              required
            />
            <button>등록</button>
          </form>
        </div>
      </div>
    </>
  );
};

export default EditReview;
