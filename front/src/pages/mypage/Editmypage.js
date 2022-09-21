import axios from "axios";
import { useNavigate,useLocation } from "react-router-dom";
import { useState } from "react";

const Editmypage = () => {

  const navigate = useNavigate();

  const myInfo = useLocation();

  let url = ""

  const [pwdInfo, setPwdInfo] = useState("");
  const [passwordRe, setPasswordRe] = useState("");
  const [phoneNum,setPhoneNum] = useState(myInfo.phoneNum);
  const [carNumber,setCarNumber] = useState(myInfo.carNumber);



  const clickInfo = () => {
    axios.put(url+"/api/mypage",{
        password: pwdInfo,
        passwordRe : passwordRe,
        phoneNum: phoneNum,
        carNumber: carNumber
    })
    .then((res)=>{
        if(res.status===200){
         navigate('/mypage/{id}')
        }
    })
  }

    return <div>
        <div>
            <label>가입정보</label>
            <p>{myInfo.email}</p>
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
                setPhoneNum(e.target.value);
              }}/>
        </div>
        <div>
            <label>휴대폰 번호</label>
            <input 
            type="txt"
            value = {phoneNum}
            onChange={(e) => {
                setPasswordRe(e.target.value);
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
}

export default Editmypage;