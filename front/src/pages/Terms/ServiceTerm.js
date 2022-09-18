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
        <div className="main_content">
          Sint dolor consectetur cillum nostrud proident minim voluptate duis
          duis commodo ipsum ipsum ex esse. Dolor sint aliqua qui duis labore
          excepteur commodo adipisicing eiusmod. Duis ipsum fugiat proident
          tempor cupidatat ad veniam nostrud dolore ea voluptate minim
          reprehenderit ex. Ullamco consectetur cillum quis nisi exercitation
          ea. Lorem in ut consequat commodo consequat consectetur non nulla eu.
          Adipisicing sunt nulla adipisicing occaecat. Minim proident proident
          Lorem ex. Voluptate et tempor amet veniam et culpa aute aute cupidatat
          in. Lorem aliquip do amet duis exercitation id. Quis velit qui duis
          labore occaecat sint. Nostrud qui dolor elit Lorem minim adipisicing
          qui culpa in veniam consequat nostrud. Ipsum nostrud ea voluptate duis
          id. Adipisicing esse velit tempor in duis ipsum deserunt ut duis ad.
          In ipsum deserunt reprehenderit adipisicing tempor fugiat nulla amet
          eu ea aliquip. Eu ullamco aliqua deserunt non ad incididunt.
        </div>
      </div>
    </div>
  );
};

export default ServiceTerm;
