// react-icons
import { GrClose } from "react-icons/gr";
// import { AiOutlineLeft } from "react-icons/ai";

import axios from "../../apis/axios";
import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import useRefreshToken from "../../hooks/useRefreshToken";
import { useContext } from "react";
import { AuthContext } from "../../context/AuthContext";
import { UserIdContext } from "../../context/UserIdContext";
import "./Login.css"

const LOGIN_URL = '/login';

const Login = () => {
  const onSilentRefresh = useRefreshToken();
  const navigate = useNavigate();

  const { setAuth } = useContext(AuthContext);
  const { setUserId } = useContext(UserIdContext);
  const userRef = useRef();
  const errRef = useRef();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errMsg, setErrMsg] = useState("");

  useEffect(() => {
    userRef.current.focus();
  }, []);

  useEffect(() => {
    setErrMsg("");
  }, [email, password]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        LOGIN_URL,
        JSON.stringify({ email, password }),
        {
          headers: { "Content-Type": "application/json" },
          withCredentials: true,
        }
      );
      const authorization = response?.headers?.authorization;
      const auth = response?.headers?.authorization;
      const refreshtoken = response?.headers?.refreshtoken;
      const memberId = response.data.memberId;
      localStorage.setItem("authorization", authorization);
      localStorage.setItem("refreshtoken", refreshtoken);
      localStorage.setItem("userId", memberId);
      setAuth(auth);
      setUserId(memberId);
      setEmail("");
      setPassword("");
      setTimeout(onSilentRefresh, 360000); // 1시간
      navigate("/");
    } catch (err) {
      setErrMsg(err.response.data.message);
      errRef.current.focus();
      alert(err.response.data.message)
    }
  };

  return (
    <div>
      <div>
        <div className="signup_header">
          <h2>로그인</h2>
          <GrClose
            className="closebtn"
            size={22}
            onClick={() => navigate(`/`)}
          />
        </div>
        <div className="Login">
          {/* <AiOutlineLeft
            size={24}
            onClick={() => {
              navigate(-1);
            }}
          /> */}
          <img className="stacklogo" alt="logo" src={process.env.PUBLIC_URL + "/juagologo.png"} />
            <p>로그인 후 이용 가능합니다</p>
          <form onSubmit={handleSubmit}>


            <input
              type="type"
              id="email"
              ref={userRef}
              autoComplete="off"
              placeholder="이메일"
              onChange={(e) => setEmail(e.target.value)}
              value={email}
              required
              autocomplete="new-password"
            />
            <input
              type="password"
              id="password"
              placeholder="비밀번호"
              onChange={(e) => setPassword(e.target.value)}
              value={password}
              required
              autocomplete="off"
            />
          <p
            ref={errRef}
            className={errMsg ? "errmsg" : "offscreen"}
            aria-live="assertive"
           >
          </p>
            <button className="buttonlogin">로그인</button>
        <button className="buttonlogin2" onClick={() => navigate("/join")}>회원가입</button>

          </form>
        </div>
      </div>
    </div>
  );
};

export default Login;
