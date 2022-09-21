import React, { useState, useEffect } from "react";
import { FaAngleRight } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function JoinPresenter(props) {
  const navigate = useNavigate();

  const [allCheck, setAllCheck] = useState(false);
  const [ageCheck, setAgeCheck] = useState(false);
  const [useCheck, setUseCheck] = useState(false);
  const [marketingCheck, setMarketingCheck] = useState(false);

  const name = props.usernameinput;
  const email = props.emailinput;
  const password = props.checkpassword;
  const carNumber = props.carnumber;
  const phoneNum = props.phonenumber;
 

  const [massage,setMassage] = useState("");

  const url = ""

  const registeraxios = (event) => {
    event.preventDefault();
    axios
      .post(url+"/api/join", {
        name: name,
        email: email,
        password: password,
        carNumber : carNumber,
        phoneNum : phoneNum,
        // 비밀번호 재확인이 안들어가 있음 
        svcUseAgmt : ageCheck,
        psInfoAgmt: useCheck,
        eventAgmt: marketingCheck ,
        
    },{withCredentials: true} )
      .then((response) => {
        console.log(response);
        alert("회원가입성공");
        if(response.status ===201){
            return navigate('/welcome')
        }
    
      }).catch((err)=>{
        console.log(err);
        setMassage(err.response.data.massage);
      })
      ;
  };


  const allBtnEvent = () => {
    if (allCheck === false) {
      setAllCheck(true);
      setAgeCheck(true);
      setUseCheck(true);
      setMarketingCheck(true);
    } else {
      setAllCheck(false);
      setAgeCheck(false);
      setUseCheck(false);
      setMarketingCheck(false);
    }
  };

  const ageBtnEvent = () => {
    if (ageCheck === false) {
      setAgeCheck(true);
    } else {
      setAgeCheck(false);
    }
  };

  const useBtnEvent = () => {
    if (useCheck === false) {
      setUseCheck(true);
    } else {
      setUseCheck(false);
    }
  };

  const marketingBtnEvent = () => {
    if (marketingCheck === false) {
      setMarketingCheck(true);
    } else {
      setMarketingCheck(false);
    }
  };



// 새로고침시에도 자료 유지 
//   let [name, setName] = useState();
// const inputValue = navigate.location.state;

//   localStorage.setItem(name, JSON.stringify(inputValue));

//   useEffect(() => {
//     const saved = localStorage.getItem("id");
//     if (saved !== null) {
//       setName(saved);
//     }
//   }, [inputValue]);



  useEffect(() => {
    if (ageCheck === true && useCheck === true && marketingCheck === true) {
      setAllCheck(true);
    } else {
      setAllCheck(false);
    }
  }, [ageCheck, useCheck, marketingCheck]);

  return (
    <form method="post" action="">
      <div>
        <label>가입약관을 읽고 동의를 해주세요.</label>
        <div>
          <div>
            <input
              type="checkbox"
              id="all-check"
              checked={allCheck}
              onChange={allBtnEvent}
            />
            <label htmlFor="all-check">전체동의하기</label>
          </div>
          <div>
            <input
              type="checkbox"
              id="check1"
              checked={ageCheck}
              onChange={ageBtnEvent}
            />
            <label htmlFor="check1">서비스 이용약관 (필수)</label>
            <FaAngleRight onClick={() => navigate("/agmtconf/service")} />
          </div>
          <div>
            <input
              type="checkbox"
              id="check2"
              checked={useCheck}
              onChange={useBtnEvent}
            />
            <label htmlFor="check2">개인정보 처리방침 (필수)</label>
            <FaAngleRight onClick={() => navigate("/agmtconf/psinfo")} />
          </div>
          <div>
            <input
              type="checkbox"
              id="check3"
              checked={marketingCheck}
              onChange={marketingBtnEvent}
            />
            <label htmlFor="check3">이벤트/마케팅 수신동의 (선택)</label>
            <FaAngleRight onClick={() => navigate("/agmtconf/event")} />
          </div>
          <p>{massage}</p>
          <button onClick={registeraxios}>
            회원가입
          </button>
        </div>
      </div>
    </form>
  );
}

export default JoinPresenter;
