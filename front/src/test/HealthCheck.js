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
        페이지 체크용 연결 버튼
        <br />
        <button onClick={() => navigate(`/loading`)}>로딩컴포넌트</button>
        <button onClick={()=> navigate('/join')}>회원가입</button>
        <button onClick={()=> navigate('/login')}>로그인</button>
        <button onClick={()=> navigate('/welcome')}>환영창</button>
      </div>
      <div>
        <br />( p.s 효영님! 화면 점검용으로 만들어 놓은 임의페이지 & 버튼입니다.
        수정하여 사용하셔도 됩니다 ! )
      </div>
    </div>
  );
}

export default HealthCheck;
