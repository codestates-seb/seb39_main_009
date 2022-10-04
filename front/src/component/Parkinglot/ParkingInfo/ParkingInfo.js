import "./ParkingInfo.css";
import React from "react";
import ParkingIconDC from "./ParkingIconDC";
import ParkingIconInfo from "./ParkingIconInfo";
import Parkingschedule from "./Parkingschedule";
import ParkingFee from "./ParkingFee";
import RandomImg from "../../../assets/parkinglot/RandomImg";

const ParkingInfo = ({ data }) => {
  return (
    <div className="parkingInfo_container">
      <RandomImg size={"pakinglot_size"} />
      <Parkingschedule data={data} />
      <ParkingFee data={data} />
      <ParkingIconInfo data={data} />
      <ParkingIconDC data={data} />
    </div>
  );
};

export default ParkingInfo;
