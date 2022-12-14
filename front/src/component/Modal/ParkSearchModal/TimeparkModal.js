import "./AreaparkModal.css";
import { GrClose } from "react-icons/gr";
import "react-datepicker/dist/react-datepicker.css";
// import { useEffect } from "react";
import Select from "react-select";
import useGetTime from "../../../hooks/useGetTime";
import useMinGetTime from "../../../hooks/useMinGetTime";

const TimeparkModal = (props) => {
  const getTime = useGetTime();
  const minGetTime = useMinGetTime();

  let arr = [];
  for (let i = 0; i < 24; i++) {
    for (let j = 0; j <= 30; j += 30) {
      let op = {};
      op.value = ("0" + i).slice(-2) + ":" + ("0" + j).slice(-2);
      op.label = ("0" + i).slice(-2) + ":" + ("0" + j).slice(-2);
      arr.push(op);
    }
  }
  const tt = `${props.Timeday}T${props.timeValue.value}`;
  const yy = `${props.Timeday2}T${props.timeValue2.value}`;

  const totaltime = getTime(tt, yy);
  const mintime = minGetTime(tt, yy);

  return (
    // 스크롤 처리하기
    <>
      {props.timeparkSearchModal && (
        <div className="modal1">
          <div
            className="overlay1"
            onClick={props.TimeParkSearchToggleModal}
          ></div>
          <div className="modal-content1">
          <div className="modal-content2">

            <label>입차시간</label>
            <br />
            <input
              type="date"
              name="bday"
              required
              pattern="\d{4}-\d{2}-\d{2}"
              onChange={props.onChangeTime}
              value={props.Timeday}
            ></input>
            <br />
            <Select
              onChange={(value) => props.setTimeValue(value)}
              value={props.timeValue}
              placeholder="시간을 선택하세요."
              options={arr}
            />
            <br />
            <label>출차시간</label>
            <br />
            <input
              type="date"
              name="startday"
              onChange={props.onChangeTime2}
              value={props.Timeday2}
            ></input>
            <br />
            <Select
              onChange={(value) => props.setTimeValue2(value)}
              value={props.timeValue2}
              onBlur={mintime}
              placeholder="시간을 선택하세요."
              options={arr}
            />
            <br />
            <div className="totaltime">
              <div>총이용시간</div>
              {totaltime==="NaN시간 NaN분"?"":<div>{totaltime}</div>}
              
            </div>

            <br />
            <button
              // type="submit"
              // value="submit"
              onClick={props.TimeParkSearchToggleModal}
            >submit</button>
            </div>
            <GrClose
              size={25}
              className="close-modal1"
              onClick={props.TimeParkSearchToggleModal}
            />
          </div>
        </div>
      )}
    </>
  );
};

export default TimeparkModal;
