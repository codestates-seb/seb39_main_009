import { Link, useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import Listreview from "./LIstreview";
import { useEffect, useState } from "react";
import { GrClose } from "react-icons/gr";
import Loading from "../../component/Loading/Loading";

const Review = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const { pkId } = useParams();

  useEffect(() => {
    fetch(`${process.env.REACT_APP_BASE_URL}/api/reviews/${pkId}`, {
      withCredentials: true,
    })
      .then((response) => {
        return response.json();
      })
      .then((res) => {
        setData(res.data);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
        // fetch 이후 로딩 해제
      }, 1000);
  }, []);

  return (
    <>
      {loading && <Loading />}
      <div>
        <Link to={`/parking/${pkId}/review/write`}>
          <button>리뷰작성</button>
        </Link>
        <br />
        {data.length === 0 ? (
          <div>
            <p>작성된 리뷰가 없습니다.</p>
          </div>
        ) : (
          <>
            <div>
              {data.map((data) => (
                <Listreview data={data} pkId={pkId} />
              ))}
            </div>
            <br />
          </>
        )}
      </div>
    </>
  );
};

export default Review;
