import "./ParkingIcon.css";
import React from "react";

const ParkingType = (props) => {
  return (
    <div>
      <div id="parkingTypeIcon">
        <p>{props.text}</p>
      </div>
      <p>{props.text}주차장</p>
    </div>
  );
};

export default ParkingType;
