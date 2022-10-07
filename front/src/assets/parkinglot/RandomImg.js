import React from "react";
import "./RandomImg.css";
import parkinglot01 from "./../../assets/parkinglot/parkinglot01.jpeg";
import parkinglot02 from "./../../assets/parkinglot/parkinglot02.jpeg";
import parkinglot03 from "./../../assets/parkinglot/parkinglot03.jpeg";
import parkinglot04 from "./../../assets/parkinglot/parkinglot04.jpeg";
import parkinglot05 from "./../../assets/parkinglot/parkinglot05.jpeg";
import parkinglot06 from "./../../assets/parkinglot/parkinglot06.jpeg";
import parkinglot07 from "./../../assets/parkinglot/parkinglot07.jpeg";
import parkinglot08 from "./../../assets/parkinglot/parkinglot08.jpeg";
import parkinglot09 from "./../../assets/parkinglot/parkinglot09.jpeg";

const RandomImg = (props) => {
  const imgArr = [
    parkinglot01,
    parkinglot02,
    parkinglot03,
    parkinglot04,
    parkinglot05,
    parkinglot06,
    parkinglot07,
    parkinglot08,
    parkinglot09,
  ];

  const rNum = Math.floor(Math.random() * 9);
  let imgUrl = imgArr[rNum];

  return (
    <>
      <img className={props.size} alt={imgArr[rNum]} src={imgUrl} />
    </>
  );
};

export default RandomImg;
