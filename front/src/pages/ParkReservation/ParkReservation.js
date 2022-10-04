// import { useState } from "react"
import { useEffect } from "react";

const ParkReservation =() =>{

    // const [data,setData] = useState();
    // const [data2,setData2] = useState();
    

      useEffect(()=>{
      fetch(`${process.env.REACT_APP_BASE_URL}/api/parking/1/reservation`,{
        headers: {
          authorization: localStorage.getItem("authorization"),
        },
      })
      .then(response =>{
          return response.json()
      })
      .then(res => {
        //   setData(res)
          console.log(res)
          console.log('리스트 불러오기 성공')
      })
      .catch(err=>{
          console.log(err);
          console.log('리스트 불러오기 실패');
      })
  }, [])

//   useEffect(()=>{
//     fetch('http://localhost:3002/validNum')
//     .then(response =>{
//         return response.json()
//     })
//     .then(res => {
//         setData2(res)
//         console.log(res)
//         console.log('리스트 불러오기 성공')
//     })
//     .catch(err=>{
//         console.log(err);
//         console.log('리스트 불러오기 실패');
//     })
// }, [])




    return ( 
    <div>
        <label>주차장사진</label>
        {/* <div>{data&&data}</div> */}
        <div>
            <label>자리선택</label>
            {/* <select className="ul_style1">
                {data2&&data2.map(review => (
                  <option key={review.number} value={review.number}>{review.number}</option>
                 ))}
            </select> */}
        </div>
    </div>
    )
}

export default ParkReservation;