import "./layout.css"; // 레이아웃 CSS 입니다. Don't touch !
import "./App.css"; // 비어있으니 레이아웃 외 CSS 추가변경 원하시면 이곳에서 수정해주세요 !
import HealthCheck from "./test/HealthCheck";
import Header from "./component/Header/Header";

function App() {
  return (
    <div className="container">
      <div className="side">프로젝트 소개 구역</div>
      <div className="main_container">
        <div className="header">
          <Header />
        </div>
        <div className="main">
          {/* ↓ 아래 main div 안에 페이지 추가해주시면 됩니다. */}
          <HealthCheck />
        </div>
      </div>
    </div>
  );
}
export default App;
