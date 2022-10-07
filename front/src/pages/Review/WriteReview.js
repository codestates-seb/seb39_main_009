//react-icons
import { GrClose } from "react-icons/gr";

import axios from "../../apis/axios";
import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import ReviewStar from "./ReviewStar";

const WriteReview = () => {
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);
  const { pkId } = useParams();
  const [star, setStar] = useState(null);
  const [body, setBody] = useState("");

  const onSubmit = (e) => {
    e.preventDefault();
    axios
      .post(
        `/reviews/${pkId}`,
        { star, body },
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
        navigate(`/parking/${pkId}`);
      })
      .catch((err) => {
        alert(err.response.data.message);
        navigate(`/parking/${pkId}`);
      });
  };

  return (
    <div className="dvwr_container">
      <div className="dvwr_header">
        <p>리뷰작성페이지</p>
        <GrClose className="closebtn" size={22} onClick={() => navigate(-1)} />
      </div>
      <div className="dvwr_main">
        <form onSubmit={onSubmit}>
          <h2>상품은 만족하셨나요?</h2>
          <ReviewStar setStar={setStar} star={star} />
          <h2>어떤 점이 좋았나요?</h2>
          <textarea
            placeholder="최소 10자 이상 입력해주세요."
            type="text"
            value={body}
            onChange={(e) => setBody(e.target.value)}
            required
          />
          <button>등록</button>
        </form>
      </div>
    </div>
  );
};

export default WriteReview;
