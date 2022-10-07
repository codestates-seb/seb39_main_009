// react-icons
import { GiStarMedal } from "react-icons/gi";

import "./ParkingIcon.css";
import React, { useEffect } from "react";

const NationalMeritIcon = ({ data, nationalMerit, setNationalMerit }) => {
  useEffect(() => {
    const getNationalMerit = () => {
      const substring = "유공자";
      if (data.indexOf(substring) !== -1) {
        setNationalMerit(true);
      }
    };
    getNationalMerit();
  }, [data, setNationalMerit]);

  return (
    <div className="icon_container">
      {nationalMerit ? (
        <div>
          <GiStarMedal
            className="pk_icons"
            size={44}
            color={"rgb(164, 164, 164, 0.5)"}
          />
          <p>국가유공자</p>
          <p>할인</p>
        </div>
      ) : null}
    </div>
  );
};

export default NationalMeritIcon;
