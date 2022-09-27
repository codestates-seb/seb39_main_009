import { useNavigate,Link, useParams  } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
// import useFetch from "../../useFetch";
import RandomImg2 from "../../assets/profileimg/RandomImg2";
import { GrClose } from "react-icons/gr";
import { useEffect, useState } from "react";
import Loading from "../../component/Loading/Loading";





const Mypage = () => {
  const navigate = useNavigate();
  const {id} = useParams();
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);




  // const { data } = useFetch(`/api/member`);
  
  useEffect(()=>{
    fetch(`${process.env.REACT_APP_BASE_URL}/api/member`,{
        headers : {
          authorization : localStorage.getItem("authorization")
     }
      })
    .then(response =>{
        return response.json()
    })
    .then(res => {
      setData(res)
        console.log('리스트 불러오기 성공')
        console.log(res)
    })
    .catch(err=>{
        console.log(err);
        console.log('리스트 불러오기 실패');
    })
    .finally(() => {
      setLoading(false);
      // fetch 이후 로딩 해제
    }, 1000);
}, [])
  
const handlelogOut = () => {
  localStorage.removeItem("authorization");
  localStorage.removeItem("refreshtoken");
  navigate('/')
} 



    return (<>
      {loading ? (
        <Loading />
        ) : (
    <div>
        <div className="signup_header">
          <p>마이페이지</p>
          <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
        </div>
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <RandomImg2 size={"reviewImg_size"} />
        <div>
            <label>가입정보</label>
            <div>{data.email}</div>
        </div>
        <div>
            <label>휴대폰 번호</label>
            <p>{data.phoneNum}</p>
        </div>
        <div>
            <label>차량번호</label>
            <p>{data.carNumber}</p>
        </div>
        <Link to = {`/mypage/${id}/edit`}
        state ={{data:data}}
        >개인정보수정</Link>
        <button
        onClick={handlelogOut}
        >로그아웃</button>
    </div>
       )}
    </>
)}


export default Mypage;