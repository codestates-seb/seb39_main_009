// react-icons
import { FaWheelchair } from "react-icons/fa";

import "./ParkingIcon.css";
import React, { useEffect } from "react";

const DisabilityIcon = ({ data, disability, setDisability }) => {
  useEffect(() => {
    const getDisability = () => {
      const substring = "장애인";
      if (data.indexOf(substring) !== -1) {
        setDisability(true);
      }
    };
    getDisability();
  }, [data, setDisability]);

  return (
    <div className="icon_container_wheel">
      {disability ? (
        <div>
          <FaWheelchair
            className="pk_icons"
            size={42}
            color={"rgb(164, 164, 164, 0.5)"}
          />
          <p>장애인할인</p>
        </div>
      ) : null}
    </div>
  );
};

export default DisabilityIcon;
