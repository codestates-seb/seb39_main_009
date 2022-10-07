import axios from "axios";
import { useState } from "react";

const FindPwd = () => {
  const [state, setState] = useState();
  // state = {};

  const [pwdchange, setPwdchange] = useState;

  const handlechange = (e) => {
    e.preventDefault();

    const data = {
      // token : this.props.match.params.id,
      password: pwdchange,
    };

    axios
      .put(`${process.env.REACT_APP_BASE_URL}/api/resetpwd/{UUID}`, data)
      .then((res) => {
        console.log(res);
        setState({
          reset: true,
        });
        if (state.reset) {
          return "로그인창으로";
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <label>비밀번호</label>
      <input
        type="password"
        placeholder="password..."
        onChange={(e) => {
          setPwdchange(e.target.value);
        }}
      ></input>
      <label>비밀번호 재설정</label>
      <input type="password" placeholder="password..."></input>
      <button onClick={handlechange}>비밀번호 재설정</button>
    </>
  );
};

export default FindPwd;
