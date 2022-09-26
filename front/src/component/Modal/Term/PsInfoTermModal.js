import React from "react";
import "./../Modal.css";

const PsInfoTermModal = ({ psInfoModal, psInfoToggleModal }) => {
  return (
    <>
      {psInfoModal && (
        <div className="modal">
          <div className="overlay" onClick={psInfoToggleModal}></div>
          <div className="modal-content">
            <h2>개인 정보 수집 및 이용약관</h2>
            <hr />
            <p></p>
            <p>
              '주차GO'(이하 "회사"라 함)는 통신비밀보호법, 전기통신사업법,
              정보통신망 이용촉진 및 정보보호 등에 관한 법률 등 정보통신서비스
              제공자가 준수하여야 할 관련 법령상의 개인정보보호 규정을 준수하며,
              관련 법령에 의거한 개인정보 취급방침을 정하여 이용자 권익 보호에
              최선을 다하고 있습니다. 본 개인정보취급방침은 회사에서 제공하는
              온라인 웹 및 모바일 어플리케이션 서비스에 적용되며 다음과 같은
              내용을 담고 있습니다.
            </p>
            <h4>제 1조 (개인정보 수집동의 및 수집항목)</h4>
            <p>
              회사는 이용자가 "주차장 정보제공"에 최초 소셜로그인
              (페이스북,네이버,카카오 등) 을 시도할 때 애플리케이션 승인을 하면
              개인정보 수집에 대해 동의한 것으로 봅니다.
              <br />
              회사가 원활한 서비스 제공을 위해 수집하는 이용자의 개인정보 항목은
              다음과 같습니다.
              <br />
            </p>
            <ul>
              <li>가. 이용자 성명 및 연락처(휴대폰 번호)</li>
              <li>나. 이메일 주소와 고유식별 정보</li>
              <li>다. SNS 소셜로그인 회원가입 시 아이디 및 비밀번호</li>
              <li>
                라. 상기의 항목을 필수항목으로 하며, 서비스 제공을 위한 필요에
                따라 이용자의 동의 하에 추가적인 정보들을 요청하고 수집할 수
                있습니다.
              </li>
            </ul>
            <h4>제 2조 (개인정보 수집목적 및 활용방식)</h4>
            <p>
              회사는 회사에서 제공하는 서비스의 원활한 제공을 위하여 이용자의
              동의 하에 이용자의 개인정보를 수집하고 있습니다. 회사가 수집한
              개인정보는 다음의 목적을 위해 활용합니다.
              <br />
            </p>
            <ul>
              <li>가. 관련 해당 서비스 이용 권한</li>
              <li>나. 이메일 주소 - 고지사항 전달</li>
              <li>다. 불만 처리 등을 위한 원활한 의사소통 경로의 확보</li>
              <li>라. 새로운 서비스 및 행사 정보 등의 안내</li>
              <li>마. 차량의 스마트 입차, 출차 서비스 및 현장 확인용</li>
              <li>바. 원활한 모바일 신용카드 결제</li>
            </ul>
            <p>
              회사는 위의 목적 이외에는 수집된 개인정보를 사용할 수 없습니다.
              다만 추후 개인정보의 사용목적과 용도가 변경될 경우에는
              공지합니다.회사는 어떠한 경우라도 이용자의 개인정보를 사전에 밝힌
              목적 이외의 다른 목적으로 사용하지 않으며, 해당 정보를 외부로
              유출시키지 않습니다. 그러나 회사의 서비스 또는 광고 배너 등으로
              링크되어 있는 타 사이트에서 이용자의 동의를 거쳐 개인정보를
              수집하는 행위에 대해서는 본 개인정보보호정책이 적용되지 않음을
              알려드립니다.
            </p>
            <h4>제 3조 (수집한 개인정보 보유 및 이용기간)</h4>
            <p>
              이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용목적이
              달성되면 지체 없이 파기합니다. 단, 다음의 정보에 대해서는 아래의
              이유로 명시한 기간 동안 보존합니다.
            </p>
            <ul>
              <li>[ 회원탈퇴 시 보존 개인정보 ]</li>
              <li>- 보존항목 : 최초 접속 시 제공한 정보</li>
              <li>
                - 보존근거 : 불량 이용자의 재가입 방지, 부정 이용 방지, 명예훼손
                등 권리침해 분쟁 및 수사협조
              </li>
              <li>- 보존기간 : 회원탈퇴 후 3개월</li>
            </ul>
            <p>
              이용자가 회사에서 제공하는 서비스를 이용하는 동안은 회사는
              이용자의 개인정보를 지속적으로 보유하며 편리한 서비스의 제공을
              위한 기초 자료로 이용합니다. 다만 이용자 본인이 개인정보를
              변경하거나 삭제 요청을 하는 경우는 회사에서 정책에 따라서 삭제하여
              추후 열람이나 이용이 불가능한 상태로 처리하고 있습니다.
            </p>
            <h4>제 4조 (개인정보의 파기 절차 및 방법)</h4>
            <p>
              이용자의 개인정보는 원칙적으로 개인정보의 수집 및 이용목적이
              달성되면 지체 없이 파기합니다. 회사의 개인정보 파기절차 및 방법은
              다음과 같습니다.
            </p>
            <ul>
              <li>[ 파기 절차 ]</li>
              <li>
                가. 이용자가 회원가입 등을 위해 입력한 정보는 목적이 달성된 후
                별도의 DB로 옮겨져(종이의 경우 별도의 서류함) 내부 방침 및 기타
                관련 법령에 의한 정보보호 사유에 따라(보유 및 이용기간 참조)일정
                기간 저장된 후 파기됩니다.
              </li>
              <li>
                나. 동 개인정보는 법률에 의한 경우가 아니고서는 보유되는 이외의
                다른 목적으로 이용되지 않습니다.
              </li>
            </ul>
            <ul>
              <li>[ 파기 방법 ]</li>
              <li>
                가. 종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여
                파기합니다.
              </li>
              <li>
                나. 전자적 파일 형태로 저장된 개인정보는 기록을 재생할 수 없는
                기술적 방법을 사용하여 삭제합니다.
              </li>
            </ul>
            <h4>제 5조 (개인정보 제공 및 공유)</h4>
            <p>
              개인정보는 원칙적으로 서비스와 무관한 제 3자에게 이용자의 동의
              없이 제공될 수 없습니다. 회사는 약관에 의거하여 다음과 같은 경우
              개인정보를 이용자의 동의 없이 제공, 판매할 수 있습니다.
            </p>
            <ul>
              <li>
                가. 특정 개인을 식별할 수 없는 형태로 가공되어 회사와 계약한
                개인 또는 고객에게 제공되는 경우
              </li>
              <li>
                나. 통계작성, 학술연구 또는 시장조사를 위하여 필요한 경우로써
                특정 개인을 식별할 수 없는 형태로 제공하는 경우
              </li>
            </ul>
            <p>
              회사는 약관에 의거하여 다음과 같은 경우 개인정보를 이용자의 동의
              없이 제 3자에게 제공할 수 있습니다.
            </p>
            <ul>
              <li>
                가. 이용자가 서비스를 이용하여 타인에게 정신적 또는 물질적
                피해를 줌으로써 그에 대한 법적인 조치를 취하기 위하여 개인정보를
                공개해야 한다고 판단되는 충분한 근거가 있는 경우
              </li>
              <li>
                나. 이용자의 법령 또는 약관의 위반을 포함하여 부정행위 확인 등의
                정보보호 업무를 위해 필요한 경우
              </li>
              <li>
                다. 법에 의거하여 적법한 절차에 의한 수사기관이나 기타
                정부기관으로부터 정보제공을 요청 받은 경우
              </li>
              <li>라. 기타 법률에 의해 요구되는 경우</li>
            </ul>
            <br />
            <button className="close-modal" onClick={psInfoToggleModal}>
              Close
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default PsInfoTermModal;