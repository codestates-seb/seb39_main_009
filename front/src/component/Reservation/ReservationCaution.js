import React from "react";

const ReservationCaution = () => {
  return (
    <div className="reservation_inner">
      <div>
        <p>입출차 주의사항</p>
        <div>
          <ul>
            <li>- 예약 유효 시간 외 연박 주차 불가</li>
            <li>- 입출차 방법 : 현장관리자 주차권 확인</li>
            <li>- 주차GO 주차권 외 할인 적용 불가</li>
          </ul>
        </div>
      </div>
      <div>
        <p>안내사항</p>
        <div>
          <ul>
            <li>- 현장사정에 따라 주차가 어려울 수 있습니다.</li>
            <li>- 선불주차권으로 입차한 상태에서 구매시 사용 불가합니다.</li>
            <li>
              - 입출차는 1회만 가능하며, 주차권으로 출차 후 재입차는 현장요금
              적용합니다.
            </li>
            <li>
              예약 유효 시간 외 주차 시 주차권 적용 취소 및 총 이용시간에 대해
              전액 현장요금 적용합니다.
            </li>
            <li>
              주차권 2개 연속 사용 및 연박 주차 시 총 이용시간에 대해 전액
              현장요금 적용합니다.
            </li>
            <li>주차장에서의 도난, 분실, 사고는 일체 책임지지 않습니다.</li>
          </ul>
        </div>
      </div>
      <div>
        <p>예약취소/환불</p>
        <div>
          <ul>
            <li>
              입차 시간 1시간 전까지 '예약 취소'가 가능합니다. '나의 예약 내역'
              화면에서 구매한 주차권을 찾아 '예약 취소'버튼을 눌러주세요.
            </li>
            <li>주차권 구매 후 입차 시 주차권 환불 불가합니다.</li>
            <li>예약 시간보다 일찍 이용을 종료해도 부분 환불이 불가합니다.</li>
          </ul>
        </div>
      </div>
    </div>
  );
};

export default ReservationCaution;
