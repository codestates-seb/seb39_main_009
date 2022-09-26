import { Link } from "react-router-dom";
import { useParams } from "react-router-dom";
import Listreview from "./LIstreview";
import { useEffect, useState } from "react";



const Review =() =>{
  const [data, setData] = useState([])

  const {pkId} = useParams();


  useEffect(()=>{
      fetch('/api/reviews/1',{
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
      })
  }, [])



 return (
    <div>
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
 )
}

export default Review;