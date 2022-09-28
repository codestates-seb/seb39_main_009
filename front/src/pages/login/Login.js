// react-icons
import { GrClose } from "react-icons/gr";
import { AiOutlineLeft } from "react-icons/ai";

import axios from "../../apis/axios";
import { useState, useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import useRefreshToken from "../../hooks/useRefreshToken";

const LOGIN_URL = `/login`;

const Login = () => {
  const onSilentRefresh = useRefreshToken();
  const navigate = useNavigate();

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
      console.log(response);
      const authorization = response?.headers?.authorization;
      const refreshtoken = response?.headers?.refreshtoken;
      localStorage.setItem("authorization", authorization);
      localStorage.setItem("refreshtoken", refreshtoken);
      setEmail("");
      setPassword("");
      setTimeout(onSilentRefresh, 360000); // 1시간
      navigate("/");
      // window.location.reload();
    } catch (err) {
      setErrMsg(err?.response.data.message);
      errRef.current.focus();
    }
  };

  return (
    <div>
      <div>
        <p
          ref={errRef}
          className={errMsg ? "errmsg" : "offscreen"}
          aria-live="assertive"
        >
          {errMsg}
        </p>
        <div className="signup_header">
          <h2>로그인</h2>
          <GrClose
            className="closebtn"
            size={22}
            onClick={() => navigate(`/`)}
          />
        </div>
        <div className="Login">
          <AiOutlineLeft
            size={24}
            onClick={() => {
              navigate(-1);
            }}
          />
          <form onSubmit={handleSubmit}>
            <label htmlFor="email">이메일</label>
            <input
              type="type"
              id="email"
              ref={userRef}
              autoComplete="off"
              onChange={(e) => setEmail(e.target.value)}
              value={email}
              required
            />
            <label htmlFor="email">비밀번호</label>
            <input
              type="password"
              id="password"
              onChange={(e) => setPassword(e.target.value)}
              value={password}
              required
            />
            <button>Sign In</button>
          </form>
        </div>
        <button onClick={() => navigate("/join")}>회원가입하기</button>
      </div>
    </div>
  );
};

export default Login;
