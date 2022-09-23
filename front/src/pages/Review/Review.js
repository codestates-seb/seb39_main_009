import { Link } from "react-router-dom";
// import Listreview from "./LIstreview";
import useFetch from "../../useFetch";


const Review =() =>{

  const { data } = useFetch(`/api/member`);


 return (
    <div>
        <h1>리뷰</h1>
        <Link to ='/parking/{id}/review/write'><button>리뷰작성</button></Link>
        <div> 
            {/* {data.map(review =>(
                <Listreview 
                key ={review.id}
                nickname ={review.nickname}
                body = {review.body}
                />
        ))} */}
        <div>{data.body}</div>

        </div>
        <Link to={'/parking/{id}/review/write'}><button>예약하기</button></Link>
    </div>
 )
}

export default Review;