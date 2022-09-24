import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";

const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const onSubmit = (event) => {
    event.preventDefault();
    axios
      .post("/api/login", {
        email: email,
        password: password,
      })
      .then((res) => {
        console.log(res);
        const token1 = localStorage.setItem(
          "authorization",
          res.headers.authorization
        );
        const token2 = localStorage.setItem(
          "refreshtoken",
          res.headers.refreshtoken
        );
        console.log("로그인 성공");
        console.log(token1, token2);
        setTimeout(OnSilentRefresh, 20000);
      })
      .catch((Error) => {
        console.log(Error);
        console.log("일치하는 회원 정보가 없습니다.");
      });
  };

  // refreshtoken으로 accesstoken(authorization)받아오는 함수
  const OnSilentRefresh = () => {
    axios
      .post("/api/oauth/token", {
        refreshtoken: localStorage.getItem("refreshtoken"),
      })
      .then((response) => {
        console.log(response);
        const token3 = localStorage.setItem(
          "authorization",
          response.headers.authorization
        );
        console.log("연장됨");
        console.log(token3);
      })
      .catch((error) => {
        console.log(error);
        console.log("연장실패");
        // 로컬스토리지에 토큰이 없으면 다른 페이지로 이동하는 조건문
        if (!localStorage.getItem("authorization")) {
          return navigate(`/join`);
        }
        //음?? 이거 되네? warning이 나오긴 하지만 브라우저상에서 잘 돌아감
      });
  };

  // 새로고침시에도 OnSilentRefresh함수가 작동할 수 있게 만드는 것
  useEffect(() => {
    // 로컬스토리지에 토큰이 없으면 OnSilentRefresh함수를 계속 실행시키던
    // setInterva함수를 멈추게하려고한 나의 로직 -> 하지만 안됨(함수가 계속 반복됨)

    const stoplogin = setInterval(OnSilentRefresh, 20000);
    if (!localStorage.authorization) return;
    return clearInterval(stoplogin);
  }, []);

  return (
    <div>
      <div className="Login">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <div className="inputemail">
          <label>Email</label>
          <br />
          <input
            type="text"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
            placeholder="email..."
          />
        </div>
        <div className="inputpassword">
          <label>password</label>
          <br />
          <input
            type="password"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
            placeholder="password..."
          />
        </div>
        <button className="buttonlogin" onClick={onSubmit}>
          Login
        </button>
      </div>
      <button onClick={() => navigate("/join")}>회원가입하기</button>
    </div>
  );
};

export default Login;
