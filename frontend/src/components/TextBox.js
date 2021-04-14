import React from 'react'

function TextBox(props) {
    return (
        <div>
            <label className="textbox" htmlFor={props.label + ": "}>{props.label + ": "}</label>
            {props.focus ? (
                <input

                    type={props.type}
                    autoFocus
                    label={props.label}
                    required
                    value={props.value}
                    onChange={(e) => props.change(e.target.value)}
                />
            ) : (
                <input
                    className="inputBox"
                    type={props.type}
                    label={props.type}
                    required
                    value={props.value}
                    onChange={(e) => props.change(e.target.value)}
                />
            )}
        </div>
    )
}

export default TextBox