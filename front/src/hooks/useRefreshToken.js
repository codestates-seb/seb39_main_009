import { axiosPrivate } from "../apis/axios";

// import { useNavigate } from "react-router-dom";
const REFRESH_URL = `/oauth/token`;

const useRefreshToken = () => {
  const onSilentRefresh = async () => {
    const refreshtoken = localStorage.getItem("refreshtoken");

    const response = await axiosPrivate.post(
      REFRESH_URL,
      JSON.stringify({ refreshtoken }),
      {
        headers: { "Content-Type": "application/json" },
        withCredentials: true,
      }
    );
    console.log("리프레시 토큰 연장!"); // 삭제 필요
    const authorization = response.headers.authorization;
    localStorage.setItem("authorization", authorization);
    localStorage.setItem("auth", JSON.stringify(authorization));
    return authorization;
  };
  return onSilentRefresh;
};
export default useRefreshToken;
