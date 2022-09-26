import React from "react";
import "./RandomImg2.css";
import img_avatar1 from "./../../assets/profileimg/img_avatar1.png";
import img_avatar2 from "./../../assets/profileimg/img_avatar2.png";
import img_avatar3 from "./../../assets/profileimg/img_avatar3.png";
import img_avatar4 from "./../../assets/profileimg/img_avatar4.png";
import img_avatar5 from "./../../assets/profileimg/img_avatar5.png";


const RandomImg2 = (props) => {
  const imgArr2 = [
    img_avatar1,
    img_avatar2,
    img_avatar3,
    img_avatar4,
    img_avatar5,


  ];

  const rNum2 = Math.floor(Math.random() * 5);
  let imgUrl2 = imgArr2[rNum2];

  return (
    <>
      <img className={props.size} alt={imgArr2[rNum2]} src={imgUrl2} />
    </>
  );
};

export default RandomImg2;
