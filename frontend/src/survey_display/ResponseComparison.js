function ResponseComparison(props) {
    console.log(props.userData);
    console.log(props.partnerData);
    console.log(props.options);
    console.log(props.questionNumber);

    const userResponse = props.options[props.userData.responses[props.questionNumber - 1]];
    const partnerResponse = props.options[props.partnerData.responses[props.questionNumber - 1]];
    console.log(userResponse);
    return (
      <div className="comparisons">
          <div className = "answers">
              You picked {userResponse}
          </div>

          <div className = "answers">
              {props.userData.name} picked {partnerResponse}
          </div>
      </div>
    );
}


export default ResponseComparison;