import Option from "./Option";
import React, {useState, useEffect} from 'react';


function Question(props) {

    const[selectedOption, setSelectedOption] = useState(-1);

    // set ID, used in child component
    const setAnswer = (answer) => {
        setSelectedOption(answer);
        props.onSelect(props.id, answer);
    }


    return (
        <div className="question" >
            <div className="theQuestion">
            {props.question}
            </div>
            {props.options.map((item, index) =>
                <Option id={index} option={item} onSelected={setAnswer} highlighted={selectedOption}/>
            )}
        </div>
    );
}

export default Question;