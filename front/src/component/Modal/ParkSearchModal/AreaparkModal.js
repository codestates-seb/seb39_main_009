import React from "react";
import "./AreaparkModal.css"
import { GrClose } from "react-icons/gr";

const AreaparkModal = (props) => {


  return (
    <>
        {props.parkSearchModal && (
            <div className="modal1">
                <div className="overlay1" onClick={props.ParkSearchToggleModal}></div>
                 <div className="modal-content1">
                   <div className="modal-content2">
                    <select className="ul_style1" onChange={props.onChangeHanlder} value={props.Content}>
                        <option value="시/도" >시/도</option>
                        <option value="서울특별시" >서울특별시</option>
                    </select>
                    <br/>
                    <select className="ul_style1" onChange={props.onChangeHanlder2} value={props.Content2}>
                        <option value="구" >구</option>
                        <option value="강남구" >강남구</option>
                    </select>
                    <br/>
                    <select className="ul_style1" onChange={props.onChangeHanlder3} value={props.Content3}>
                        <option value="동" >동</option>
                        <option value="신사동" >신사동</option>
                        <option value="압구정동">압구정동</option>
                        <option value="청담동">청담동</option>
                        <option value="삼성동">삼성동</option>
                        <option value="대치동">대치동</option>
                        <option value="역삼동">역삼동</option>
                        <option value="논현동">논현동</option>
                        <option value="도곡동">도곡동</option>
                        <option value="개포동">개포동</option>
                        <option value="일원본동">일원본동</option>
                        <option value="수서동">수서동</option>
                        <option value="세곡동">세곡동</option>
                        <option value="일원동">일원동</option>
                    </select>
                    <br/>
                        <button 
                        onClick={props.ParkSearchToggleModal}>submit</button>
                    </div>
                    <GrClose size={25} className="close-modal1" onClick={props.ParkSearchToggleModal} />
                </div>
              </div>
         )}
    </>
  );
};

export default AreaparkModal;



