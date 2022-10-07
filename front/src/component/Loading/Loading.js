// react-icons
import { FaCar } from "react-icons/fa";

import "./Loading.css";
import React from "react";

const Loading = () => {
  return (
    <div className="loading_container">
      <div className="car">
        <div className="circle"></div>
        <FaCar className="carfront" size={50} />
      </div>
    </div>
  );
};

export default Loading;
