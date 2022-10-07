// react-icons
import { FiMenu } from "react-icons/fi";

import "./Header.css";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import SideBarAfter from "./SideBarAfter";
import SideBarBefore from "./SideBarBefore";

const Header = ({ handlelogOut }) => {
  const navigate = useNavigate();

  const [show, setShow] = useState(false);

  const handleSideOpen = () => {
    setShow(true);
  };

  const handleSideClose = () => {
    setShow(false);
  };

  return (
    <div className="header_container">
      <FiMenu
        className="openbtn"
        size={30}
        onClick={handleSideOpen}
        color={"rgb(6, 167, 116)"}
      />
      <div className="logo" onClick={() => navigate(`/find`)}>
        <img
          alt="logo"
          src={process.env.PUBLIC_URL + "/juchagoh.png"}
          width={"35px"}
          height={"35px"}
        />
        <p>JUCHAGO</p>
      </div>
      <div className={show ? "side_active" : "side_hide"}>
        {localStorage.authorization ? (
          <SideBarAfter
            show={show}
            setShow={setShow}
            handleSideClose={handleSideClose}
            handlelogOut={handlelogOut}
          />
        ) : (
          <SideBarBefore
            show={show}
            setShow={setShow}
            handleSideClose={handleSideClose}
          />
        )}
      </div>
    </div>
  );
};

export default Header;
