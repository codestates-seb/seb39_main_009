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
        <button onClick={() => navigate(`/agmtconf/service`)}>
          서비스이용약관
        </button>
        <button onClick={() => navigate(`/agmtconf/psinfo`)}>
          개인정보처리방침
        </button>
        <button onClick={() => navigate(`/agmtconf/event`)}>
          마케팅정보수신동의
        </button>
      </div>
      <br />
      <div>
        효영님 ! 페이지 체크를 위해 만들어둔 임시 페이지입니다.
        삭제 수정하여 사용하셔도 됩니당 :)
      </div>
    </div>
  );
}

export default HealthCheck;
