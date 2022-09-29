// react-icons
import { TbMoodKid } from "react-icons/tb";

import "./ParkingIcon.css";
import React, { useEffect } from "react";

const MultiChildIcon = ({ data, multiChild, setMultiChild }) => {
  useEffect(() => {
    const getMultiChild = () => {
      const substring = "자녀";
      if (data.indexOf(substring) !== -1) {
        setMultiChild(true);
      }
    };

    getMultiChild();
  }, [data, setMultiChild]);

  return (
    <div className="icon_container">
      {multiChild ? (
        <div>
          <TbMoodKid
            className="pk_icons"
            size={44}
            color={"rgb(164, 164, 164, 0.5)"}
          />
          <p>다자녀할인</p>
        </div>
      ) : null}
    </div>
  );
};

export default MultiChildIcon;
