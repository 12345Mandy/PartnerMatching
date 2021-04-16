function AnswerBox(props) {
    return (
        <div className="answerInput">
            <input type={'text'} placeholder="type an answer here" onChange={event => props.change(props.id, event.target.value)}/>
        </div>
    );
}

export default AnswerBox;