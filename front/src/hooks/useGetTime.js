const useGetTime = () => {
  const getTime = (starttime, endtime) => {
    const setMinutes =
      (new Date(endtime).getTime() - new Date(starttime).getTime()) / 1000 / 60;

    if (setMinutes % 60 === 0) {
      let hours = setMinutes / 60;
      return `${hours}시간`;
    } else {
      let hours = Math.floor(setMinutes / 60);
      let minutes = setMinutes % 60;
      return `${hours}시간 ${minutes}분`;
    }
  };
  return getTime;
};

export default useGetTime;
