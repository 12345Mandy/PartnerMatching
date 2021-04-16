function GeneratedLink(props) {
    if(props.madeLink) {
        return(<div className="getOutputLink">
            your survey: <a href={props.link}>{props.link}</a>
            <button onClick={navigator.clipboard.writeText(props.link)}>copy link</button>
        </div>);
    } else {
        return null;
    }


}

export default GeneratedLink;