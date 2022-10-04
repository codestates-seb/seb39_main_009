import axios from "axios";
import { useState, useContext } from "react";
import { UserIdContext } from "../../context/UserIdContext";
import RandomImg2 from "../../assets/profileimg/RandomImg2";
import { useNavigate, useParams } from "react-router-dom";

const Listreview = (props) => {
  const navigate = useNavigate();
  const { pkId } = useParams();
  const { userId } = useContext(UserIdContext);

  const [review, setReview] = useState(props.reviews);
  // const navigate = useNavigate();

  // 삭제버튼을 어떻게 할지 의문!
  const del = () => {
    if (window.confirm("삭제하시겠습니까?")) {
      axios.delete(`/api/reviews/${props.pkId}/${review.id}`).then((res) => {
        if (res.ok) {
          setReview({
            ...review,
            id: 0,
          });
        }
      });
    }
  };

  if (review.reviewId === 0) {
    return null;
  }

  return (
    <div>
      <div key={review.reviewId}>
        <RandomImg2 size={"reviewImg_size"} />
        <div>닉네임 : {review.nickName}</div>
        <div>{review.body}</div>
        {userId === review.memberId ? (
          <>
            <button onClick={() => navigate(`/parking/${pkId}/review/edit`)}>
              수정
            </button>
            <button onClick={del}>삭제</button>
          </>
        ) : null}
      </div>
    </div>
  );
};

export default Listreview;
