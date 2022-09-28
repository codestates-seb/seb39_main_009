import axios from "../apis/axios";

const REFRESH_URL = `/oauth/token`;

const useRefreshToken = () => {
  const onSilentRefresh = async () => {
    const refreshtoken = localStorage.getItem("refreshtoken");

    const response = await axios.post(
      REFRESH_URL,
      JSON.stringify({ refreshtoken }),
      {
        headers: { "Content-Type": "application/json" },
        withCredentials: true,
      }
    );
    console.log("리프레시 토큰 연장!"); // 삭제 필요
    console.log(response); // 삭제 필요
    const newAuthorization = response.headers.authorization;
    localStorage.setItem("authorization", newAuthorization);
    return newAuthorization;
  };
  return onSilentRefresh;
};
export default useRefreshToken;
