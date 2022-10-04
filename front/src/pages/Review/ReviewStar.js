//react-icons
import { FaStar } from "react-icons/fa";

import "./../../pages/Review/Review.css";
import React, { useState } from "react";

const ReviewStar = ({ star, setStar }) => {
  const [hover, setHover] = useState(null);
  return (
    <div className="ratingStar">
      {[...Array(5)].map((el, i) => {
        const ratingValue = i + 1;

        return (
          <label>
            <input
              type="radio"
              nama="rating"
              value={ratingValue}
              onClick={() => setStar(ratingValue)}
            />
            <FaStar
              className="star"
              color={ratingValue <= (hover || star) ? "#ffc107" : "#e4e5e9"}
              size={42}
              onMouseEnter={() => setHover(ratingValue)}
              onMouseLeave={() => setHover(null)}
            />
          </label>
        );
      })}
    </div>
  );
};

export default ReviewStar;
