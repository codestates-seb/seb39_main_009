import { Link, useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import Listreview from "./LIstreview";
import { useEffect, useState } from "react";
import { GrClose } from "react-icons/gr";
import Loading from "../../component/Loading/Loading";





const Review =() =>{
  const [data, setData] = useState([]);
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const {pkId} = useParams();

  useEffect(()=>{
      fetch(`${process.env.REACT_APP_BASE_URL}/api/reviews/1`,{
          withCredentials: true,
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
      }).finally(() => {
        setLoading(false);
        // fetch 이후 로딩 해제
      }, 1000);
  }, [])



 return (<>
      {loading ? (
        <Loading />
        ) : (
      <div>
        <div className="signup_header">
          <p>리뷰페이지</p>
          <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
         </div>
        <Link to ={`/parking/${pkId}/review/write`}><button>리뷰작성</button></Link>
        <br />
          <div>
          {data.data &&data.data.map(data => (
                  
                  <Listreview data={data}/>
           ))}
          </div>
          <br/>
        <Link to={'#'}><button>예약하기</button></Link>
    </div>
        )}
    </>
 )
}

export default Review;