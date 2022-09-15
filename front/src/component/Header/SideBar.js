import React from "react";
import "./SideBar.css";
import { GrClose } from "react-icons/gr";
import { useRef } from "react";

const SideBar = ({ show, setShow, handleSideClose }) => {
  const sidebarBackRef = useRef();

  return (
    <>
      {show ? (
        <>
          <div
            className="sidebar_back"
            ref={sidebarBackRef}
            onClick={(e) => {
              if (sidebarBackRef.current === e.target) {
                setShow(false);
              }
            }}
          />
          <div className="sidebar">
            <div className="sidebar_header">
              <p>SideBar</p>
              <GrClose
                className="closebtn"
                size={22}
                onClick={handleSideClose}
              />
            </div>
          </div>
        </>
      ) : null}
    </>
  );
};

export default SideBar;
