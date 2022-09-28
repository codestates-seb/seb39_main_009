const useDateFormat = () => {
  const dateFormat = (fulldate) => {
    const putZero = (d) => {
      if (d.length === 1) {
        return `0${d}`;
      } else {
        return `${d}`;
      }
    };

    let year = String(new Date(fulldate).getFullYear());
    let month = putZero(String(new Date(fulldate).getMonth()));
    let date = putZero(String(new Date(fulldate).getDate()));
    let hours = putZero(String(new Date(fulldate).getHours()));
    let minutes = putZero(String(new Date(fulldate).getMinutes()));

    return `${year}.${month}.${date} ${hours}:${minutes}`;
  };
  return dateFormat;
};

export default useDateFormat;
