import Loading from "../../component/Loading/Loading";
import "./ParkSearch.css";
import RandomImg from "../../assets/parkinglot/RandomImg";
import { useNavigate } from "react-router-dom";

const ClickSearch = (props) => {
  const navigate = useNavigate();


  const truePartnership =props.lala.data.filter((el)=>el.partnership===true)
  const falsePartnership =props.lala.data.filter((el)=>el.partnership===false)



    return (
        <div>
          {props.loading && <Loading />}
          <select className="searchselect">
            <option value="인기순">인기순</option>
          </select>
          <div className="parksearch2">
          <h2>검색 결과</h2>
          
          {truePartnership.length===0?"": <h3>제휴 주차장</h3>}
          <div>{truePartnership && truePartnership.map(data => (
                          <div className="gopakring" key={data.parkingId} >
                              <div 
                                   onClick={()=>{
                                    navigate(`/parking/${data.parkingId}`)
                                   }}
                                  className="searchpage">
                                   <RandomImg size={"pakinglot2_size"} />
                            <div  className="parking_data">
                             <div>{data.name}</div>
                             <div>{data.address}</div>
                           </div>
                          </div>
                          </div>
                  ))}
           </div>
           <h3>일반 주차장</h3>
          <div className="gopakring">{falsePartnership && falsePartnership.map(data => (
                          <div className="gopakring" key={data.parkingId} >
                              <div 
                                   onClick={()=>{
                                    navigate(`/parking/${data.parkingId}`)
                                   }}
                                  className="searchpage">
                                   <RandomImg size={"pakinglot2_size"} />
                            <div className="parking_data">
                             <div>{data.name}</div>
                             <div>{data.address}</div>
                           </div>
                          </div>
                          </div>
                      ))}
           </div>
           </div>
        </div>
    )
}

export default ClickSearch;