import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";


const Login = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");


  const onSubmit = (event) => {
    event.preventDefault();
    axios
      .post("/api/login", {
        email: email,
        password: password,
      })
      .then((res) => {
        console.log(res.data);
        localStorage.setItem("accessToken", res.headers.authorization);
        localStorage.setItem("refreshToken", res.headers.refreshToken);
        //20분뒤 로그인 연장
        setInterval(onSilentRefresh, 1200000);
        console.log(res.headers.authorization);
        // if (res.data.accessToken) {
        //   navigate("/");
        // }
      })
      .catch((Error) => {
        alert("일치하는 회원 정보가 없습니다.");
      });
  };
  
  const onSilentRefresh = () => {
    axios.post(' /api/oauth/token', {
      accessToken: localStorage.getItem("accessToken"),
      refreshToken: localStorage.getItem("refreshToken")
    })
        .then((response)=>{
          localStorage.setItem("accessToken",response.headers.authorization);
          localStorage.setItem("refreshToken",response.headers.refreshToken);
          //로그인 연장 후 20분 뒤
          setInterval(onSilentRefresh, 1200000);
        })
        .catch(error => {
          alert("실패.");

            // ... 로그인 실패 처리
        });
}
  // if (performance.navigation.type===1){
  //   //새로고침하면 바로 로그인 연장(토큰 갱신)
  //   onSilentRefresh();
  // }

  // useEffect(()=>{
  //   onSubmit()
  // })

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