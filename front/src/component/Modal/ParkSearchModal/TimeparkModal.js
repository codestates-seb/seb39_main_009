import "./AreaparkModal.css"
import { GrClose } from "react-icons/gr";
import React, { useState } from "react";
// import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

import Select from 'react-select';


const TimeparkModal = ({ timeparkSearchModal,TimeParkSearchToggleModal }) => {
    // const [startDate, setStartDate] = useState(new Date());
    const [timeValue, setTimeValue] = useState('');
    const [timeValue2, setTimeValue2] = useState('');


let arr = [];
for ( let i=0; i<24; i++) {
    for (let j=0; j<=30; j+=30) {
        let op = {};
        op.value = ('0' + i).slice(-2) + ":" +('0' + j).slice(-2)
        op.label = ('0' + i).slice(-2) + ":" +('0' + j).slice(-2)
        arr.push(op)
    }
}
  
  return (
    <>
        {timeparkSearchModal && (
            <div className="modal1">
                <div className="overlay1" onClick={TimeParkSearchToggleModal}></div>
                 <div className="modal-content1">
                    {/* <DatePicker selected={startDate} onChange={date => setStartDate(date)} /> */}
                    <input type="date" ></input>
                    <br/>
                    <label>입차시간</label>
                     <Select
                        onChange={(value) => setTimeValue(value)}
                        value = {timeValue}
                        placeholder="시간을 선택하세요."
                        options={arr}
                         />
                    <br/>
                    <label>출차시간</label>
                    <Select
                        onChange={(value) => setTimeValue2(value)}
                        value = {timeValue2}
                        placeholder="시간을 선택하세요."
                        options={arr}
                         />
                    <br/>
                    {/* useGetTime Hook 사용*/}
                    <div>총 이용시간</div>
                    <br/>
                    <input type="submit" value="submit" />
                    <GrClose size={25} className="close-modal1" onClick={TimeParkSearchToggleModal} />
                  {/* <button className="close-modal1" onClick={ParkSearchToggleModal}>
                    Close
                  </button> */}
                </div>
              </div>
         )}
    </>
  );
};

export default TimeparkModal;



