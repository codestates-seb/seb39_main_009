// react-icons
import { FaCarSide } from "react-icons/fa";

import "./ParkingIcon.css";
import React, { useEffect } from "react";

const CompactCarIcon = ({ data, compactCar, setCompactCar }) => {
  useEffect(() => {
    const getCompactCar = () => {
      const substring = "경차";
      if (data.indexOf(substring) !== -1) {
        setCompactCar(true);
      }
    };

    getCompactCar();
  }, [data, setCompactCar]);

  return (
    <div className="icon_container">
      {compactCar ? (
        <div>
          <FaCarSide
            className="pk_icons"
            size={44}
            color={"rgb(164, 164, 164, 0.5)"}
          />
          <p>경차할인</p>
        </div>
      ) : null}
    </div>
  );
};

export default CompactCarIcon;
