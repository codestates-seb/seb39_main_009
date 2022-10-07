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
      <FiMenu className="openbtn" size={22} onClick={handleSideOpen} />
      <p className="logo" onClick={() => navigate(`/`)}>
        This is Header(Home)
      </p>
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
