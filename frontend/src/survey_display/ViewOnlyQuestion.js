import React, {useState} from "react";
import Option from "./Option";
import ViewOnlyOption from "./ViewOnlyOption";

function ViewOnlyQuestion(props) {
    const userResponse = props.options[props.userData.responses[props.questionNumber - 1]];
    const partnerResponse = props.options[props.partnerData.responses[props.questionNumber - 1]];

    return (
        <div className="question" >
            <div className="theQuestion">
                {props.question}
            </div>
            {props.options.map((item, index) =>
                <ViewOnlyOption id={index} option={item}/>
            )}

            <div>
                You picked: {userResponse}
            </div>

            <div>
                {props.partnerData.name} picked: {partnerResponse}
            </div>
        </div>
    );
}

export default ViewOnlyQuestion;