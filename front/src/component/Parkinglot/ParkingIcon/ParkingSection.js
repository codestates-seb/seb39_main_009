import "./ParkingIcon.css";
import React from "react";

const ParkingSectionIcon = (props) => {
  return (
    <div>
      <div id="parkingSectionIcon">
        <div id="circle">
          <p>{props.text}</p>
        </div>
      </div>
      <p>{props.text}주차장</p>
    </div>
  );
};

export default ParkingSectionIcon;
