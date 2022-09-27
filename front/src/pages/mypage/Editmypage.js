// import axios from "axios";
import { useNavigate} from "react-router-dom";
import { useState } from "react";
import { useLocation } from "react-router-dom";
import { GrClose } from "react-icons/gr";


const Editmypage = () => {

  const navigate = useNavigate();

  const location = useLocation();
  const data = location.state.data;


  const [pwdInfo, setPwdInfo] = useState("");
  const [passwordRe, setPasswordRe] = useState("");
  const [phoneNum,setPhoneNum] = useState(data.phoneNum);
  const [carNumber,setCarNumber] = useState(data.carNumber);



const clickInfo = ()=>{
  fetch(`${process.env.REACT_APP_BASE_URL}/api/member`, {
    method: 'PATCH',
    headers: {
      'Content-Type': 'application/json',
        authorization: localStorage.getItem("authorization"),

    },
    body: JSON.stringify({
      password: pwdInfo,
      phoneNum: phoneNum,
      carNumber: carNumber
    })
  })
    .then((json) => {
    console.log('수정됨')
    console.log(json)})
    .catch((err)=> {
      console.log(err)
      console.log('수정안됨')
    })
}


  const isMatch = (e) => {
    if (pwdInfo !== passwordRe) {
      setPasswordRe("");
      alert(
        "비밀번호와 비밀번호 확인이 일치하지 않습니다. \n다시 입력해주세요."
      );
    }
  };

    return (
    <div>
        <div className="signup_header">
          <p>개인정보수정</p>
          <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
        </div>
        <div>
            <label>가입정보</label>
            <p>{data.email}</p>
        </div>
        <div>
            <label>비밀번호</label>
            <input 
            type="password"
            placeholder="password..."
            onChange={(e) => {
                setPwdInfo(e.target.value);
              }}/>
        </div>
        <div>
            <label>비밀번호확인</label>
            <input 
            type="password"
            placeholder="checkpassword..."
            onChange={(e) => {
                setPasswordRe(e.target.value);
              }}
              onBlur={() => {
                isMatch(passwordRe);
              }}/>
        </div>
        <div>
            <label>휴대폰 번호</label>
            <input 
            type="txt"
            value = {phoneNum}
            onChange={(e) => {
                setPhoneNum(e.target.value);
              }}/>
        </div>
        <div>
            <label>차량번호</label>
            <input 
            type="txt"
            value ={carNumber}
            onChange={(e) => {
                setCarNumber(e.target.value);
              }}/>
        </div>
        <button
        onClick ={clickInfo}
        >수정</button>
    </div>
)}

export default Editmypage;