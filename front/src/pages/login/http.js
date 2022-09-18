import axios from "axios";
// import { getToken } from "./GetToken";

import {cookies} from "react-cookie"

const http = axios.create({
    // baseURL: "http://localhost:4000",
    headers: { Authorization: `Bearer ${localStorage.getItem("access_token")}`,
    withCredentials: true
  },
  });

// http.interceptors.request.use(
//   (config) => {
//     const token = getToken();

//     config.headers = {
//       Authorization: `${token ? token : ""}`,
//       ...config.headers,
//     };

//     return config;
//   },
//   (error) => {
//     return Promise.reject(error);
//   }
// );

http.interceptors.response.use(
  (response) => {
    return response;
  },

  async (error) => {
    if (error.response.status === 401) {
      axios
        .post(
            "/api/oauth/token",
            //  { withCredentials: true} 


          {
            accessToken: localStorage.getItem("accessToken"),
            refreshToken: cookies.get("refreshToken"),
          }
        )
        .then((res) => {
          let accessToken = res.headers.accesstoken;
          let refreshToken = res.headers.refreshtoken;

          localStorage.setItem("accessToken", accessToken);
          localStorage.setItem("refreshToken", refreshToken);
          alert("토큰이 만료되었습니다. 새로고침해주세요");
        })
        .catch((err) => {
          if (err.response.status === 404) {
            console.log("여기");
            alert("로그인이 만료되었습니다.");
            // localStorage.removeItem("persist:root");
            localStorage.removeItem("accessToken");
            localStorage.removeItem("refreshToken");
          }
        });
    }

    return Promise.reject(error);
  }
);

export default http;