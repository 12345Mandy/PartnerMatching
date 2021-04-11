function Option(props) {
    // onClick={() => props.toUpdate(props.id)}
    return (
        <div className="option" onClick={() => props.selected(props.id)}>
            {props.option}
            <br/>
        </div>
    );
}

export default Option;