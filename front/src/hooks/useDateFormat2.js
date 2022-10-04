const useDateFormat2 = () => {
    const dateFormat2 = (fulldate) => {
      const putZero2 = (d) => {
        if (d.length === 1) {
          return `0${d}`;
        } else {
          return `${d}`;
        }
      };
  
      let year = String(new Date(fulldate).getFullYear());
      let month = putZero2(String(new Date(fulldate).getMonth()));
      let date = putZero2(String(new Date(fulldate).getDate()));
  
      return `${year}-${month}-${date}`;
    };
    return dateFormat2;
  };
  
  export default useDateFormat2;
  