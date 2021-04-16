function SurveyInfoBox(props) {
    return (
        <input type={'text'} placeholder={props.placeholder} onChange={event => props.change(event.target.value)}/>
    );
}

export default SurveyInfoBox