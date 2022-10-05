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
      <Parkingschedule data={data} />
      <ParkingFee data={data} />
      <ParkingIconInfo data={data} />
      <ParkingIconDC data={data} />
      <RandomImg size={"pakinglot_size"} />
    </div>
  );
};

export default ParkingInfo;
