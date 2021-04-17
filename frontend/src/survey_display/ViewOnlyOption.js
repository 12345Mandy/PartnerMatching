function ViewOnlyOption(props) {
    // onClick={() => props.toUpdate(props.id)}
    let classToUse = "option";


    return (
        <div tabIndex="0" className={classToUse}>
            {props.option}
            <br/>
        </div>
    );
}

export default ViewOnlyOption;