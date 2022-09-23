import axios from "axios";
// import { useNavigate} from "react-router-dom";
import { useState } from "react";
import { useLocation } from "react-router-dom";

const Editmypage = () => {

//   const navigate = useNavigate();

  const location = useLocation();
  const data = location.state.data;


  const [pwdInfo, setPwdInfo] = useState("");
  const [passwordRe, setPasswordRe] = useState("");
  const [phoneNum,setPhoneNum] = useState(data.phoneNum);
  const [carNumber,setCarNumber] = useState(data.carNumber);



  const clickInfo = () => {
    axios.patch("/api/mypage",{
        password: pwdInfo,
        passwordRe : passwordRe,
        phoneNum: phoneNum,
        carNumber: carNumber
    }, {
        withCredentials: true,
      })
    .then((res)=>{
        console.log('수정완료')
        console.log(res)
        // if(res.status===200){
        //  navigate('/mypage/{id}')
        // }
    }).catch((err)=>{
        console.log('수정안됨')
        console.log(err)
    })
  }

    return <div>
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
}

export default Editmypage;