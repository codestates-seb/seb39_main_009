import "./ParkingInfo.css";
import React from "react";

const ParkingFee = ({ data }) => {
  const getbasicTime = (chargeInfo, basicTime, addUnitTime) => {
    if (chargeInfo !== "무료") {
      let setBasicTime = basicTime;
      if (basicTime === 0) {
        setBasicTime = addUnitTime;
      }
      if (basicTime === 0.5) {
        setBasicTime = 30;
      }
      if (setBasicTime)
        if (setBasicTime < 60) {
          return `${setBasicTime}분`;
        } else if (setBasicTime >= 60 && setBasicTime < 1440) {
          let setHour = setBasicTime / 60;
          return `${setHour}시간`;
        } else if (setBasicTime >= 1440) {
          let setDay = setBasicTime / 1440;
          return `${setDay}일`;
        }
    } else {
      return `무료`;
    }
  };
  return (
    <>
      <div className="parkingInfo_title">
        <p>주차요금</p>
      </div>
      <div className="parkingfee_container">
        <div className="basicfee">
          <div>
            <p>기본요금</p>
            <p>
              {getbasicTime(
                data.parkingChargeInfo,
                data.basicTime,
                data.addUnitTime
              )}
              /
              {data.basicCharge === 0
                ? data.addUnitCharge.toLocaleString("ko-KR")
                : data.basicCharge.toLocaleString("ko-KR")}
              원
            </p>
          </div>
          <div>
            <p>추가요금</p>
            <p>
              {getbasicTime(
                data.parkingChargeInfo,
                data.addUnitTime,
                data.basicTime
              )}
              /
              {data.addUnitCharge === 0
                ? data.basicCharge.toLocaleString("ko-KR")
                : data.addUnitCharge.toLocaleString("ko-KR")}
              원
            </p>
          </div>
        </div>
        <div className="maxfee">
          <p>일최대요금</p>
          <p>{data.dayMaxPrice.toLocaleString("ko-KR")}원</p>
        </div>
      </div>
    </>
  );
};

export default ParkingFee;
