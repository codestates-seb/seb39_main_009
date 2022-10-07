import { useNavigate, Link, useParams } from "react-router-dom";
import RandomImg2 from "../../assets/profileimg/RandomImg2";
// import { GrClose } from "react-icons/gr";
import { useEffect, useState } from "react";
import Loading from "../../component/Loading/Loading";
import './Mypage.css'
import { AiOutlineLeft } from "react-icons/ai";
import { BiLogOut } from "react-icons/bi";


const Mypage = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch(`${process.env.REACT_APP_BASE_URL}/api/member`, {
      headers: {
        authorization: localStorage.getItem("authorization"),
      },
    })
      .then((response) => {
        return response.json();
      })
      .then((res) => {
        setData(res);
        console.log(res)
        // console.log("리스트 불러오기 성공");
      })
      .catch((err) => {
        // console.log("리스트 불러오기 실패");
      })
      .finally(() => {
        setLoading(false);
        // fetch 이후 로딩 해제
      }, 1000);
  }, []);

  const handlelogOut = () => {
    localStorage.removeItem("authorization");
    localStorage.removeItem("refreshtoken");
    navigate("/");
  };

  return (
    <>
      {loading ? (
        <Loading />
      ) : (
        <div className="mypage_container">
      <div className="backheader">
        <AiOutlineLeft
          size={35}
          onClick={() => {
            navigate(-1);
          }}
        />
        <h2>마이페이지</h2>
      </div>
          <div className="mypage_main">
            <div className="profil">
              <RandomImg2 size={"reviewImg_size"} />
              <div>{data.name}</div>
            </div>
            <div className="profil_Info">
              <div>
                <label>이메일 주소</label>
                <p>{data.email}</p>
              </div>
              <div>
                <label>휴대폰 번호</label>
                <p>{data.phoneNum}</p>
              </div>
              <div>
                <label>차량번호</label>
                <p>{data.carNumber}</p>
              </div>
        </div>

          <button><Link to={`/mypage/${id}/edit`} className="link" state={{ data: data }}>
            개인정보수정
          </Link></button>
          <div className="mypage_logout" onClick={handlelogOut}>
                <BiLogOut className="mypage_icons" size={22} />
                <p>로그아웃</p>
              </div>
        </div>
        </div>
      )}
    </>
  );
};

export default Mypage;
