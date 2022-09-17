import React, { useState } from "react";
import axios from "axios";
import JoinPresenter from "./JoinPresenter";

const SignUp = () => {
  const [usernameinput, setUsernameinput] = useState("");
  const [emailinput, setEmailinput] = useState("");
  const [checkpassword, setCheckpassword] = useState("");
  const [phonenumber, setPhonenumber] = useState("");
  const [carnumber, setCarnumber] = useState("");




  const registeraxios = () => {
    axios
      .post("/api/join", {
        name: usernameinput,
        email: emailinput,
        password: checkpassword,
        carNumber : carnumber,
        phoneNum : phonenumber,
        svcUseAgmt : true,
        psInfoAgmt: true,
        eventAgmt: true,
        
    },{withCredentials: true} )
      .then((response) => {
        console.log(response);
        alert("회원가입성공");
      }).catch((err)=>{
        console.log(err);
      })
      ;
  };

  return (
    <div className="signup">
      <div className="signup_input">
      <label>Name</label>
      <br/>
      <input
        type="text"
        placeholder="username..."
        onChange={(e) => {
          setUsernameinput(e.target.value);
        }}
      />
      </div>
      <div className="signup_input">
      <label>Email</label>
      <br/>
      <input
        type="text"
        placeholder="email..."
        onChange={(e) => {
          setEmailinput(e.target.value);
        }}
      />
      </div>
      <div className="signup_input">
      <label>비밀번호</label>
      <br/>
      <input
        type="password"
        placeholder="password..."
      />
      </div>
      <div className="signup_input">
      <label>비밀번호 재확인</label>
      <br/>
      <input
        type="password"
        placeholder="password..."
        onChange={(e) => {
          setCheckpassword(e.target.value);
        }}
      />
      </div>
      <div className="signup_input">
      <label>휴대폰번호</label>
      <br/>
      <input
        type="text"
        placeholder="phonenumber..."
        onChange={(e) => {
            setPhonenumber(e.target.value);
        }}
      />
      </div>
      <div className="signup_input">
      <label>차량번호</label>
      <br/>
      <input
        type="text"
        placeholder="carnumber..."
        onChange={(e) => {
            setCarnumber(e.target.value);
        }}
      />
      </div>
      <br />
      <JoinPresenter/>
      <br />
      <button onClick={registeraxios}>회원가입</button>
    </div>
  );
};

export default SignUp;