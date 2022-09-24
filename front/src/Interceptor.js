import axios from "axios";

// 응답 인터셉터 추가
axios.interceptors.response.use(
  function (response) {
    // 응답 데이터를 가공
    // ...
    return response;
  },
  function (error) {
    // 오류 응답을 처리
    // ...
    return Promise.reject(error);
  }
);

// //요청시 AccessToken 계속 보내주기
// axios.interceptors.request.use(function (config) {
//   const token = localStorage.getItem("token");

//   if (!token) {
//     config.headers["authorization"] = null;
//     config.headers["refreshtoken"] = null;
//     return config;
//   }
//   if (config.headers && token) {
//     const { authorization, refreshtoken } = JSON.parse(token);
//     config.headers["authorization"] = authorization;
//     config.headers["refreshtoken"] = refreshtoken;
//     return config;
//   }
// });

// //AccessToken이 만료됐을때 처리
// axios.interceptors.response.use(
//   function (response) {
//     return response;
//   },
//   async function (err) {
//     const originalConfig = err.config;

//     if (err.response && err.response.status === 403) {
//       const refreshtoken = originalConfig.headers["refreshtoken"];
//       try {
//         const data = await axios({
//           url: `/api/oauth/token`,
//           method: "GET",
//           headers: {
//             refreshtoken: refreshtoken,
//           },
//         });
//         if (data) {
//           localStorage.setItem(
//             "token",
//             JSON.stringify(data.data, ["authorization", "refreshtoken"])
//           );
//           return await axios.request(originalConfig);
//         }
//         console.log(`토큰 갱신 성공`);
//       } catch (err) {
//         console.log("토큰 갱신 에러");
//       }
//       return Promise.reject(err);
//     }
//     return Promise.reject(err);
//   }
// );
// export default axios;
