import { axiosPrivate } from "../apis/axios";
import { useEffect, useState } from "react";

const useFetch = (url) => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    axiosPrivate
      .get(url)
      .then((response) => {
        setData(response.data);
      })
      .catch((err) => {
        setError(err);
        console.log(err);
      })
      .finally(() => {
        setLoading(false);
        // fetch 이후 로딩 해제
      }, 1000);
  }, [url]);

  return { data, loading, error };
};

export default useFetch;
