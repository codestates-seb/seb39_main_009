import axios from "axios";
import { useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";


function HealthCheck() {
  const navigate = useNavigate();
  const [test, setTest] = useState("");
  const {id} = useParams()


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

        <button onClick={(localStorage.getItem("authorization"))?
        () => navigate("/mypage/{id}"):
        () => navigate("/join")}>마이페이지</button>

        <button onClick={handlelogOut}>로그아웃</button>
        <button onClick={()=>navigate(`/parking/{pkId}/review`)}>리뷰페이지</button>
        <button onClick={()=>navigate(`/parking/{pkId}/review/write`)}>리뷰작성페이지</button>
        {/* 이거 왜 안되지? */}
        <Link to = {`/mypage/${id}/edit`}><button>개인정보수정/안나님 이거 안돼요ㅜ</button></Link> 
        


      </div>
      <br />
      <div>
        <br />
        페이지 체크용 연결 버튼 (안나)
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
        <button onClick={() => navigate(`/notice`)}>공지사항</button>
        <button onClick={() => navigate(`/bookmark`)}>즐겨찾기</button>
        <button onClick={() => navigate(`/reservation`)}>예약목록</button>
        <button onClick={() => navigate(`/reservation/id`)}>
          예약상세조회
        </button>
        <button onClick={() => navigate(`/parking/1`)}>
          주차장정보(지도구현)
        </button>
      </div>
      <div>
        <br />( p.s 효영님! 화면 점검용으로 만들어 놓은 임의페이지 & 버튼입니다.
        수정하여 사용하셔도 됩니다 ! )
      </div>
    </div>
  );
}

export default HealthCheck;
