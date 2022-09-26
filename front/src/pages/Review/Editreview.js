import axios from "axios";
import { useRef } from "react";
// import { useNavigate } from "react-router-dom";
// import useFetch from "../../useFetch";
// import { useParams } from "react-router-dom";
// import { useEffect} from "react";



const Editreview =() =>{
    // const navigate=useNavigate();

    // const { data } = useFetch(`/api/member`);

const reviewRef= useRef(null)
const nickname = useRef(null)
// const {pkId} = useParams();


const onSubmit=(e)=>{
    e.preventDefault();

    // http://localhost:3001/data

    axios.post('/api/reviews/1',{
        star : nickname.current.value,
        body : reviewRef.current.value,
    },{
        withCredentials: true,
      })
    .then(res=>{
        console.log('리뷰등록')
        // navigate(`/parking/${pkId}/review`)
        console.log(res)
    })
    .catch(err=>{
        console.log('등록실패')
        console.log(err)
    })
}



return (
    <form onSubmit={onSubmit}>
        <h2>상품은 만족하셨나요?</h2>
        <div>별점표시</div>
        <h2>어떤 점이 좋았나요?</h2>
        <input 
         placeholder="닉네임"
        type="text"
        ref={nickname}
        />
        <textarea
         placeholder="최소 10자 이상은 입력해주세요."
         type ="text"
         ref={reviewRef}/>
        <button>등록</button>
    </form>
 )
}

export default Editreview;