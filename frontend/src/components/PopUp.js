import React from 'react'

function PopUp(props) {

    const handleClick = () => {
        props.toggle(false);
    };

    return (
        <div className="modal">
            <div className="modal_content">
       <span className="close" onClick={()=>handleClick()}>
            &times;
          </span>
                {props.content}

            </div>
        </div>
    );

    //https://medium.com/@daniela.sandoval/creating-a-popup-window-using-js-and-react-4c4bd125da57
}


export default PopUp