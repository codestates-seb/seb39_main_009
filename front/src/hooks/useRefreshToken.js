import { useContext } from "react";
import axios from "../apis/axios";
import { AuthContext } from "../context/AuthContext";

// import { useNavigate } from "react-router-dom";
const REFRESH_URL = `/oauth/token`;

const useRefreshToken = () => {
  const { auth } = useContext(AuthContext);
  const onSilentRefresh = async () => {
    const refreshtoken = localStorage.getItem("refreshtoken");

    const response = await axios.post(
      REFRESH_URL,
      JSON.stringify({ refreshtoken }),
      {
        headers: {
          "Content-Type": "application/json",
          authorization: auth,
        },
      },
      { withCredentials: true }
    );
    const authorization = response.headers.authorization;
    localStorage.setItem("authorization", authorization);
    localStorage.setItem("auth", JSON.stringify(authorization));
    return authorization;
  };
  return onSilentRefresh;
};
export default useRefreshToken;
