import "./ParkingTabs.css";
import React, { useState } from "react";
import ParkingInfo from "./../ParkingInfo/ParkingInfo";
import Review from "../../../pages/Review/Review";

const ParkingTabs = ({ data }) => {
  const [tabs, setTabs] = useState(1);

  const toggleTab = (index) => {
    setTabs(index);
  };

  return (
    <div className="tabs-container">
      <div className="tabs-menu">
        <button
          className={tabs === 1 ? "tabs active-tabs" : "tabs"}
          onClick={() => toggleTab(1)}
        >
          기본정보
        </button>
        <button
          className={tabs === 2 ? "tabs active-tabs" : "tabs"}
          onClick={() => toggleTab(2)}
        >
          리뷰
        </button>
      </div>

      <div className="tabs-content">
        <div className={tabs === 1 ? "content active-content" : "content"}>
          <ParkingInfo data={data} />
        </div>
        <div className={tabs === 2 ? "content active-content" : "content"}>
          <Review />
        </div>
      </div>
    </div>
  );
};

export default ParkingTabs;
