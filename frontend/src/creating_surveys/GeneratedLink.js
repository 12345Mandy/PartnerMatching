import {Link} from "react-router-dom";

function GeneratedLink(props) {
    if(props.madeLink) {
        return(<div className="getOutputLink">
            <Link to={props.shortlink}>{props.link}</Link>
            <button onClick={navigator.clipboard.writeText(props.link)}>copy link</button>
        </div>);
    } else {
        return null;
    }


}

export default GeneratedLink;