// react-icons
import { AiOutlineLeft } from "react-icons/ai";
// import { GrClose } from "react-icons/gr";

import { useNavigate } from "react-router-dom";
import "./Signup.css"

const Welcome = () => {
  const navigate = useNavigate();

  return (
    <div className="welcome_container">
      <div className="backheader">
        <AiOutlineLeft
          size={35}
          onClick={() => {
            navigate(-1);
          }}
        />
        <h2>환영페이지</h2>
      </div>

      <div className="welcome">
        <p>환영합니다!</p>
        <p>JUCHAGO에서 다양한 주차장을</p>
        <p> 할인 가격에 이용해보세요!</p>
        <button
          onClick={() => {
            navigate("/login");
          }}
          className="submit_bt"
        >
          로그인
        </button>
      </div>
    </div>
  );
};

export default Welcome;
