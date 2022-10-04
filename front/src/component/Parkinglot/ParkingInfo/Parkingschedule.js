import "./ParkingInfo.css";
import React from "react";

const Parkingschedule = ({ data }) => {
  return (
    <>
      <div className="parkingInfo_title">
        <p>운영시간</p>
      </div>
      <div className="schedule_container">
        <div className="daily">
          <p></p>
          <div>
            <p>운영시작시간</p>
            <p>{data.weekdayOpen}</p>
          </div>
          <div className="daily_border"></div>
          <div>
            <p>운영종료시간</p>
            <p>{data.weekdayClose}</p>
          </div>
        </div>
        {data.satOpen === "00:00" && data.satClose === "00:00" ? null : (
          <div className="sat">
            <p>운영시간(토요일)</p>
            <p>
              {data.satOpen} ~ {data.satClose}
            </p>
          </div>
        )}
        {data.sunOpen === "00:00" && data.sunClose === "00:00" ? null : (
          <div className="sun">
            <p>운영시간(일, 공휴일)</p>
            <p>
              {data.sunOpen} ~ {data.sunClose}
            </p>
          </div>
        )}
        {data.satOpen === "00:00" &&
        data.satClose === "00:00" &&
        data.sunOpen === "00:00" &&
        data.sunClose === "00:00" ? (
          <div className="notopen">
            <p>( 주말미운영 )</p>
          </div>
        ) : null}
      </div>
    </>
  );
};

export default Parkingschedule;
