import { Link } from "react-router-dom";

const Editreview =() =>{
 return (
    <div>
        <h2>상품은 만족하셨나요?</h2>
        <div>별점표시</div>
        <textarea></textarea>
        <Link to={'/parking/{id}/review'}><button>등록</button></Link>
    </div>
 )
}

export default Editreview;