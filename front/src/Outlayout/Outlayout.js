import '../layout.css';
import './Outlayout.css';
import { FaCar,FaRegClock,FaSearch } from "react-icons/fa";



const Outlayout =()=>{
    return(
        <>
        <div className="outlayout">
            <div className='firsttitle'>
            <h2>더이상 주차 자리 찾아 </h2>
            <h2>빙~빙~ 돌지마세요! </h2>
            </div>
            <div className='middletitle'>
                <h1>주차GO</h1>
                <h2>로 내 자리 찜!</h2>
            </div>
            <div className='secondtitle'>
            <div className='con'>
                <FaCar className='Icontitle' color='#145b45' size={40} />
                <div>
                <div>원하는 자리를 예약해요</div>
                <p>주차장에서 원하는 자리를 </p>
                <p>선택하고 주차할 수있어요 </p>
                </div>
            </div>
            <div className='con'>
                <FaRegClock className='Icontitle' color='#145b45' size={40}/>
                <div>
                <div>원하는 시간대를 예약해요</div>
                <p>원하는 시간대와 지역을 </p>
                <p>검색하고 주차할 수 있어요 </p>
                </div>
            </div>
            <div className='con'>
                <FaSearch className='Icontitle' color='#145b45' size={40}/>
                <div>
                <div>내가 원하는 조건에 맞게 검색해요</div>
                <p>필터 검색 기능으로 원하는 조건에</p>
                <p>들어맞는 주차장을 찾아보세요</p>
                </div>
            </div>
            </div>
        </div>

        </>
    )
}

export default Outlayout;