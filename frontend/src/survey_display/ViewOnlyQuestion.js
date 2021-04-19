import React, {useState} from "react";
import Option from "./Option";
import ViewOnlyOption from "./ViewOnlyOption";

function ViewOnlyQuestion(props) {
    const userResponse = props.options[props.userData.responses[props.questionNumber]];
    const partnerResponse = [];
    for (let i = 0; i < props.partnerData.length; i++) {
        partnerResponse.push(props.options[props.partnerData[i].responses[props.questionNumber]]);
    }

    if (props.partnerData.length === 0) {
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
                    {props.partnerData[0].name} picked: {partnerResponse[0]}
                </div>
            </div>
        );
    } else {
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
                    {props.partnerData[0].name} picked: {partnerResponse[0]}
                </div>

                <div>
                    {props.partnerData[1].name} picked: {partnerResponse[1]}
                </div>
            </div>
        );
    }


}

export default ViewOnlyQuestion;