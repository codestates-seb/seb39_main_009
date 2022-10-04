// react-icons
import { BsGearWide } from "react-icons/bs";

import "./ParkingIcon.css";
import React, { useState, useEffect } from "react";

const FreeParkingIcon = ({ data }) => {
  const [freeParking, setFreeParking] = useState(false);

  useEffect(() => {
    const getFreeParking = () => {
      const substring = "무료";
      if (data === substring) {
        setFreeParking(true);
      }
    };

    getFreeParking();
  }, []);

  return (
    <>
      {freeParking ? (
        <div className="icon_container_free">
          <BsGearWide
            className="pk_icons"
            size={50}
            color={"rgb(164, 164, 164, 0.5)"}
          />
          <div id="free_circle">
            <p id="free_icontext">FREE</p>
          </div>
          <p>무료주차장</p>
        </div>
      ) : null}
    </>
  );
};

export default FreeParkingIcon;
