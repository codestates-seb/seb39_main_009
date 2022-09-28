import axios from "../../apis/axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import { useEffect, useRef } from "react";
import { GrClose } from "react-icons/gr";
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
  const [success, setSuccess] = useState(false);

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
      console.log(JSON.stringify(response?.headers));
      const authorization = response?.headers?.authorization;
      const refreshtoken = response?.headers?.refreshtoken;
      localStorage.setItem("authorization", authorization);
      localStorage.setItem("refreshtoken", refreshtoken);
      setEmail("");
      setPassword("");
      setSuccess(true);
      setTimeout(onSilentRefresh, 360000); // 1시간
      navigate("/");
      window.location.reload();
    } catch (err) {
      console.log(err?.response.data.message);
      setErrMsg(err?.response.data.message);
      errRef.current.focus();
    }
  };

  return (
    <div>
      {success ? (
        <div>
          <h1>You are logged in!</h1>
          <button onClick={() => navigate(`/users`)}>이동</button>
        </div>
      ) : (
        <div>
          <p
            ref={errRef}
            className={errMsg ? "errmsg" : "offscreen"}
            aria-live="assertive"
          >
            {errMsg}
          </p>
          <div className="signup_header">
            <p>로그인</p>
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
      )}
    </div>
  );
};

export default Login;
