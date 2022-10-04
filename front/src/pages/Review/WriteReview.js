//react-icons
import { GrClose } from "react-icons/gr";

import axios from "../../apis/axios";
import { useContext, useEffect, useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import ReviewStar from "./ReviewStar";

const WriteReview = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);
  const reviewRef = useRef(null);
  const { pkId } = useParams();
  const [rating, setRating] = useState(0);
  const [review, setReview] = useState("");

  const reviewData = {
    star: rating,
    body: review,
  };

  const onSubmit = (e) => {
    e.preventDefault();
    axios
      .post(
        `/reviews/${pkId}`,
        reviewData,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((res) => {
        alert("리뷰가 등록되었습니다.");
        navigate(`/parking/${pkId}/review`);
      })
      .catch((err) => {
        alert(err.response.data.message);
        navigate(`/parking/${pkId}/review`);
      });
  };

  useEffect(() => {
    reviewRef.current.focus();
  }, []);

  return (
    <>
      <form onSubmit={onSubmit}>
        <div className="signup_header">
          <p>리뷰작성페이지</p>
          <GrClose
            className="closebtn"
            size={22}
            onClick={() => navigate(-1)}
          />
        </div>
        <h2>상품은 만족하셨나요?</h2>
        <ReviewStar setRating={setRating} />
        <h2>어떤 점이 좋았나요?</h2>
        <textarea
          placeholder="최소 10자 이상은 입력해주세요."
          type="text"
          value={review}
          onChange={(e) => setReview(e.target.value)}
          ref={reviewRef}
          required
        />
        <button>등록</button>
      </form>
    </>
  );
};

export default WriteReview;
