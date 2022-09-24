import React, { useState } from "react";
import "./Signup.css";
import { GrClose } from "react-icons/gr";
import { useNavigate } from "react-router-dom";
import JoinPresenter from "./JoinPresenter";

const SignUp = () => {
  const navigate = useNavigate();

  const [usernameinput, setUsernameinput] = useState("");
  const [emailinput, setEmailinput] = useState("");
  const [passwordinput, setPasswordinput] = useState("");
  const [checkpassword, setCheckpassword] = useState("");
  const [phonenumber, setPhonenumber] = useState("");
  const [carnumber, setCarnumber] = useState("");

  const isMatch = (e) => {
    if (passwordinput !== checkpassword) {
      setCheckpassword("");
      alert(
        "비밀번호와 비밀번호 확인이 일치하지 않습니다. \n다시 입력해주세요."
      );
    }
  };

  return (
    <div className="signup_container">
      <div className="signup_header">
        <p>회원가입</p>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <div className="signup_main">
        <div className="signup_input">
          <div className="signup_label">
            <p>이 름</p>
            <p>*</p>
          </div>
          <input
            type="text"
            placeholder="이름을 입력해주세요."
            autoComplete="off"
            onChange={(e) => {
              setUsernameinput(e.target.value);
            }}
          />
        </div>
        <div className="signup_input">
          <div className="signup_label">
            <p>이메일</p>
            <p>*</p>
          </div>
          <input
            type="email"
            placeholder="예: user@juchago.com"
            onChange={(e) => {
              setEmailinput(e.target.value);
            }}
          />
        </div>
        <div className="signup_input">
          <div className="signup_label">
            <p>비밀번호</p>
            <p>*</p>
          </div>
          <input
            type="password"
            placeholder="비밀번호를 입력해주세요."
            onChange={(e) => {
              setPasswordinput(e.target.value);
            }}
          />
        </div>
        <div className="signup_input">
          <div className="signup_label">
            <p>비밀번호확인</p>
            <p>*</p>
          </div>
          <input
            type="password"
            placeholder="비밀번호를 한번 더 입력해주세요."
            onChange={(e) => {
              setCheckpassword(e.target.value);
            }}
            onBlur={() => {
              isMatch(checkpassword);
            }}
          />
        </div>
        <div className="signup_input">
          <div className="signup_label">
            <p>휴대폰</p>
            <p>*</p>
          </div>
          <input
            type="text"
            placeholder="숫자만 입력해주세요."
            onChange={(e) => {
              setPhonenumber(e.target.value);
            }}
          />
        </div>
        <div className="signup_input">
          <p>차량번호</p>
          <input
            type="text"
            placeholder="예: 123주4567"
            onChange={(e) => {
              setCarnumber(e.target.value);
            }}
          />
        </div>
        <JoinPresenter
          usernameinput={usernameinput}
          emailinput={emailinput}
          passwordinput={passwordinput}
          checkpassword={checkpassword}
          phonenumber={phonenumber}
          carnumber={carnumber}
        />
      </div>
    </div>
  );
};

export default SignUp;
