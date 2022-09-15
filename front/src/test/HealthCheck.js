import axios from "axios";
import { useState } from "react";

function HealthCheck() {
  const [test, setTest] = useState("");

  const inputData = { test: test };

  const handlePostData = () => {
    axios
      .post(`${process.env.REACT_APP_BASE_URL}/api/test`, inputData)
      .then((res) => {
        console.log(res);
        alert(`post 성공!`);
      })
      .catch((err) => {
        console.log(err);
      });
    setTest("");
  };

  return (
    <div>
      <form onSubmit={(e) => e.preventDefault()}>
        <label>서버 건강 검진</label>{" "}
        <input
          type="text"
          value={test}
          placeholder="Health Check!"
          onChange={(e) => setTest(e.target.value)}
          autoFocus
          required
        />{" "}
        <button onClick={handlePostData}>POST</button>
      </form>
    </div>
  );
}

export default HealthCheck;
