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
    <div className="gopakring" >
      <div key={data.parkingId} onClick={clickpage} className="searchpage">
        <RandomImg size={"pakinglot2_size"} />
        <div className="parking_data">
          <div>{data.parkingName}</div>
          <div>{data.address}</div>
        </div>
      </div>
    </div>
  );
};

export default Presentsearchlist;
