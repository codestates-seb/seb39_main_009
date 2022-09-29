// react-icons
import { GrClose } from "react-icons/gr";

import "./Signup.css";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import JoinPresenter from "./JoinPresenter";

const SignUp = () => {
  const navigate = useNavigate();

  //이름, 이메일, 비밀번호, 비밀번호 확인, 핸드폰 번호, 차번호
  const [usernameinput, setUsernameinput] = useState("");
  const [emailinput, setEmailinput] = useState("");
  const [passwordinput, setPasswordinput] = useState("");
  const [checkpassword, setCheckpassword] = useState("");
  const [phonenumber, setPhonenumber] = useState("");
  const [carnumber, setCarnumber] = useState("");


  //오류메시지 상태저장
  const [nameMessage, setNameMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const [passwordConfirmMessage, setPasswordConfirmMessage] = useState("");
  const [phonenumberMessage, setPhonenumberMessage] = useState("");
  const [carnumberMessage, setCarnumberMessage] = useState("");


 // 유효성 검사
 const [isName, setIsName] = useState(false)
 const [isPassword, setIsPassword] = useState(false)
 const [isPasswordConfirm, setIsPasswordConfirm] = useState(false)
 const [isphonenumber, setIsPhonenumber] = useState(false);
 const [iscarnumber, setIsCarnumber] = useState(false);
 
   // 이름
const onChangeName = (e) => {
  if (usernameinput.length < 2 || usernameinput.length > 5) {
    setNameMessage('2글자 이상 5글자 이하로 입력해주세요.')
    setIsName(false)
  } else {
    setNameMessage('올바른 이름 형식입니다 :)')
    setIsName(true)
  }
}


  // 비밀번호
  const onChangePassword = (e) => {
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    setPasswordinput(passwordinput);

    if (!passwordRegex.test(passwordinput)) {
      setPasswordMessage("숫자+영문자+특수문자 조합으로 8자리 이상");
      setIsPassword(false);
    } else {
      setPasswordMessage("안전한 비밀번호에요 : )");
      setIsPassword(true);
    }
  };
  // 비밀번호 확인
  const onChangePasswordConfirm = (e) => {
    if (passwordinput === checkpassword) {
      setPasswordConfirmMessage("비밀번호를 똑같이 입력했어요 : )");
      setIsPasswordConfirm(true);
    } else {
      setPasswordConfirmMessage("비밀번호가 틀려요. 다시 확인해주세요.");
      setIsPasswordConfirm(false);
    }
  };

  //휴대폰 번호

  const onChangephonenumber = (e) => {
    const regex = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/;
    if (regex.test(phonenumber)) {
      setIsPhonenumber(true);
      setPhonenumberMessage("올바른 전화번호입니다.");
    } else setPhonenumberMessage("올바른 전화번호 형식이 아닙니다.");
    setIsPhonenumber(false);
  };


  const onChangecarnumber =(e)=>{
    const pattern1 = /\d{2,3}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 12저1234
    const pattern2 = /[가-힣ㄱ-ㅎㅏ-ㅣ\x20]{2}\d{2}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 서울12치1233
    if (!pattern1.test(carnumber)) {
      if (!pattern2.test(carnumber)) {
        setCarnumberMessage("올바른 차량번호 형식이 아닙니다.");
        setIsCarnumber(false);
      } else {
        setCarnumberMessage("올바른 차량번호입니다.");
        setIsCarnumber(true);
      }
    } else {
      setCarnumberMessage("올바른 차량번호입니다.");
      setIsCarnumber(true);
    }
  };

  return (
    <div className="signup_container">
      <div className="signup_header">
        <h2>회원가입</h2>
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
            required
            onChange={(e) => {
              setUsernameinput(e.target.value);
            }}
            onBlur={() => {
              onChangeName(usernameinput);
            }}
            required
          />
          {usernameinput.length > 0 && (
            <span className={`message ${isName ? "success" : "error"}`}>
              {nameMessage}
            </span>
          )}
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
            required
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
            onBlur={() => {
              onChangePassword(passwordinput);
            }}
            autoComplete="new-password" // 구글 자동완성 막기.
            required
          />
          {passwordinput.length > 0 && (
            <span className={`message ${isPassword ? "success" : "error"}`}>
              {passwordMessage}
            </span>
          )}
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
              onChangePasswordConfirm(checkpassword);
            }}
            required
          />
          {checkpassword.length > 0 && (
            <span
              className={`message ${isPasswordConfirm ? "success" : "error"}`}
            >
              {passwordConfirmMessage}
            </span>
          )}
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
            onBlur={() => {
              onChangephonenumber(phonenumber);
            }}
            required
          />
          {phonenumber.length > 0 && (
            <span className={`message ${isphonenumber ? "success" : "error"}`}>
              {phonenumberMessage}
            </span>
          )}
        </div>
        <div className="signup_input">
          <p>차량번호</p>
          <input
            type="text"
            placeholder="예: 123주4567"
            onChange={(e) => {
              setCarnumber(e.target.value);
            }}
            onBlur={() => {
              onChangecarnumber(carnumber);
            }}
          />
          {carnumber.length > 0 && (
            <span className={`message ${iscarnumber ? "success" : "error"}`}>
              {carnumberMessage}
            </span>
          )}
        </div>
        <JoinPresenter
          usernameinput={usernameinput}
          emailinput={emailinput}
          passwordinput={passwordinput}
          checkpassword={checkpassword}
          phonenumber={phonenumber}
          carnumber={carnumber}
          isName={isName}
          isPassword={isPassword}
          isPasswordConfirm={isPasswordConfirm}
        />
      </div>
    </div>
  );
};

export default SignUp;
