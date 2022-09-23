import React, { useState } from "react";
import { AiOutlineLeft } from "react-icons/ai";
import { useNavigate} from "react-router-dom";
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
    <div className="signup">
      <div>
       <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
      <div className="signup_input">
      <label>Name</label>
      <br/>
      <input
        type="text"
        placeholder="username..."
        autocomplete="off"
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
        onChange={(e) => {
          setPasswordinput(e.target.value);
        }}
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
        onBlur={() => {
          isMatch(checkpassword);
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
      <JoinPresenter 
          usernameinput = {usernameinput}
          emailinput = {emailinput}
          passwordinput = {passwordinput}
          checkpassword = {checkpassword}
          phonenumber = {phonenumber}
          carnumber = {carnumber}

      />
      </div>
    </div>
  );
};

export default SignUp;