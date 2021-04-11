import Option from "./Option";
import React, {useState, useEffect} from 'react';


function Question(props) {


    // set ID, used in child component
    const setAnswer = (answer) => {
        props.onSelect(props.id, answer);
    }


    return (
        <div className="question" >
            <b>{props.question}</b>
            <br/>
            {props.options.map((item, index) =>
                <Option id={index} option={item} selected={setAnswer}/>
            )}
        </div>
    );
}

export default Question;