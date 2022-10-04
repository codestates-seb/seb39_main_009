import axios from "axios";
import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { GrClose } from "react-icons/gr";

const Editreview = () => {
  const navigate = useNavigate();

  const reviewRef = useRef(null);
  const { pkId } = useParams();

  const onSubmit = (e) => {
    e.preventDefault();

    axios
      .post(
        `/api/reviews/${pkId}`,
        {
          body: reviewRef.current.value,
        },
        {
          withCredentials: true,
        }
      )
      .then((res) => {
        console.log("리뷰등록");
        navigate(`/parking/${pkId}/review`);
        console.log(res);
      })
      .catch((err) => {
        console.log("등록실패");
        console.log(err);
      });
  };

  return (
    <form onSubmit={onSubmit}>
      <div className="signup_header">
        <p>리뷰작성페이지</p>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <h2>상품은 만족하셨나요?</h2>
      <div>별점표시</div>
      <h2>어떤 점이 좋았나요?</h2>
      <textarea
        placeholder="최소 10자 이상은 입력해주세요."
        type="text"
        ref={reviewRef}
      />
      <button>등록</button>
    </form>
  );
};

export default Editreview;
