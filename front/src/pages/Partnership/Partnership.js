import React from "react";
import "./Partnership.css";
import { useNavigate } from "react-router-dom";
import { GrClose } from "react-icons/gr";

const Partnership = () => {
  const navigate = useNavigate();

  return (
    <div className="partnership_container">
      <div className="partnership_header">
        <p>제휴 문의</p>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <div className="partnership_main">제휴 문의 구글 폼</div>
    </div>
  );
};

export default Partnership;
