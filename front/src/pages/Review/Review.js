//react-icons
import { FaStar } from "react-icons/fa";

import "./../../pages/Review/Review.css";
import { axiosPrivate } from "../../apis/axios";
import { useEffect, useState } from "react";
import useFetch from "../../hooks/useFetch";
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
  const [del, setDel] = useState("");

  const handlePageChange = (page) => {
    setPage(page);
  };

  const { data } = useFetch(`/parking/${pkId}/star`);

  const getReviewData = () => {
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
  };

  const handleDelReview = () => {
    if (window.confirm("삭제하시겠습니까?")) {
      axiosPrivate
        .delete(`/reviews/${pkId}`)
        .then(() => setDel(pkId))
        .catch((err) => {
          console.log(err);
        });
    }
  };

  useEffect(() => {
    getReviewData();
  }, [page, del]);

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
              <FaStar size={35} />
              <p>주차장 평점 {data.star}</p>
            </div>
            <div>
              {reviews.map((reviews, i) => (
                <Listreview
                  key={i}
                  reviews={reviews}
                  pkId={pkId}
                  handleDelReview={handleDelReview}
                />
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
