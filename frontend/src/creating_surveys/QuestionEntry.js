import NewAnswerButton from "./NewAnswerButton";
import AnswerBox from "./AnswerBox";

function QuestionEntry(props) {

    const addAns = () => {
        props.addAnswer(props.id);
    }

    const setAns = (ansid, content) => {
        props.changeAnswer(props.id, ansid, content);
    }

    return (
        <div className="newQuestion">
            <input className="questionInput" placeholder="type a question here" type={'text'} onChange={event => props.change(props.id, event.target.value)}/>
            Importance (from 1-10):
            <input className="importance" type="number" min="1" max="10" onChange={event => props.changeImp(props.id, event.target.value)}/>
            {props.options.map((option, oid) =>
                <AnswerBox id={oid} change={setAns}/>)}
            <NewAnswerButton newAnswer={addAns}/>
        </div>
    );
}

export default QuestionEntry;