import { useNavigate} from "react-router-dom";
import { useState } from "react";
import { useLocation } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";

import './Mypage.css'


const Editmypage = () => {

  const navigate = useNavigate();

  const location = useLocation();
  const data = location.state.data;


  const [pwdInfo, setPwdInfo] = useState("");
  const [passwordRe, setPasswordRe] = useState("");
  const [phoneNum,setPhoneNum] = useState(data.phoneNum);
  const [carNumber,setCarNumber] = useState(data.carNumber);

  //오류메시지 상태저장
  const [passwordMessage, setPasswordMessage] = useState("");
  const [passwordConfirmMessage, setPasswordConfirmMessage] = useState("");
  const [phonenumberMessage, setPhonenumberMessage] = useState("");
  const [carnumberMessage, setCarnumberMessage] = useState("");


 // 유효성 검사
 const [isPassword, setIsPassword] = useState(false)
 const [isPasswordConfirm, setIsPasswordConfirm] = useState(false)
 const [isphonenumber, setIsPhonenumber] = useState(false);
 const [iscarnumber, setIsCarnumber] = useState(false);


  // 비밀번호
  const onChangePassword = (e) => {
    const passwordRegex =
      /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
      setPwdInfo(pwdInfo);

    if (!passwordRegex.test(pwdInfo)) {
      setPasswordMessage("숫자+영문자+특수문자 조합으로 8자리 이상");
      setIsPassword(false);
    } else {
      setPasswordMessage("안전한 비밀번호에요 : )");
      setIsPassword(true);
    }
  };
  // 비밀번호 확인
  const onChangePasswordConfirm = (e) => {
    if (pwdInfo === passwordRe) {
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
    if (regex.test(phoneNum)) {
      setPhonenumberMessage("올바른 전화번호입니다.");
      setIsPhonenumber(isphonenumber);
    } else setPhonenumberMessage("올바른 전화번호 형식이 아닙니다.");
    setIsPhonenumber(false);
  };

// 차번호
  const onChangecarnumber =(e)=>{
    const pattern1 = /\d{2,3}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 12저1234
    const pattern2 = /[가-힣ㄱ-ㅎㅏ-ㅣ\x20]{2}\d{2}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 서울12치1233
    if (!pattern1.test(carNumber)) {
      if (!pattern2.test(carNumber)) {
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
    if(json.status===200){
      alert('수정되었습니다');
      navigate(`/mypage/${data.id}`)
    }
    if(json.status===400){
      alert('비밀번호를 적어주세요');}


  })
    .catch((err)=> {
      alert('수정이 안되었습니다.')
    })
}


    return (
    <div className="editmypage_container">
      <div className="backheader">
        <AiOutlineLeft
          size={35}
          onClick={() => {
            navigate(-1);
          }}
        />
        <h2>개인정보수정</h2>
      </div>
      <div className="editmypage_main">
        <div>
            <label>이메일</label>
            <p>{data.email}</p>
        </div>
        <div>
            <label>비밀번호</label>
            <div>
            {pwdInfo.length > 0 && (
            <span className={`message${isPassword ? "success1" : "error1"}`}>
              {passwordMessage}
            </span>
          )}
          </div>
            <input 
            type="password"
            placeholder="password..."
            onChange={(e) => {
                setPwdInfo(e.target.value);
              }}
              onBlur={() => {
                onChangePassword(pwdInfo);
              }}
              autoComplete="new-password" 
              />
  
        </div>
        <div>
            <label>비밀번호확인</label>
            {passwordRe.length > 0 && (
              <span
                className={`message${isPasswordConfirm ? "success1" : "error1"}`}
              >
                {passwordConfirmMessage}
              </span>
            )}
            <input 
            type="password"
            placeholder="checkpassword..."
            onChange={(e) => {
                setPasswordRe(e.target.value);
              }}
              onBlur={() => {
                onChangePasswordConfirm(passwordRe);
              }}
              required
            />

        </div>
        <div>
            <label>휴대폰 번호</label>
            {phoneNum.length > 0 && (
              <span className={`message${phonenumberMessage=== "올바른 전화번호입니다."? "success1" : "error1"}`}>
                {phonenumberMessage}
              </span>
            )}
            <input 
            type="txt"
            value = {phoneNum}
            onChange={(e) => {
                setPhoneNum(e.target.value);
              }}
              onBlur={() => {
                onChangephonenumber(phoneNum);
              }}
              required
            />

        </div>
        <div>
            <label>차량번호</label>
            {carNumber.length > 0 && (
              <span className={`message${iscarnumber ? "success1" : "error1"}`}>
                {carnumberMessage}
              </span>
            )}
            <input 
            type="txt"
            value ={carNumber}
            onChange={(e) => {
                setCarNumber(e.target.value);
              }}
              onBlur={() => {
                onChangecarnumber(carNumber);
              }}
            />
        </div>
        <button
        onClick ={clickInfo}
        >수정</button>
        </div>

    </div>
)}

export default Editmypage;