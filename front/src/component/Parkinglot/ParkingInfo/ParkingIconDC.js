import React, { useState, useEffect } from "react";
import CompactCarIcon from "./../ParkingIcon/CompactCarIcon";
import DisabilityIcon from "./../ParkingIcon/DisabilityIcon";
import MultiChildIcon from "./../ParkingIcon/MultiChildIcon";
import NationalMeritIcon from "./../ParkingIcon/NationalMeritIcon";

const ParkingIconDC = ({ data }) => {
  const [compactCar, setCompactCar] = useState(false);
  const [disability, setDisability] = useState(false);
  const [multiChild, setMultiChild] = useState(false);
  const [nationalMerit, setNationalMerit] = useState(false);
  const [hide, setHide] = useState(false);

  useEffect(() => {
    // 할인, 감면 적용이 없는 주차장일 경우, 컴포넌트 숨겨준다.
    const handleHide = () => {
      if ((compactCar || disability || multiChild || nationalMerit) === true) {
        setHide(false);
      } else {
        setHide(true);
      }
    };
    handleHide();
  }, [hide]);

  return (
    <>
      {hide ? null : (
        <div className="pkIconDc">
          <p>감면 & 할인정보</p>
          <div className="pkIconDc_container">
            {/* 경차할인 */}
            <CompactCarIcon
              data={data.spacial_management}
              compactCar={compactCar}
              setCompactCar={setCompactCar}
            />
            {/* 장애인할인 */}
            <DisabilityIcon
              data={data.spacial_management}
              disability={disability}
              setDisability={setDisability}
            />
            {/* 다자녀할인 */}
            <MultiChildIcon
              data={data.spacial_management}
              multiChild={multiChild}
              setMultiChild={setMultiChild}
            />
            {/* 국가유공자할인 */}
            <NationalMeritIcon
              data={data.spacial_management}
              nationalMerit={nationalMerit}
              setNationalMerit={setNationalMerit}
            />
          </div>
        </div>
      )}
    </>
  );
};

export default ParkingIconDC;
