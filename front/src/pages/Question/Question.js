// react-icons
import { GrClose } from "react-icons/gr";

import "./Question.css";
import React from "react";
import { useNavigate } from "react-router-dom";

const Question = () => {
  const navigate = useNavigate();

  return (
    <div className="question_container">
      <div className="question_header">
        <h2>1:1 문의</h2>
        <GrClose
          className="closebtn"
          size={22}
          onClick={() => navigate(`/find`)}
        />
      </div>
      <div className="question_main">1:1 문의 구글 폼</div>
    </div>
  );
};

export default Question;
