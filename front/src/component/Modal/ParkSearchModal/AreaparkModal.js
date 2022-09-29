import React from "react";
// import { FaAngleDown } from "react-icons/fa";
import "./AreaparkModal.css"
import { GrClose } from "react-icons/gr";

const AreaparkModal = ({ parkSearchModal,ParkSearchToggleModal }) => {

  
  return (
    <>
        {parkSearchModal && (
            <div className="modal1">
                <div className="overlay1" onClick={ParkSearchToggleModal}></div>
                 <div className="modal-content1">
                    <select className="ul_style1">
                        <option value="시/도" selected="selected">시/도</option>
                        <option value="서울시" >서울시</option>
                    </select>
                    <br/>
                    <select className="ul_style1">
                        <option value="구" selected="selected">구</option>
                        <option value="강남구" >강남구</option>
                    </select>
                    <br/>
                    <select className="ul_style1">
                        <option value="동" selected="selected">동</option>
                        <option value="역삼동" >역삼동</option>
                        <option value="대치동">대치동</option>
                    </select>
                    <br/>
                    <input type="submit" value="submit" />
                    <GrClose size={25} className="close-modal1" onClick={ParkSearchToggleModal} />
                </div>
              </div>
         )}
    </>
  );
};

export default AreaparkModal;



