import "./ParkSearch.css";
import RandomImg from "../../assets/parkinglot/RandomImg";
import { useNavigate } from "react-router-dom";

const Presentsearchlist =(props) => {
    const navigate = useNavigate();
    const data = props.data;
    const clickpage = ()=>{
        navigate(`/parking/${data.parkingId}`)
      }

  return (
    <div>
      <br />
      <div key={data.parkingId} onClick={clickpage} className="searchpage">
        <RandomImg size={"reviewImg_size"} />
        <div>
          <div>주차장이름 : {data.parkingName}</div>
          <div>주차장 주소 : {data.address}</div>
        </div>
      </div>
    </div>
  );
};

export default Presentsearchlist;
