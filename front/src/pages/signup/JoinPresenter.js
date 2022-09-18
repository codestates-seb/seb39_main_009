import React, {useState, useEffect}  from 'react';
import {FaAngleRight} from "react-icons/fa";


function JoinPresenter(){


  const [allCheck, setAllCheck] = useState(false);
  const [ageCheck, setAgeCheck] = useState(false);
  const [useCheck, setUseCheck] = useState(false);
  const [marketingCheck, setMarketingCheck] = useState(false);

  const allBtnEvent =()=>{
    if(allCheck === false) {
      setAllCheck(true);
      setAgeCheck(true);
      setUseCheck(true);
      setMarketingCheck(true);
    }else {
      setAllCheck(false);
      setAgeCheck(false);
      setUseCheck(false);
      setMarketingCheck(false);
    } 
  };
  
  const ageBtnEvent =()=>{
    if(ageCheck === false) {
      setAgeCheck(true)
    }else {
      setAgeCheck(false)
    }
  };
  
  const useBtnEvent =()=>{
    if(useCheck === false) {
      setUseCheck(true)
    }else {
      setUseCheck(false)
    }
  };
  
  const marketingBtnEvent =()=>{
    if(marketingCheck === false) {
      setMarketingCheck(true)
    }else {
      setMarketingCheck(false)
    }
  };

  useEffect(()=>{
    if(ageCheck===true && useCheck===true && marketingCheck===true){
      setAllCheck(true)
    } else {
      setAllCheck(false)
    }
  }, [ageCheck,useCheck, marketingCheck])


  return (
    <form method="post" action="" >
      <div >
        <label >
        	가입약관을 읽고 동의를 해주세요.
        </label>
        <div >
        	<div >
        		<input type="checkbox" id="all-check" checked={allCheck} onChange={allBtnEvent}/>
        		<label for="all-check">전체동의하기</label>
        	</div>
        	<div >
        		<input type="checkbox" id="check1" checked={ageCheck} onChange={ageBtnEvent}/>
        		<label for="check1">서비스 이용약관 (필수)</label>
                <FaAngleRight  />
        	</div>
        	<div >
        		<input type="checkbox" id="check2" checked={useCheck}  onChange={useBtnEvent}/>
        		<label for="check2">개인정보 처리방침 (필수)</label>
                <FaAngleRight />
        	</div>
        	<div >
        		<input type="checkbox" id="check3" checked={marketingCheck}  onChange={marketingBtnEvent}/>
        		<label for="check3">이벤트/마케팅 수신동의 (선택)</label>
                <FaAngleRight />
        	</div>
        </div>
      </div>
    </form>
  )
}

export default JoinPresenter;