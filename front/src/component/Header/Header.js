import React from "react";
import "./Header.css";
import { FiMenu } from "react-icons/fi";
import SideBar from "./SideBar";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Header = () => {
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
      <p onClick={() => navigate(`/`)}>This is Header(Home)</p>
      <div className={show ? "active" : "hide"}>
        <SideBar
          show={show}
          setShow={setShow}
          handleSideClose={handleSideClose}
        />
      </div>
    </div>
  );
};

export default Header;
