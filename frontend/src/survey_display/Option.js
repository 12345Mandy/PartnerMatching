function Option(props) {
    // onClick={() => props.toUpdate(props.id)}
    let classToUse = "option";
    if(props.id === props.highlighted) {
        classToUse = "optionSelected";
    }

    // for accessibility!
    const selectWithEnterOrSpace = (key) => {
        if(key.code === "Enter" ||  key.code === "Space") {
            props.onSelected(props.id);
        }
    }

    return (
        <div tabindex="0" className={classToUse} onClick={() => props.onSelected(props.id)} onKeyPress={selectWithEnterOrSpace}>
            {props.option}
            <br/>
        </div>
    );
}

export default Option;