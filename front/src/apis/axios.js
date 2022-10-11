import axios from "axios";

const BASE_URL = `${process.env.REACT_APP_BASE_URL}/api`;

export default axios.create({ baseURL: BASE_URL });

// export const axiosPrivate = axios.create({
//   baseURL: BASE_URL,
//   headers: {
//     "Content-Type": "application/json",
//     authorization: localStorage.getItem("authorization"),
//   },
//   withCredentials: true,
// });
