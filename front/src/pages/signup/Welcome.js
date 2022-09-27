import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import { GrClose } from "react-icons/gr";


const Welcome = () => {
  const navigate = useNavigate();

  return (
    <div>
        <div className="signup_header">
          <p>로그인</p>
          <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
       </div>
      <AiOutlineLeft
        size={24}
        onClick={() => {
          navigate(-1);
        }}
      />
      <div>
        <p>환영합니다!</p>
        <p>파킹Go에서 다양한 주차장을 할인 가격에 이용해보세요!</p>
        <button
          onClick={() => {
            navigate("/login");
          }}
        >
          로그인
        </button>
      </div>
    </div>
  );
};

export default Welcome;