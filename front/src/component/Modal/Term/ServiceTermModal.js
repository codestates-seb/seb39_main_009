import "./../Modal.css";
import React from "react";

const ServiceTermModal = ({ serviceModal, serviceToggleModal }) => {
  return (
    <>
      {serviceModal && (
        <div className="modal">
          <div className="overlay" onClick={serviceToggleModal}></div>
          <div className="modal-content">
            <h2>서비스 이용약관</h2>
            <hr />
            <h4>제 1조 (목적)</h4>
            <ul>
              <li>
                본 약관은 "주차GO"(이하 "회사"라 함)가 운영하는 "주차GO"에서
                제공하는 모든 서비스를 이용함에 있어 이용자의 권리 및 의무, 기타
                부수 사항에 관하여 규정함을 목적으로 합니다.
              </li>
            </ul>
            <h4>제 2조 (정의)</h4>
            <ul className="ul_style">
              <li>
                “주차GO”라 함은 “회사”가 “회사” 및 “주차공간 제공자”가 등록한
                “주차공간”을 “회원”이 “주차공간”을 사용할 수 있도록 온라인 웹,
                모바일 어플리케이션 등 정보통신 소프트웨어를 이용한 중개서비스
                및 이와 관련한 부가서비스 일체(이하 “서비스”라 함)를 말합니다.
              </li>
              <li>
                “주차공간”이라 함은 자동차를 주차하기 위한 시설을 말하며,
                주차장법에 의거해 노상주차장, 노외주차장, 부설주차장으로
                구분됩니다.
              </li>
              <li>
                “주차공간 제공자”라 함은 “주차GO”을 통해 주차공간의 정보를
                온라인상에 등록하는 자로서, 상기에 구분된 주차장의 유형별로 소유
                혹은 관리 및 운영 권한이 있는 주체를 의미합니다.
              </li>
              <li>
                “이용자”라 함은 본 약관에 동의하고 “회사”가 제공하는 서비스를
                이용하는 자로 “회원”과 “비회원”으로 구분됩니다.
              </li>
              <li>
                “회원”이라 함은 “회사”가 정한 소정의 절차를 거쳐서 회원가입을 한
                자로서, "주차GO"의 정보를 지속적으로 제공받으며, "주차GO"가
                제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다.
              </li>
              <li>
                “비회원”이라 함은 회사의 “회원” 자격을 득하지 않고 “회사”의
                서비스를 이용하는 자를 말합니다.
              </li>
              <li>
                “컨텐츠”라 함은 “주차공간 제공자” 또는 “이용자”가 “주차공간”의
                정보공유 및 판매를 위하여 게재한 모든 글, 사진, 동영상 등을
                말합니다.
              </li>
            </ul>
            <h4>제 3조 (약관의 명시, 효력과 개정)</h4>
            <ul className="ul_style">
              <li>
                회사는 본 약관의 내용을 회사의 상호, 영업소 소재지, 대표자의
                성명, 사업자등록번호, 연락처(전화, 팩스, 전자우편주소 등)등과
                함께 회원이 확인할 수 있도록 주차GO 초기 서비스 화면(전면)에
                게시합니다.
              </li>
              <li>
                회사는 약관의 규제에 관한 법률, 전기통신사업법 및
                정보통신망이용촉진 및 정보보호 등에 관한 법률 등 관련법을
                위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.
              </li>
              <li>
                약관은 주차GO 서비스 화면에 게시하거나 기타의 방법으로 서비스를
                이용하고자 하는 자에게 공지하고, 이용자가 이에 동의함으로써
                효력이 발생합니다.
              </li>
              <li>
                이용자가 주차GO 서비스에 회원 가입신청을 하거나 네이버, 카카오,
                페이스북의 계정을 이용하여 가입신청을 하는 경우 본 약관에 명시된
                조건에 동의하여 회원 가입신청을 하는 것으로 간주합니다.
              </li>
              <li>
                회사는 필요한 사유가 발생했을 때 본 약관을 변경할 수 있습니다.
                회사가 약관을 변경할 때에는 적용일자 및 변경사유를 명시하여
                제3항과 같은 방법으로 그 적용일자 7일 전부터 공지합니다. 다만,
                약관 변경이 이용자에게 불리한 경우에는 그 적용일자 30일 전부터
                제3항과 같은 방법으로 공지하며, 회원에 대하여는 회사가
                필요하다고 판단하는 경우 회원가입 시 회원이 기재한 이메일 등으로
                추가 개별 통지합니다. 단, 회원이 이메일 등을 기재하지 않았거나,
                이메일 등이 변경되었음에도 수정하지 않아 개별 통지가 어려운
                경우에는 제3항과 같은 방법으로 공지함으로써 개별 통지한 것으로
                간주합니다. 본 조에도 불구하고 법령의 개정 등 긴급한 사유가
                발생했을 때에는 사전 고지 없이 약관을 변경할 수 있고, 이 때
                변경된 약관은 시행일로부터 7일간 제3항과 같은 방법으로 공지하고,
                명시적으로 이용자가 반대하지 않는 한 이용자는 개정된 약관에
                동의한 것으로 간주됩니다.
              </li>
              <li>
                이용자에게 약관 변경 적용일까지 거부의사를 표시하지 아니할 경우,
                약관의 변경에 동의한 것으로 간주한다는 내용을 공지 또는
                통지하였음에도 이용자가 명시적으로 약관 변경에 대한 거부의사를
                표시하지 아니하면, 이용자가 변경 약관에 동의한 것으로
                간주합니다.
              </li>
              <li>
                회원은 변경된 약관 사항에 동의하지 않으면 서비스 이용을 중단하고
                이용 계약을 해지할 수 있습니다. 변경된 약관이 시행된 이후에도
                계속 당사가 제공하는 서비스를 이용하는 경우 회원은 변경된 약관에
                동의한 것으로 간주합니다. 이와 별개로 회사는 회원을 탈퇴 처리할
                수 있습니다.
              </li>
              <li>
                비회원도 변경된 약관의 적용을 거부할 수 있으나, 이 경우 변경된
                약관의 적용을 받는 해당 서비스의 제공은 불가능합니다. 변경된
                약관이 시행된 이후에도 계속 당사가 제공하는 서비스를 이용하는
                경우 비회원은 변경된 약관에 동의한 것으로 간주합니다.
              </li>
              <li>
                제5항에 따라 변경 고지된 약관은 기존의 회원에게도 유효하게
                적용됩니다.
              </li>
            </ul>
            <br />
            <button className="close-modal" onClick={serviceToggleModal}>
              Close
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default ServiceTermModal;
