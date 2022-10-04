import { useState } from "react";
import AreaparkModal from "../../component/Modal/ParkSearchModal/AreaparkModal";
import "./ParkSearch.css"
import { FaAngleDown } from "react-icons/fa";
import TimeparkModal from "../../component/Modal/ParkSearchModal/TimeparkModal";
import { useEffect } from "react";
import PresentSearch from "./PresentSearch";
import ClickSearch from "./ClickSearch";
import { GrClose } from "react-icons/gr";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import useDateFormat2 from "../../hooks/useDateFormat2";


const ParkSearch = () =>{
  const navigate = useNavigate();

  // 모달창
    const [parkSearchModal, setParkSearchModal] = useState(false);
    const [timeparkSearchModal, setTimeParkSearchModal] = useState(false);
    const ParkSearchToggleModal = () => {
      setParkSearchModal(!parkSearchModal);
    };
    const TimeParkSearchToggleModal = () => {
      setTimeParkSearchModal(!timeparkSearchModal);
    };

    //지역선택모달창에서 선택한 값 전달
    const [Content, setContent] = useState('지역선택');
    const [Content2, setContent2] = useState('');
    const [Content3, setContent3] = useState('');
    const totalContent = `${Content} ${Content2} ${Content3}`

    const onChangeHanlder=(e)=>{
      setContent(e.currentTarget.value)
    }
    const onChangeHanlder2=(e)=>{
      setContent2(e.currentTarget.value)
    }
    const onChangeHanlder3=(e)=>{
      setContent3(e.currentTarget.value)
    }


    
    //시간선택 모달창에서 선택한 값 전달
    const dateFormat2 = useDateFormat2();

    const [Timeday, setTimeday] = useState('시간선택');
    const [Timeday2, setTimeday2] = useState('시간선택');
    const [timeValue, setTimeValue] = useState('');
    const [timeValue2, setTimeValue2] = useState('');
    const inputtime = `${Timeday} ${timeValue.value||""}`
    const inputtime2 = `${Timeday} ${timeValue2.value||""}`
    const formatday = dateFormat2(Timeday)
    const formatday2 = dateFormat2(Timeday2)
    const sendTime = `${formatday} ${timeValue.value}`
    const sendTime2= `${formatday2} ${timeValue2.value}`

    
    const onChangeTime=(e)=>{
      setTimeday(e.currentTarget.value)
    }

    const onChangeTime2=(e)=>{
      setTimeday2(e.currentTarget.value)
    }

    //로딩 데이터
  const [loading, setLoading] = useState(true);

    // 최근내역 받아온 데이터
    const [lala, setLala] = useState('')


      const getUser=(e)=>{
        axios.get(`${process.env.REACT_APP_BASE_URL}/api/parking`,{
          params :{
            region:totalContent,
            parkingStartDateTime: sendTime,
            parkingEndDateTime: sendTime2,
            sort:"",
            crtLocation:""
          }
        },{
          headers: {
            authorization: localStorage.getItem("authorization"),
          },
        })
        .then(res => {
            setLala(res)
            // console.log(res.status)
            console.log('리스트 불러오기 성공2')
            console.log(res)
        })
        .catch(err=>{
            console.log(err)
            console.log('리스트 불러오기 실패2');
        })
        .finally(() => {
          setLoading(false);
          // fetch 이후 로딩 해제
        }, 1000)
    }

        const [viewcon, setViewcon] = useState(true)
        const hide = (e)=>{
            setViewcon(!viewcon)


        }
    

// 테스토용
      useEffect(() => {
  // console.log(totalContent);
  // console.log('whgdk')
    // console.log(lala.status)

  // console.log("lala결과값");
});




    return (<>
        <div >
        <div className="signup_header">
          <p>주차장 조건 검색 페이지</p>
          <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
         </div>
         <div className="parksearch_main">
            <h2>어느 지역 주차장을 찾으시나요?</h2>
            <div className="dropdown">
            <label>지역선택</label>
            <label className="dropdownLabel"
            onClick={ParkSearchToggleModal}>
              <input
              placeholder="지역선택"
              value={totalContent}
              />

              <FaAngleDown/>
            </label>
                <AreaparkModal
                Content = {Content}
                Content2 = {Content2}
                Content3 = {Content3}
                onChangeHanlder = {onChangeHanlder}
                onChangeHanlder2 = {onChangeHanlder2}
                onChangeHanlder3 = {onChangeHanlder3}
                parkSearchModal={parkSearchModal}
                ParkSearchToggleModal ={ParkSearchToggleModal}
            />
            <br/><br/>
            <div>
            <label>시간선택</label>
            <br/>
            <label className="labeltext">입차시간</label>
            <label>출차시간</label>
            </div>
            <label className="dropdownLabel"
            onClick={TimeParkSearchToggleModal}>
             <input
              placeholder="시간선택"
              value={inputtime}
              required/>
              <div>-</div>
              <input
              placeholder="시간선택"
              value={inputtime2}
              required/>
              <FaAngleDown className="caretIcon" />
            </label>
            <TimeparkModal 
                Timeday ={Timeday}
                Timeday2 ={Timeday2}
                timeValue ={timeValue}
                timeValue2 ={timeValue2}
                setTimeValue ={setTimeValue}
                setTimeValue2 ={setTimeValue2}
                onChangeTime={onChangeTime}
                onChangeTime2={onChangeTime2}
                timeparkSearchModal={timeparkSearchModal}
                TimeParkSearchToggleModal ={TimeParkSearchToggleModal}
            />
            <br/><br/>
            <button 
            onClick={()=>{hide();getUser();}}
      
            >검색하기</button>
           
            <div>
           { viewcon ? <PresentSearch /> : 
           (lala.status===200? <ClickSearch 
            lala={lala} loading = {loading}/> : <PresentSearch />
            )  
            }
              </div>
          </div>
          </div>
        </div>
        </>
    )
}

export default ParkSearch;