const Listreview =(props) =>{

    const nickname = props.review.data.nickname;
    const body = props.review.data.body;
    return(
        <div>
                <div>{nickname}</div>
                <div>{body}</div>
        </div>
    )
}

export default Listreview;