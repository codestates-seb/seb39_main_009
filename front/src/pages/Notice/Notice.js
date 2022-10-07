// react-icons
import { AiOutlineDown, AiOutlineUp } from "react-icons/ai";
import { GrClose } from "react-icons/gr";

import "./Notice.css";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import useDateFormat from "../../hooks/useDateFormat";
import Loading from "../../component/Loading/Loading";
import Error from "../../component/Error/Error";

const Notice = () => {
  const dateFormat = useDateFormat();
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(null);

  const toggle = (i) => {
    if (isOpen === i) {
      return setIsOpen(null);
    }
    setIsOpen(i);
  };

  const { data, loading, error } = useFetch(`/notice`);

  if (loading) {
    return <Loading />;
  }
  if (error) {
    return <Error />;
  }

  return (
    <div className="notice_container">
      <div className="notice_header">
        <h2>공지사항</h2>
        <GrClose
          className="closebtn"
          size={22}
          onClick={() => navigate(`/find`)}
        />
      </div>
      <div className="notice_main">
        {data &&
          data.map((notice, i) => (
            <div className="notice" key={i}>
              <div
                className={
                  isOpen === i ? `notice_title n_active` : `notice_title`
                }
                onClick={() => toggle(i)}
              >
                <div className="title">{`[${
                  notice.type === `NORMAL`
                    ? `공지`
                    : notice.type === `EMERGENCY`
                    ? `긴급공지`
                    : `이벤트`
                }] ${notice.title}`}</div>
                <div className="date">{dateFormat(notice.createdDate)}</div>
                <div className="arrowicon">
                  {isOpen === i ? (
                    <AiOutlineUp size={20} />
                  ) : (
                    <AiOutlineDown size={20} />
                  )}
                </div>
              </div>
              <div
                className={
                  isOpen === i ? `notice_content` : "notice_content n_hide"
                }
              >
                {notice.body}
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default Notice;
