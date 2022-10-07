import "./Modal.css";
import React from "react";

const Modal = ({ modal, setModal }) => {
  const toggleModal = () => {
    setModal(!modal);
  };

  return (
    <>
      {modal && (
        <div className="modal">
          <div className="overlay" onClick={toggleModal}></div>
          <div className="modal-content">
            <h2>Hello Modal</h2>
            <p>
              Est laboris et consectetur amet duis aliqua do sint Lorem. Nisi
              est proident velit mollit do velit. Do ea enim sint aliquip cillum
              consectetur eu ex id qui et Lorem.
            </p>
            <button className="close-modal" onClick={toggleModal}>
              Close
            </button>
          </div>
        </div>
      )}
    </>
  );
};

export default Modal;
