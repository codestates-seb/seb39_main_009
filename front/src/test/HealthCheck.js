import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function HealthCheck() {
  const navigate = useNavigate();
  const [test, setTest] = useState("");

  const inputData = { test: test };

  const handlePostData = () => {
    axios
      .post(`${process.env.REACT_APP_BASE_URL}/api/test`, inputData)
      .then((res) => {
        console.log(res);

        alert(`post 성공!`);
      })
      .catch((err) => {
        console.log(err);
      });
    setTest("");
  };

  const handlelogOut = () => {
    localStorage.removeItem("authorization");
    localStorage.removeItem("refreshtoken");
    axios.defaults.headers.common[`Authorization`] =
      localStorage.getItem("authorization");
    alert("로그아웃");
  };

  return (
    <div>
      <form onSubmit={(e) => e.preventDefault()}>
        <label>서버 건강 검진</label>{" "}
        <input
          type="text"
          value={test}
          placeholder="Health Check!"
          onChange={(e) => setTest(e.target.value)}
          autoFocus
          required
        />{" "}
        <button onClick={handlePostData}>POST</button>
      </form>
      <div>
        <br />
        페이지 체크용 연결 버튼 (효영)
        <br />
        <button onClick={() => navigate("/join")}>회원가입</button>
        <button onClick={() => navigate("/login")}>로그인</button>
        <button onClick={() => navigate("/welcome")}>환영창</button>
        {/* <button onClick={() => navigate("/mypage/{id}")}>마이페이지</button> */}
        <button
          onClick={
            localStorage.getItem("authorization")
              ? () => navigate("/mypage/{id}")
              : () => navigate("/join")
          }
        >
          마이페이지
        </button>
        <button onClick={handlelogOut}>로그아웃</button>
        <button onClick={() => navigate(`/find`)}>주차장조건검색페이지</button>
        <button onClick={() => navigate(`/parking/{id}/sector`)}>주차장예약페이지</button>

        
      </div>
      <br />
      <div>
        <br />
        페이지 체크용 연결 버튼 (안나)
        <br />
        <button onClick={() => navigate(`/notice`)}>공지사항</button>
        <button onClick={() => navigate(`/bookmark`)}>즐겨찾기</button>
        <button onClick={() => navigate(`/reservation`)}>예약목록</button>
        <button onClick={() => navigate(`/parking/17993`)}>
          주차장정보(지도구현)
        </button>
        <button onClick={() => navigate(`/pay/reservId`)}>결제페이지</button>
      </div>
      <div>
        <br />( p.s 효영님! 화면 점검용으로 만들어 놓은 임의페이지 & 버튼입니다.
        수정하여 사용하셔도 됩니다 ! )
      </div>
    </div>
  );
}

export default HealthCheck;
