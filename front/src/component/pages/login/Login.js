import axios from "axios";
import { useState } from "react";



const Login = () => {



  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const loginaxios = async(e) => {

 e.preventDefault();

   
    axios
      .post( "/api/login", {
        email: email,
        password: password
      }, {withCredentials: true})
      .then((response) => {
        localStorage.setItem("Token", response.headers.authorization);

        console.log(response);
      })
      .catch((err) => {
        console.log(err);
      });


    
  };
    return  (
        <div >
          <div className="Login">
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
            <button className="buttonlogin" onClick={loginaxios}>Login</button>
          </div>
    
        </div>
      );
}

export default Login;