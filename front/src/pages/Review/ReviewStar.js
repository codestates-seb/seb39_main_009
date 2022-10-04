//react-icons
import { ImStarFull } from "react-icons/im";

import "./../../pages/Review/Review.css";
import React, { useEffect, useState } from "react";

const ReviewStar = ({ setRating }) => {
  const [clicked, setClicked] = useState([false, false, false, false, false]);
  const arr = [0, 1, 2, 3, 4];

  const handleStarClick = (index) => {
    let clickStates = [...clicked];
    for (let i = 0; i < 5; i++) {
      clickStates[i] = i <= index ? true : false;
    }
    setClicked(clickStates);
  };

  const handleRating = () => {
    setRating(clicked.filter(Boolean).length);
  };

  useEffect(() => {
    handleRating();
  }, [clicked]);

  return (
    <>
      <div className="ratingStar">
        {arr.map((el, idx) => {
          return (
            <ImStarFull
              key={idx}
              size="50"
              onClick={() => handleStarClick(el)}
              className={clicked[el] && "yellowStar"}
            />
          );
        })}
      </div>
    </>
  );
};

export default ReviewStar;
