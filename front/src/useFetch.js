import { useEffect, useState } from "react";
import axios from "axios";

axios.defaults.baseURL = ``;
axios.defaults.headers.common[`Authorization`] =
  localStorage.getItem("authorization");

const useFetch = (url) => {
  const [data, setData] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    setLoading(true);
    axios
      .get(url, {
        // headers: { Authorization: localStorage.getItem("authorization") },
        withCredentials: true,
      })
      .then((response) => {
        setData(response.data);
        console.log("connect!");
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
