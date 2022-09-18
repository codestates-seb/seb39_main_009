import React from "react";
import "./Terms.css";
import { AiOutlineLeft } from "react-icons/ai";
import { useNavigate } from "react-router-dom";

const PsInfoTerm = () => {
  const navigate = useNavigate();
  return (
    <div className="term_container">
      <div className="term_header">
        <AiOutlineLeft
          size={24}
          onClick={() => {
            navigate(-1);
          }}
        />
        <p>개인정보 처리방침</p>
      </div>
      <div className="term_main">
        <div className="main_title">
          <h3>주차GO 개인 정보 수집 및 이용약관</h3>
        </div>
        <div className="main_content">
          Reprehenderit sunt laboris elit nostrud sit eiusmod ea nostrud
          voluptate fugiat est nisi. Ut et exercitation magna quis ut in aliquip
          ullamco. Nostrud magna qui amet do officia est commodo laborum nisi
          aliqua tempor ipsum anim. Qui in proident ullamco consectetur velit
          ullamco enim. Officia aute Lorem in anim proident anim qui incididunt
          qui cillum culpa est ad. Adipisicing esse aliqua mollit consequat
          nulla est. Pariatur voluptate in voluptate non et amet quis. Ut irure
          cillum id non dolore eiusmod tempor consectetur qui commodo laboris et
          cupidatat dolor. Eu consectetur magna sunt ullamco aliqua voluptate
          occaecat. Non aute reprehenderit in non do anim magna mollit. Irure
          sunt cillum voluptate deserunt eiusmod incididunt. Minim dolore et ad
          magna Lorem proident magna. Minim consectetur eiusmod irure
          reprehenderit duis ea. Laborum exercitation laboris voluptate amet ad
          anim magna consectetur labore dolor dolore. Velit cupidatat amet in
          magna elit ad nulla excepteur est. Enim nisi nisi proident anim est
          qui. Minim Lorem Lorem tempor quis adipisicing sint fugiat officia
          irure ullamco ad velit. Fugiat magna culpa ut ea occaecat minim cillum
          ullamco. Minim incididunt minim adipisicing esse sit eiusmod aute amet
          labore commodo tempor ad ad. Voluptate velit adipisicing velit
          voluptate labore nulla officia deserunt. Amet quis ut id cupidatat
          aliqua amet. Elit ex nostrud cillum id irure laborum aute irure sunt.
          Fugiat reprehenderit veniam id proident proident consectetur labore
          occaecat. Consequat aliqua labore ullamco incididunt mollit anim
          aliquip dolor qui qui. Enim id sit velit ullamco reprehenderit labore
          ullamco labore. Anim ipsum elit minim nisi et Lorem sint cillum id.
          Esse deserunt sint ea elit irure et commodo quis id ex commodo. Mollit
          minim reprehenderit in ad ullamco proident irure sint aliquip officia
          eu. Aliquip commodo eiusmod dolor enim do deserunt et proident velit
          do sunt irure amet. Non do reprehenderit ut qui et Lorem minim cillum
          non. Minim incididunt velit duis veniam anim ut laboris enim qui
          mollit dolor mollit. Pariatur veniam proident enim magna do
          consectetur consequat dolore veniam laboris id reprehenderit ut
          voluptate. Irure ipsum deserunt esse ipsum. Laborum eu proident in
          consequat mollit est ex nulla sunt amet dolore sit voluptate qui.
          Fugiat ex non commodo laboris anim proident pariatur commodo est
          incididunt dolore consectetur. Elit consectetur velit aute pariatur
          anim sit. Minim quis ipsum consectetur nulla voluptate sunt elit
          dolore laboris proident deserunt quis Lorem. In sit Lorem aliquip
          minim proident mollit tempor aliqua. Mollit minim ad nulla sunt do
          fugiat. Est in officia do magna. Reprehenderit eiusmod cupidatat dolor
          cupidatat consequat. Ut dolor adipisicing est veniam aliqua nostrud eu
          fugiat culpa fugiat quis nulla. Minim cillum labore aliqua voluptate
          cillum nisi voluptate ex minim nulla aliqua reprehenderit. Consequat
          nostrud sunt cupidatat officia elit ipsum proident est dolore sit
          voluptate duis ullamco anim. Mollit adipisicing quis sint excepteur
          ipsum aliquip consectetur elit. Tempor dolore ipsum anim proident.
        </div>
      </div>
    </div>
  );
};

export default PsInfoTerm;
