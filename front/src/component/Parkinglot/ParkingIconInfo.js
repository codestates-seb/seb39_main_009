import React from "react";
import ParkingSection from "./ParkingIcon/ParkingSection";
import ParkingType from "./ParkingIcon/ParkingType";
import CreditCardIcon from "./ParkingIcon/CreditCardIcon";
import FreeParkingIcon from "./ParkingIcon/FreeParkingIcon";

const ParkingIconInfo = ({ data }) => {
  return (
    <div className="pkIconInfo">
      <p>주차장 종류 및 형태</p>
      <div className="pkIconInfo_container">
        {/* 공영/민영/부설 구분 */}
        <ParkingSection text={data.parkingSeparation} />
        {/* 노외/노상 구분 */}
        <ParkingType text={data.parkingType} />
        {/* 무료주차장 */}
        <FreeParkingIcon data={data.parkingChargeInfo} />
        {/* 신용카드전용 */}
        <CreditCardIcon data={data.methodPay} />
      </div>
    </div>
  );
};

export default ParkingIconInfo;
