// react-icons
import { AiOutlineRight } from "react-icons/ai";

import "./Signup.css";
import axios from "../../apis/axios";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import ServiceTermModal from "../../component/Modal/Term/ServiceTermModal";
import PsInfoTermModal from "../../component/Modal/Term/PsInfoTermModal";
import EventTermModal from "../../component/Modal/Term/EventTermModal";

function JoinPresenter(props) {
  const navigate = useNavigate();

  const [serviceModal, setServiceModal] = useState(false);
  const [psInfoModal, setPsInfoModal] = useState(false);
  const [eventModal, setEventModal] = useState(false);

  const serviceToggleModal = () => {
    setServiceModal(!serviceModal);
  };

  const psInfoToggleModal = () => {
    setPsInfoModal(!psInfoModal);
  };

  const eventToggleModal = () => {
    setEventModal(!eventModal);
  };

  const [allCheck, setAllCheck] = useState(false);
  const [ageCheck, setAgeCheck] = useState(false);
  const [useCheck, setUseCheck] = useState(false);
  const [marketingCheck, setMarketingCheck] = useState(false);

  const name = props.usernameinput;
  const email = props.emailinput;
  const password = props.checkpassword;
  const carNumber = props.carnumber;
  const phoneNum = props.phonenumber;
  const isName = props.isName;
  const isPassword = props.isPassword;
  const isPasswordConfirm = props.isPasswordConfirm;

  const [massage, setMassage] = useState("");

  const registeraxios = (event) => {
    event.preventDefault();
    axios
      .post(
        "/join",
        {
          name: name,
          email: email,
          password: password,
          carNumber: carNumber,
          phoneNum: phoneNum,
          svcUseAgmt: ageCheck,
          psInfoAgmt: useCheck,
          eventAgmt: marketingCheck,
        },
        { withCredentials: true }
      )
      .then((response) => {
        alert("회원가입성공");
        if (response.status === 201) {
          return navigate("/welcome");
        }
      })
      .catch((err) => {
        console.log(err);
        setMassage(err.response.data.massage);
      });
  };

  const allBtnEvent = () => {
    if (allCheck === false) {
      setAllCheck(true);
      setAgeCheck(true);
      setUseCheck(true);
      setMarketingCheck(true);
    } else {
      setAllCheck(false);
      setAgeCheck(false);
      setUseCheck(false);
      setMarketingCheck(false);
    }
  };

  const ageBtnEvent = () => {
    if (ageCheck === false) {
      setAgeCheck(true);
    } else {
      setAgeCheck(false);
    }
  };

  const useBtnEvent = () => {
    if (useCheck === false) {
      setUseCheck(true);
    } else {
      setUseCheck(false);
    }
  };

  const marketingBtnEvent = () => {
    if (marketingCheck === false) {
      setMarketingCheck(true);
    } else {
      setMarketingCheck(false);
    }
  };

  useEffect(() => {
    if (ageCheck === true && useCheck === true && marketingCheck === true) {
      setAllCheck(true);
    } else {
      setAllCheck(false);
    }
  }, [ageCheck, useCheck, marketingCheck]);

  return (
    <div className="joinpresent">
      <form method="post" action="">
        <label>가입약관을 읽고 동의를 해주세요.</label>
        <div className="joinpresent_inner">
          <div className="joinpresent_term">
            <input
              type="checkbox"
              id="all-check"
              checked={allCheck}
              onChange={allBtnEvent}
            />
            <label htmlFor="all-check">전체동의하기</label>
          </div>
          <div className="joinpresent_term">
            <input
              type="checkbox"
              id="check1"
              checked={ageCheck}
              onChange={ageBtnEvent}
            />
            <label htmlFor="check1">서비스 이용약관 (필수)</label>
            <AiOutlineRight
              className="rbt"
              size={18}
              onClick={serviceToggleModal}
            />
            <ServiceTermModal
              serviceModal={serviceModal}
              serviceToggleModal={serviceToggleModal}
            />
          </div>
          <div className="joinpresent_term">
            <input
              type="checkbox"
              id="check2"
              checked={useCheck}
              onChange={useBtnEvent}
              required
            />
            <label htmlFor="check2">개인 정보 수집 및 이용약관 (필수)</label>
            <AiOutlineRight
              className="rbt"
              size={18}
              onClick={psInfoToggleModal}
            />
            <PsInfoTermModal
              psInfoModal={psInfoModal}
              psInfoToggleModal={psInfoToggleModal}
            />
          </div>
          <div className="joinpresent_term">
            <input
              type="checkbox"
              id="check3"
              checked={marketingCheck}
              onChange={marketingBtnEvent}
              required
            />
            <label htmlFor="check3">이벤트 / 마케팅 수신동의 (선택)</label>
            <AiOutlineRight
              className="rbt"
              size={18}
              onClick={eventToggleModal}
            />
            <EventTermModal
              eventModal={eventModal}
              eventToggleModal={eventToggleModal}
            />
          </div>
          <p>{massage}</p>
          <button
            className={
              !(ageCheck && useCheck) ? "submit_bt btgrey" : "submit_bt"
            }
            onClick={registeraxios}
            disabled={
              !(
                isName &&
                isPassword &&
                isPasswordConfirm &&
                ageCheck &&
                useCheck
              )
                ? true
                : false
            }
          >
            회원가입
          </button>
        </div>
      </form>
    </div>
  );
}

export default JoinPresenter;
