function DisplayPair(props) {
    if (props.matches.length === 1) {
        return (
            <div>
                <div><strong>{props.user1} has been paired with: {props.matches[0]}</strong></div>
            </div>
        );
    } else {
        return (
            <div>
                <div><strong>{props.user1} has been paired with: {props.matches[0]} and {props.matches[1]}</strong></div>
            </div>
        );
    }
}

export default DisplayPair;