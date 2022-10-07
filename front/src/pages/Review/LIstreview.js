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
    <div className="rvlist_container">
      <div className="single_rv" key={reviews.reviewId}>
        <div className="rvlist_user">
          <RandomImg2 size={"reviewer_size"} />
          <div>
            <p>{reviews.nickName}</p>
            <div>
              <FaStar size={15} />
              <p>{reviews.star}</p>
            </div>
          </div>
        </div>
        <div className="rvlist_text">
          <p>{reviews.body}</p>
        </div>

        {userId === reviews.memberId ? (
          <div className="rvlist_btns">
            <button
              onClick={() =>
                navigate(`/parking/${pkId}/review/${reviews.reviewId}`)
              }
            >
              수정
            </button>
            <button onClick={handleDelReview}>삭제</button>
          </div>
        ) : null}
      </div>
    </div>
  );
};

export default Listreview;
