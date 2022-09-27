import axios from "axios";
import {  useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import {useEffect } from "react"
import { GrClose } from "react-icons/gr";


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
        localStorage.setItem(
          "authorization",
          res.headers.authorization
        );
         localStorage.setItem(
          "refreshtoken",
          res.headers.refreshtoken
        );
        console.log("로그인 성공");
        // window.location.reload();
        setTimeout(OnSilentRefresh, 600000);
        console.log(res.headers.authorization)

      })

      .catch((Error) => {
        console.log(Error);
        console.log("일치하는 회원 정보가 없습니다.");
      });
  };

  // refreshtoken으로 accesstoken(authorization)받아오는 함수
  const OnSilentRefresh = () => {
    axios
      .post( "/api/oauth/token", {
        refreshtoken: localStorage.getItem("refreshtoken"),
      })
      .then((response) => {
        console.log(response);
        localStorage.setItem(
          "authorization",
          response.headers.authorization
        );
        console.log("연장됨");
      })
      .catch((error) => {
        console.log(error);
        console.log("연장실패");

      });
  };


  // 새로고침시에도 OnSilentRefresh함수가 작동할 수 있게 만드는 것
  useEffect(() => {
         setInterval(OnSilentRefresh, 600000);
  },[]);





    return  (
      <div >
          <div className="signup_header">
          <p>로그인</p>
          <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
         </div>
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
