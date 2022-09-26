import axios from "axios";
import { useState } from "react";
import RandomImg2 from "../../assets/profileimg/RandomImg2";



const Listreview =(props) =>{
    const [review, setReview] = useState(props.data);
    // const navigate = useNavigate();



    // 삭제버튼을 어떻게 할지 의문!
    const del =()=>{
        if(window.confirm("삭제하시겠습니까?")){
            axios.delete(`/api/reviews/1/${review.id}`)
            .then(res => {
                if(res.ok){
                    setReview({
                        ...review
                        ,id:0})
                }
            })
        }
    }

    if(review.id === 0 ){
        return null;
    }

    return(
        <div>
            <div key={review.id}>
                <RandomImg2 size={"reviewImg_size"} />
                <div>닉네임 : {review.nickName}</div>
                <div>{review.body}</div>
                <button>수정</button>
                <button onClick={del}>삭제</button>
            </div>
        </div>
    )
}

export default Listreview;