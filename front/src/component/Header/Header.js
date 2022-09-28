import React from "react";
import "./Header.css";
import { FiMenu } from "react-icons/fi";
import SideBar from "./SideBar";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import useRefreshToken from "../../hooks/useRefreshToken";

const Header = ({ handlelogOut }) => {
  const navigate = useNavigate();
  const onSilentRefresh = useRefreshToken();

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
        <SideBar
          show={show}
          setShow={setShow}
          handleSideClose={handleSideClose}
          handlelogOut={handlelogOut}
        />
      </div>
      <button onClick={() => onSilentRefresh()}>리프레시토큰</button>
    </div>
  );
};

export default Header;
