// react-icons
import { GrClose } from "react-icons/gr";

import "./Pay.css";
import { axiosPrivate } from "../../apis/axios";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";

const Pay = () => {
  const navigate = useNavigate();
  const { reservId } = useParams();

  const handleCancel = () => {
    alert(`결제를 취소하시겠습니까?`);
    axiosPrivate
      .delete(`/pay/${reservId}`)
      .then((res) => res.json())
      .then(navigate(`/`))
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="pay_container">
      <div className="pay_header">
        <h2>결 제</h2>
        <GrClose className="closebtn" size={22} onClick={handleCancel} />
      </div>
      <div className="pay_main"></div>
    </div>
  );
};

export default Pay;
