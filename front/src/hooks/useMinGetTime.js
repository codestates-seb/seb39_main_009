
const useMinGetTime =() =>{
    const minGetTime= (starttime, endtime) =>{
        // let dateElement = endtime;

        const setMinimum =
        (new Date(endtime).getTime() - new Date(starttime).getTime()) / 1000 / 60;
         

            if(setMinimum <= 0) {
                alert('현재 시간보다 이전의 날짜는 설정할 수 없습니다.');
                // return dateElement.value = '';
                return setMinimum;
                // console.log(setMinutes)
            }
        
    }
    return minGetTime;
}

export default useMinGetTime;