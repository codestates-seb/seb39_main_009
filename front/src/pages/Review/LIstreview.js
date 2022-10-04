//react-icons
import { FaStar } from "react-icons/fa";

import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserIdContext } from "../../context/UserIdContext";
import RandomImg2 from "../../assets/profileimg/RandomImg2";

const Listreview = ({ pkId, reviews, handleDelReview }) => {
  const navigate = useNavigate();
  const { userId } = useContext(UserIdContext);

  return (
    <div>
      <div key={reviews.reviewId}>
        <RandomImg2 size={"reviewImg_size"} />
        <div>닉네임 : {reviews.nickName}</div>
        <FaStar size={15} />
        <div>{reviews.star}</div>
        <div>{reviews.body}</div>
        {userId === reviews.memberId ? (
          <>
            <button
              onClick={() =>
                navigate(`/parking/${pkId}/review/${reviews.reviewId}`)
              }
            >
              수정
            </button>
            <button onClick={handleDelReview}>삭제</button>
          </>
        ) : null}
      </div>
    </div>
  );
};

export default Listreview;
