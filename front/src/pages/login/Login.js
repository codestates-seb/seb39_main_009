import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";


const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const url = ""


  const onSubmit = (event) => {
    event.preventDefault();
    axios
      .post(url + "/api/login", {
        email: email,
        password: password,
      })
      .then((res) => {
        console.log(res);
        localStorage.setItem("authorization", res.headers.authorization);
        localStorage.setItem("refreshtoken", res.headers.refreshtoken);
        // setTimeout(onSilentRefresh,20000);
      })
      .catch((Error) => {
        console.log(Error)
        alert("일치하는 회원 정보가 없습니다.");
      });
  };
  
  const onSilentRefresh = () => {
    axios.post(url+"/api/oauth/token", {
      refreshtoken: localStorage.getItem("refreshtoken")
    })
        .then((response)=>{
          console.log(response);
          console.log("연장됨")
          localStorage.setItem("authorization",response.headers.authorization);

        })
        .catch(error => {
          alert("연장실패.");
            // ... 로그인 실패 처리
        });
}

useEffect(()=>{
  setInterval(onSilentRefresh,20000);
},[])


  // if (performance.navigation.type===1){
  //   //새로고침하면 바로 로그인 연장(토큰 갱신)
  //   onSilentRefresh();
  // }

    return  (
        <div >
          <div className="Login">
          <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
            <div className="inputemail">
              <label>Email</label>
              <br/>
              <input
                type="text"
                onChange={(e) => {
                setEmail(e.target.value);
               }}
                placeholder="email..."
              />
            </div>
            <div  className="inputpassword">
              <label>password</label>
              <br/>
              <input
                type="password"
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                placeholder="password..."
              />
             </div>
            <button className="buttonlogin" onClick={onSubmit}>Login</button>
          </div>
            <button onClick={()=>navigate('/join')}>회원가입하기</button>
        </div>
      );
}

export default Login;
