import React, { useState } from "react";
// import axios from "axios";
import { AiOutlineLeft } from "react-icons/ai";
import { useNavigate} from "react-router-dom";
import JoinPresenter from "./JoinPresenter";


const SignUp = () => {
  const navigate = useNavigate();
  // const location = useLocation();
  // console.log(location.state);
    // const svcUseAgmt = location.state.ageCheck ;
    // const psInfoAgmt = location.state.useCheck;
    // const eventAgmt = location.state.marketingCheck;
    // console.log(svcUseAgmt,psInfoAgmt,eventAgmt)

  // const url = ""

  // const [massage,setMassage] = useState("");

  const [usernameinput, setUsernameinput] = useState("");

  // const lala =localStorage.getItem("usernameinput");

  // if(lala!==null){
  //   localStorage.setItem("usernameinput", usernameinput);
  // }


  // useEffect(() => {
  //   const saved = localStorage.getItem("usernameinput");
  //   if (saved !== null) {
  //     setUsernameinput(saved);
  //   }
  // }, [usernameinput]);


//   useEffect(() => {
//       localStorage.setItem('usernameinput',usernameinput)
// });
//   const aa = localStorage.getItem("usernameinput")
  
  const [emailinput, setEmailinput] = useState("");
  localStorage.setItem('emailinput',emailinput)
  const bb = localStorage.getItem("emailinput")

  const [passwordinput, setPasswordinput] = useState("")
  localStorage.setItem('passwordinput',passwordinput)
  const cc = localStorage.getItem("passwordinput")

  const [checkpassword, setCheckpassword] = useState("");
  localStorage.setItem('checkpassword',checkpassword)
  const dd = localStorage.getItem("checkpassword")

  const [phonenumber, setPhonenumber] = useState("");
  localStorage.setItem('phonenumber',phonenumber)
  const ee = localStorage.getItem("phonenumber")

  const [carnumber, setCarnumber] = useState("");
  localStorage.setItem('carnumber',carnumber)
  const ff = localStorage.getItem("carnumber")

  // // const [svcUseAgmt, setSvcUseAgmt] = useState(false);
  // // const [psInfoAgmt, setPsInfoAgmt] = useState(false);
  // // const [eventAgmt, setEventAgmt] = useState(false);

  // const registeraxios = () => {
  //   axios
  //     .post(url+"/api/join", {
  //       name: usernameinput,
  //       email: emailinput,
  //       password: checkpassword,
  //       carNumber : carnumber,
  //       phoneNum : phonenumber,
  //       svcUseAgmt : svcUseAgmt,
  //       psInfoAgmt: psInfoAgmt,
  //       eventAgmt: eventAgmt ,
        
  //   },{withCredentials: true} )
  //     .then((response) => {
  //       console.log(response);
  //       alert("회원가입성공");
  //       if(response.status ===200){
  //           return navigate('/welcome')
  //       }
    
  //     }).catch((err)=>{
  //       console.log(err);
  //       setMassage(err.response.data.massage);
  //     })
  //     ;
  // };


  //   const changeSearch = (value) => {
  //     setSvcUseAgmt(value);
  //   };

  //   const changeSearch2=(value)=>{
  //     setPsInfoAgmt(value)
  //   }
 
  //   const changeSearch3=(value)=>{
  //     setEventAgmt(value)
  //   }



  return (
    <div className="signup">
      <div>
       <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        {/* <div>{massage}</div> */}
      <div className="signup_input">
      <label>Name</label>
      <br/>
      <input
        type="text"
        placeholder="username..."
        // value={aa}
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
        value={bb}
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
        value={cc}
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
        value={dd}
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
        value={ee}
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
        value={ff}
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
         {/* <button onClick={registeraxios}>회원가입</button> */}
      </div>
    </div>
  );
};

export default SignUp;