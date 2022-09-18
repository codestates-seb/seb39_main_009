import axios from "axios";
import { useState } from "react";

const FindEmail =() => {
    
    const [emailchange, setEmailchange]=useState

    const handleSubmit = (e)=> {
        e.preventDefault();

        const data = {
            email : emailchange
        }

        axios.post(`${process.env.REACT_APP_BASE_URL}/api/resetpwd`, data)
        .then(res => {
            console.log(res)
        }).catch(err=> {
            console.log(err)
        })
    }

    return <div>
    <label>Email</label>
        <input
         type="text"
         placeholder="email..."
         onChange={(e) => {
            setEmailchange(e.target.value);
          }}
        ></input>
        <button onClick={handleSubmit}>이메일 보내기</button>
    </ div>
}

export default FindEmail;