import { useState } from "react";
// import { AiOutlineRight } from "react-icons/ai";
import AreaparkModal from "../../component/Modal/ParkSearchModal/AreaparkModal";
import "./ParkSearch.css"
import { FaAngleDown } from "react-icons/fa";
import TimeparkModal from "../../component/Modal/ParkSearchModal/TimeparkModal";


const ParkSearch = () =>{
    const [parkSearchModal, setParkSearchModal] = useState(false);
    const [timeparkSearchModal, setTimeParkSearchModal] = useState(false);
    const ParkSearchToggleModal = () => {
        setParkSearchModal(!parkSearchModal);
      };
      const TimeParkSearchToggleModal = () => {
        setTimeParkSearchModal(!timeparkSearchModal);
      };
    
    return (
        <div>
            <h2>어느 지역 주차장을 찾으시나요?</h2>
            <div className="dropdown">
            <label>지역선택</label>
            <label className="dropdownLabel"
            onClick={ParkSearchToggleModal}>
             <div>지역선택</div>
              <FaAngleDown className="caretIcon" />
            </label>
                <AreaparkModal
              parkSearchModal={parkSearchModal}
              ParkSearchToggleModal ={ParkSearchToggleModal}
            />
            <br/><br/>
            <label>시간선택</label>
            <label className="dropdownLabel"
            onClick={TimeParkSearchToggleModal}>
             <div >시간선택</div>
              <FaAngleDown className="caretIcon" />
            </label>
            <TimeparkModal 
                timeparkSearchModal={timeparkSearchModal}
                TimeParkSearchToggleModal ={TimeParkSearchToggleModal}
            />
            <br/><br/>
            <button>검색하기</button>
            <br/><br/>
            <h2>최근 검색 주차장</h2>
            <div>주차장 리스트</div>
            </div>
        </div>
    )
}

export default ParkSearch;