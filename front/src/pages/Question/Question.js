import React from "react";
import "./Question.css";
import { useNavigate } from "react-router-dom";
import { GrClose } from "react-icons/gr";

const Question = () => {
  const navigate = useNavigate();

  return (
    <div className="question_container">
      <div className="question_header">
        <p>1:1 문의</p>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
      </div>
      <div className="question_main">1:1 문의 구글 폼</div>
    </div>
  );
};

export default Question;
