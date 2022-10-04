// import { useEffect } from "react";
// import { useState } from "react";
import Loading from "../../component/Loading/Loading";
import "./ParkSearch.css"
import RandomImg from "../../assets/parkinglot/RandomImg";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { useEffect } from "react";

const ClickSearch = (props) =>{
  const navigate = useNavigate();
  const { pkId } = useParams();

  
  const clickpage = ()=>{
    navigate(`/parking/${pkId}`)
  }




  const truePartnership =props.lala.data.filter((el)=>el.partnership===true)
  const falsePartnership =props.lala.data.filter((el)=>el.partnership===false)

  useEffect(()=>{
    console.log(props.lala.data[0].partnership)
    console.log(truePartnership)
    console.log(falsePartnership)

  })



    return (
        <div>
          {props.loading && <Loading />}
          <select>
            <option value="인기순" selected="selected">인기순</option>
          </select>
          <div className="parksearch">
          <h3>제휴 주차장</h3>
          <div>{truePartnership && truePartnership.map(data => (
                          <div>
                            <br/>
                              <div key={data.parkingId} 
                                   onClick={clickpage}
                                  className="searchpage">
                                   <RandomImg size={"reviewImg_size"} />
                            <div>
                             <div>주차장이름 : {data.name}</div>
                             <div>주차장 주소 : {data.address}</div>
                           </div>
                          </div>
                          </div>
                  ))}
           </div>
           <h3>일반 주차장</h3>
          <div>{falsePartnership && falsePartnership.map(data => (
                          <div>
                            <br/>
                              <div key={data.parkingId} 
                                   onClick={clickpage}
                                  className="searchpage">
                                   <RandomImg size={"reviewImg_size"} />
                            <div>
                             <div>주차장이름 : {data.name}</div>
                             <div>주차장 주소 : {data.address}</div>
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