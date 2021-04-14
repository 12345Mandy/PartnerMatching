function Option(props) {
    // onClick={() => props.toUpdate(props.id)}
    let classToUse = "option";
    if(props.id === props.highlighted) {
        classToUse = "optionSelected";
    }

    return (
        <div className={classToUse} onClick={() => props.onSelected(props.id)}>
            {props.option}
            <br/>
        </div>
    );
}

export default Option;