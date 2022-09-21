import React, { useState, useEffect } from "react";
import { FaAngleRight } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

function JoinPresenter(props) {
  const navigate = useNavigate();

  const [allCheck, setAllCheck] = useState(false);
  const [ageCheck, setAgeCheck] = useState(false);
  const [useCheck, setUseCheck] = useState(false);
  const [marketingCheck, setMarketingCheck] = useState(false);
 

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


  const handleClick = () => {
    navigate('/join',{
      stats : 
      {
        ageCheck, 
        useCheck, 
        marketingCheck}})};
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
              onChange={()=>props.changeSearch(ageBtnEvent)}
            />
            <label htmlFor="check1">서비스 이용약관 (필수)</label>
            <FaAngleRight onClick={() => navigate("/agmtconf/service")} />
          </div>
          <div>
            <input
              type="checkbox"
              id="check2"
              checked={useCheck}
              onChange={()=>props.changeSearch2(useBtnEvent)}
            />
            <label htmlFor="check2">개인정보 처리방침 (필수)</label>
            <FaAngleRight onClick={() => navigate("/agmtconf/psinfo")} />
          </div>
          <div>
            <input
              type="checkbox"
              id="check3"
              checked={marketingCheck}
              onChange={()=>props.changeSearch3(marketingBtnEvent)}
            />
            <label htmlFor="check3">이벤트/마케팅 수신동의 (선택)</label>
            <FaAngleRight onClick={() => navigate("/agmtconf/event")} />
          </div>
          <button onClick={handleClick}>
            다음으로 넘어가기
          </button>
        </div>
      </div>
    </form>
  );
}

export default JoinPresenter;
