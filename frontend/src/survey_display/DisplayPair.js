function DisplayPair(props) {
    return (
        <div>
            <div><strong>{props.user1} has been paired with: {props.user2}</strong></div>
        </div>
    );
}

export default DisplayPair;