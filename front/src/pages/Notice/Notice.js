import React, { useState } from "react";
import "./Notice.css";
import { AiOutlineDown, AiOutlineUp } from "react-icons/ai";
import { GrClose } from "react-icons/gr";
import { useNavigate } from "react-router-dom";

const Notice = () => {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(null);

  const toggle = (i) => {
    if (isOpen === i) {
      return setIsOpen(null);
    }
    setIsOpen(i);
  };

  return (
    <div className="notice_container">
      <div className="notice_header">
        <h2>공지사항</h2>
        <GrClose className="closebtn" size={22} onClick={() => navigate(`/`)} />
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
                <div className="date">{notice.created_date.substr(0, 10)}</div>
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

const data = [
  {
    type: "EVENT",
    title:
      "이벤트 Elit do dolore qui consectetur est commodo proident quis voluptate qui ullamco.",
    created_date: "2022-09-15T15:36:58",
    body: "Exercitation non enim veniam eiusmod nostrud ex deserunt dolore non ad esse aliquip. Ullamco labore anim aliqua ipsum. Duis dolore minim magna minim enim nisi voluptate id. Minim cupidatat exercitation esse eiusmod nulla veniam do aliquip culpa. Nostrud labore do ea aliqua incididunt mollit incididunt ullamco et. Laborum incididunt anim eiusmod eu consectetur proident velit. Dolore cupidatat consectetur veniam pariatur officia tempor exercitation.",
  },
  {
    type: "NORMAL",
    title: "공지사항",
    created_date: "2022-09-15T15:36:58",
    body: "Exercitation non enim veniam eiusmod nostrud ex deserunt dolore non ad esse aliquip. Ullamco labore anim aliqua ipsum. ",
  },
  {
    type: "EMERGENCY",
    title: "긴급공지사항",
    created_date: "2022-09-15T15:36:58",
    body: "Exercitation non enim veniam eiusmod nostrud ex deserunt dolore non ad esse aliquip. Ullamco labore anim aliqua ipsum. Duis dolore minim magna minim enim nisi voluptate id. Minim cupidatat exercitation esse eiusmod nulla veniam do aliquip culpa. Nostrud labore do ea aliqua incididunt mollit incididunt ullamco et.",
  },
];
