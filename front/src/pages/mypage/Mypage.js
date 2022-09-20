import { useNavigate } from "react-router-dom";
import { AiOutlineLeft } from "react-icons/ai";

const Mypage = () => {

  // const [myemail, setMyemail] = useNavigate()

const handlelogOut = () => {
        localStorage.removeItem("authorization");
        navigate('/')
      } 
  const navigate = useNavigate();

  // const url = ""

  // async function getUser() {
  //   try {
  //     const response = await axios.get(url + '/api/mypage');
  //     setMessage(response.email)
  //     console.log(response);
  //   } catch (error) {
  //     console.error(error);
  //   }


    return <div>
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
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
        onClick={()=>{navigate('/mypage/{id}/edit')}}
        >개인정보수정</button>
        <button
        onClick={handlelogOut}
        >로그아웃</button>
    </div>
}

export default Mypage;