import "./../Modal.css";
import React from "react";

const EventTermModal = ({ eventModal, eventToggleModal }) => {
  return (
    <>
      {eventModal && (
        <div className="modal">
          <div className="overlay" onClick={eventToggleModal}></div>
          <div className="modal-content">
            <h2>이벤트 / 마케팅 수신동의</h2>
            <hr />
            <h4>1. 광고성 정보의 이용목적</h4>
            <p>
              - 주차GO가 제공하는 이용자 맞춤형 서비스 및 상품 추천, 각종 행사,
              이벤트 등의 광고성 정보를 전자우편이나 서신 우편, 문자(SMS또는
              카카오 알림톡), 푸시, 전화 등을 통해 이용자에게 제공합니다.
            </p>
            <p>
              - 마케팅 수신 동의는 거부하실 수 있으며 동의 이후에라도 고객의
              의사에 따라 동의를 철회할 수 있습니다. 동의를 거부하시더라도
              주차GO가 제공하는 서비스의 이용에 제한이 되지 않습니다. 단, 할인,
              이벤트 및 이용자 맞춤형 상품 추천 등의 마케팅 정보 안내 서비스가
              제한됩니다.
            </p>
            <p>
              - 주차GO가 제공하는 이용자 맞춤형 서비스 및 상품 추천, 각종 행사,
              이벤트 등의 광고성 정보를 전자우편이나 서신 우편, 문자(SMS또는
              카카오 알림톡), 푸시, 전화 등을 통해 이용자에게 제공합니다.
            </p>
            <h4>2. 미동의 시 불이익 사항</h4>
            <p>
              - 개인정보보호법 제 22조 제 5항에 의해 선택 정보 사항에 대해서는
              동의 거부하시더라도 서비스 이용에 제한되지 않습니다. 단, 할인,
              이벤트 및 이용자 맞춤형 상품 추천 등의 마케팅 정보 안내 서비스가
              제한됩니다.
            </p>
            <h4>3. 서비스 정보 수신 동의 철회</h4>
            <p>
              - 주차GO에서 제공하는 마케팅 정보를 원하지 않을 경우, 주차GO
              고객센터에 철회를 요청할 수 있습니다.
            </p>
            <br />
            <button className="close-modal" onClick={eventToggleModal}>
              Close
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default EventTermModal;
