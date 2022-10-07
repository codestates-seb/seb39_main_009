// react-icons
import { BiMessageError } from "react-icons/bi";

import "./Error.css";
import React from "react";
import { useNavigate } from "react-router-dom";

const Error = () => {
  const navigate = useNavigate();

  return (
    <div className="error_container">
      <div className="error_main">
        <BiMessageError className="errorIcon" size={100} />
        <h1>Something went wrong.</h1>
        <h1>We're sorry ;(</h1>
        <div>
          <span>If you want to start over, </span>
          <span className="gobacklink" onClick={() => navigate(`/find`)}>
            Back To Home
          </span>
          <span>.</span>
        </div>
      </div>
    </div>
  );
};

export default Error;
