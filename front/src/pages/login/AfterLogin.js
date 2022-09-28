import React from "react";
import { useNavigate } from "react-router-dom";

const AfterLogin = () => {
  const navigate = useNavigate();

  return (
    <>
      <h1>You are logged in!</h1>
      <button onClick={() => navigate(-1)}>뒤로</button>
    </>
  );
};

export default AfterLogin;
