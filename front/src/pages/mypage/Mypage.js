import { useNavigate,Link, useParams  } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";
import useFetch from "../../useFetch";

const Mypage = () => {
  const navigate = useNavigate();
  const {id} = useParams();

const handlelogOut = () => {
        localStorage.removeItem("authorization");
        localStorage.removeItem("refreshtoken");
        navigate('/')
      } 

  const { data } = useFetch(`/api/member`);




    return <div>
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
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
}


export default Mypage;