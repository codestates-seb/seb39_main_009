import { useNavigate,Link  } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import axios from "axios";
import { useParams } from 'react-router-dom';
import { useEffect, useState, } from "react";


const Mypage = () => {
  const navigate = useNavigate();
  const [myInfo, setMyInfo] = useState([]);
  const { id } = useParams();

const handlelogOut = () => {
        localStorage.removeItem("authorization");
        localStorage.removeItem("refreshtoken");
        navigate('/')
      } 


  const url = ""

    useEffect(()=>{
      axios.get(url+`/api/mypage?id=${id}`)
      .then((res)=>res.json())
      .then(data=>{
        setMyInfo(data)
      })

      .catch((err)=>
      console.log(err))
    },[id])


    return <div>
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <div>
            <label>가입정보</label>
            <div>{myInfo.email}</div>
        </div>
        <div>
            <label>휴대폰 번호</label>
            <p>{myInfo.phoneNum}</p>
        </div>
        <div>
            <label>차량번호</label>
            <p>{myInfo.carNumber}</p>
        </div>
        <Link to = {`/mypage/${id}/edit`}
        state={{myInfo:myInfo}}>개인정보수정</Link>
        {/* <button
        onClick={()=>{navigate(`/mypage/${id}/edit`)}}
        >개인정보수정</button> */}
        <button
        onClick={handlelogOut}
        >로그아웃</button>
    </div>
}


export default Mypage;