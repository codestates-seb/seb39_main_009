import "./Modal.css";
import axios from "../../apis/axios";
import React, { useContext, useState } from "react";
import { AuthContext } from "../../context/AuthContext";
import { CarNumContext } from "../../context/CarNumContext";

const CarNumChange = ({ toggleModal, modal, setModal }) => {
  const { auth } = useContext(AuthContext);
  const { newCarNum, setNewCarNum } = useContext(CarNumContext);
  const [carnumberMessage, setCarnumberMessage] = useState("");
  const [iscarnumber, setIsCarnumber] = useState(false);

  const onChangecarnumber = (e) => {
    const pattern1 = /\d{2,3}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 12저1234
    const pattern2 = /[가-힣ㄱ-ㅎㅏ-ㅣ\x20]{2}\d{2}[가-힣ㄱ-ㅎㅏ-ㅣ\x20]\d{4}/g; // 서울12치1233
    if (!pattern1.test(newCarNum)) {
      if (!pattern2.test(newCarNum)) {
        setCarnumberMessage("올바른 차량번호 형식이 아닙니다.");
        setIsCarnumber(false);
      } else {
        setCarnumberMessage("올바른 차량번호입니다.");
        setIsCarnumber(true);
      }
    } else {
      setCarnumberMessage("올바른 차량번호입니다.");
      setIsCarnumber(true);
    }
  };

  const carModalClose = () => {
    toggleModal();
    setNewCarNum("");
  };

  const updateCarNum = () => {
    axios
      .patch(
        `/member/car-number`,
        newCarNum,
        {
          headers: {
            "Content-Type": "application/json",
            authorization: auth,
          },
        },
        { withCredentials: true }
      )
      .then((res) => {
        alert("차량번호가 수정되었습니다.");
        setModal(!modal);
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  };

  return (
    <>
      {modal && (
        <div className="modal">
          <div className="overlay" onClick={toggleModal}></div>
          <div className="modal-content">
            <h2>차량번호 변경</h2>
            <div className="carnum_update_box">
              <p>- 변경 할 차량번호</p>
              <input
                type="text"
                placeholder="예: 123주4567"
                onChange={(e) => {
                  setNewCarNum(e.target.value);
                }}
                onBlur={() => {
                  onChangecarnumber(newCarNum);
                }}
              />
              {newCarNum.length > 0 && (
                <p className={`message${iscarnumber ? "success" : "error"}`}>
                  {carnumberMessage}
                </p>
              )}
              <button onClick={updateCarNum}>변경하기</button>
            </div>
            <button className="close-modal" onClick={carModalClose}>
              Close
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default CarNumChange;
