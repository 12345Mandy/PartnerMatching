import React from 'react'

function PopUp(props) {

    const handleClick = () => {
      props.toggle(false);
  };
//<button className="close" onClick={()=>handleClick()}>times </button>
  return (
   <div className="modal">
     <div className="modal_content">
       <span className="close" onClick={()=>handleClick()}>
            &times;
          </span>
     <p>
      I'm A Pop Up!!! akdfa;ldfjdlfjalfjd;lfkj;lfdkjs;ldkfj
      I'm A Pop Up!!! akdfa;ldfjdlfjalfjd;lfkj;lfdkjs;ldkfj
      I'm A Pop Up!!! akdfa;ldfjdlfjalfjd;lfkj;lfdkjs;ldkfj
     </p>
    </div>
   </div>
  );
    // return (
    //   <div className="modal">
    //     <div className="modal_content">
    //       <span className="close" onClick={handleClick}>
    //         &times;
    //       </span>
    //       <form>
    //         <h3>Register!</h3>
    //         <label>
    //           Name:
    //           <input type="text" name="name" />
    //         </label>
    //         <br />
    //         <input type="submit" />
    //       </form>
    //     </div>
    //   </div>
    // );
    //https://medium.com/@daniela.sandoval/creating-a-popup-window-using-js-and-react-4c4bd125da57
  }


export default PopUp
