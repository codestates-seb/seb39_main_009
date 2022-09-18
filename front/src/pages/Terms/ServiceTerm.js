import React from "react";
import "./Terms.css";
import { AiOutlineLeft } from "react-icons/ai";
import { useNavigate } from "react-router-dom";

const ServiceTerm = () => {
  const navigate = useNavigate();
  return (
    <div className="term_container">
      <div className="term_header">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <p>서비스 이용약관</p>
      </div>
      <div className="term_main">
        <div className="main_title">
          <h3>주차GO 서비스 약관</h3>
        </div>
        <div className="main_content">{data.content}</div>
      </div>
    </div>
  );
};

export default ServiceTerm;

const data = {
  content:
    "Aliqua aute velit duis in est occaecat velit velit velit ea ipsum Lorem non. Exercitation non dolor exercitation aliqua ipsum nostrud reprehenderit aute dolore ex consequat. Amet aute cupidatat pariatur Lorem est dolore esse minim occaecat eu aute. Aliquip duis nulla occaecat exercitation nostrud duis sit nostrud. Exercitation consectetur sint aute in amet irure voluptate Lorem magna consequat amet aute eiusmod eiusmod. Pariatur ad enim anim adipisicing laborum eiusmod reprehenderit tempor ea culpa ea incididunt exercitation sint. Aute sint sint exercitation qui duis ea ex. Adipisicing ea consequat cillum adipisicing ad id quis ullamco ad eiusmod sint. Nisi ex consectetur dolor ea ipsum nulla Lorem non excepteur quis cillum officia Lorem sint. Non sint veniam aliqua proident occaecat non. Fugiat nisi commodo officia reprehenderit nulla sunt excepteur proident anim minim deserunt ad. Tempor sunt laboris et duis laboris ex laborum fugiat sunt eu velit commodo. Elit laborum sit incididunt cillum dolore mollit minim quis irure ut. Officia pariatur cupidatat velit pariatur eiusmod quis commodo velit sunt. Do excepteur aliquip consequat dolor Lorem ipsum sint laboris. Incididunt culpa aliqua exercitation exercitation consequat aliqua laborum. Pariatur aliqua aliquip enim mollit incididunt laboris mollit Lorem eiusmod commodo sint minim. Nisi amet adipisicing veniam ex aliquip officia officia nostrud nostrud duis ullamco Lorem occaecat aliquip. Dolore aliqua magna excepteur laborum consequat. Duis consequat mollit do elit qui velit commodo ex exercitation magna sit do veniam. Anim velit nostrud tempor et incididunt. Anim ullamco nostrud commodo minim culpa aliquip fugiat labore culpa labore voluptate aute irure aute. Dolor esse dolore proident ex excepteur quis labore ullamco. Laboris eu esse culpa minim magna ullamco. Ad consectetur Lorem aute laborum enim amet.",
};
