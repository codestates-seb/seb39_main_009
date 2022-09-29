// react-icons
import { BsFillCreditCardFill } from "react-icons/bs";

import "./ParkingIcon.css";
import React, { useState, useEffect } from "react";

const CreditCardIcon = ({ data }) => {
  const [creditCard, setCreditCard] = useState(false);

  useEffect(() => {
    const getCreditCard = () => {
      const substring = "신용카드";
      if (data === substring) {
        setCreditCard(true);
      }
    };
    getCreditCard();
  }, [data, setCreditCard]);

  return (
    <>
      {creditCard ? (
        <div className="icon_container_card">
          <BsFillCreditCardFill
            className="pk_icons"
            size={45}
            color={"rgb(164, 164, 164, 0.5)"}
          />
          <p>신용카드전용</p>
        </div>
      ) : null}
    </>
  );
};

export default CreditCardIcon;
