import "./../../pages/Review/Review.css";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import Pagination from "react-js-pagination";
import Listreview from "./LIstreview";
import Loading from "../../component/Loading/Loading";

const Review = () => {
  const { pkId } = useParams();
  const [reviews, setReviews] = useState([]);
  const [loading, setLoading] = useState(true);
  const [page, setPage] = useState(1);
  const [pageInfo, setPageInfo] = useState();

  const handlePageChange = (page) => {
    setPage(page);
  };

  useEffect(() => {
    fetch(
      `${process.env.REACT_APP_BASE_URL}/api/reviews/${pkId}/?page=${page}`,
      {
        withCredentials: true,
      }
    )
      .then((response) => {
        return response.json();
      })
      .then((res) => {
        setReviews(res.data);
        setPageInfo(res.pageInfo);
      })
      .catch((err) => {
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
        // fetch 이후 로딩 해제
      }, 1000);
  }, [page]);

  return (
    <>
      {loading && <Loading />}
      <div>
        <Link to={`/parking/${pkId}/review/write`}>
          <button>리뷰작성</button>
        </Link>
        <br />
        {reviews.length === 0 ? (
          <div>
            <p>작성된 리뷰가 없습니다.</p>
          </div>
        ) : (
          <>
            <div>
              {reviews.map((reviews, i) => (
                <Listreview key={i} reviews={reviews} pkId={pkId} />
              ))}
            </div>
            <Pagination
              activePage={page}
              itemsCountPerPage={pageInfo.size}
              totalItemsCount={pageInfo.totalElements}
              pageRangeDisplayed={pageInfo.totalPages}
              prevPageText={"‹"}
              nextPageText={"›"}
              onChange={handlePageChange}
            />
            <br />
          </>
        )}
      </div>
    </>
  );
};

export default Review;
