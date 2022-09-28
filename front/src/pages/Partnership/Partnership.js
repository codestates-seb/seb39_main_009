// react-icons
import { GrClose } from "react-icons/gr";

import "./Partnership.css";
import React from "react";
import { useNavigate } from "react-router-dom";

const Partnership = () => {
  const navigate = useNavigate();

  return (
    <div className="partnership_container">
      <div className="partnership_header">
        <h2>제휴 문의</h2>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <div className="partnership_main">제휴 문의 구글 폼</div>
    </div>
  );
};

export default Partnership;
