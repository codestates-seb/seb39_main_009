import React from "react";
import "./SideBar.css";
import { GrClose } from "react-icons/gr";

const SideBar = ({ handleSideClose }) => {
  return (
    <>
      <div className="sidebar_back" />
      <div className="sidebar">
        <div className="sidebar_header">
          <p>SideBar</p>
          <GrClose className="closebtn" size={22} onClick={handleSideClose} />
        </div>
      </div>
    </>
  );
};

export default SideBar;
