import React from "react";
import "./Terms.css";
import { AiOutlineLeft } from "react-icons/ai";
import { useNavigate } from "react-router-dom";

const Parkinglot = () => {
  const navigate = useNavigate();
  return (
    <div className="parkinglot_container">
      <div className="parkinglot_header">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <p>마케팅 정보 수신 동의</p>
      </div>
      <div className="parkinglot_main"></div>
    </div>
  );
};

export default Parkinglot;
