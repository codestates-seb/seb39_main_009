import { useNavigate } from "react-router-dom";


const Editmypage = () => {

  const navigate = useNavigate();

    return <div>
        <div>
            <label>가입정보</label>
            <p>email</p>
        </div>
        <div>
            <label>휴대폰 번호</label>
            <p>phoneNum</p>
        </div>
        <div>
            <label>차량번호</label>
            <p>carNumber</p>
        </div>
        <button
        onClick={()=>{navigate('/mypage/{id}')}}
        >수정</button>
    </div>
}

export default Editmypage;