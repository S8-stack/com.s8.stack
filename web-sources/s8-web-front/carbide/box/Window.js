



export class Window {

    /**
     * 
     */
    constructor() {
        /* <settings> */
        this.width = 360; // px
        this.zIndex = 32;
        /* </settings> */

        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("window");
        this.wrapperNode.style.width = `${this.width}px`;
        this.wrapperNode.style.zIndex = this.zIndex;

        /* <header> */
        this.headerNode = document.createElement("div");
        this.headerNode.classList.add("window-header");

        this.headerGroup0Node = document.createElement("div");
        this.headerGroup0Node.classList.add("window-header-title");

        this.titleNode = document.createElement("span");
        this.titleNode.innerHTML = "Window Title here";
        this.headerGroup0Node.appendChild(this.titleNode);
        this.headerNode.appendChild(this.headerGroup0Node);


        this.handleClose = document.createElement("div");
        this.handleClose.classList.add("window-handle-close");
        this.handleClose.innerHTML = `<svg version="1.1" xmlns="http://www.w3.org/2000/svg" 
        height="20px" width="20px" viewBox="0 0 512 512">
        <path d="M256,31C131.7,31,31,131.7,31,256s100.7,225,225,225s225-100.7,225-225S380.3,31,256,31z M256,438.8
C155,438.8,73.2,357,73.2,256S155,73.2,256,73.2S438.8,155,438.8,256S357,438.8,256,438.8z" />
        <path d="M326.3,143.5L256,213.8l-70.3-70.3l-42.2,42.2l70.3,70.3l-70.3,70.3l42.2,42.2l70.3-70.3l70.3,70.3l42.2-42.2L298.2,256
l70.3-70.3L326.3,143.5z" />
    </svg>`;

        this.wrapperNode.appendChild(this.headerNode);
        /* </header> */

        /* <body> */
        this.bodyNode = document.createElement("div");
        this.bodyNode.classList.add("window-body");

        this.wrapperNode.appendChild(this.bodyNode);
        /* </body> */


        /* add draggable */
        let _this = this;
        this.state = 0; /* 0: idle, 1: moving */
        this.headerNode.onmousedown = function(e){
            e = e || window.event;
            e.preventDefault();
            // get the mouse cursor position at startup:
            pos3 = e.clientX;
            pos4 = e.clientY;
            document.onmouseup = closeDragElement;
            // call a function whenever the cursor moves:
            document.onmousemove = elementDrag;
        }
    }


    /**
     * 
     * @returns 
     */
    getEnvelope(){
        return this.wrapperNode;
    }

    



    // Make the DIV element draggable:
dragElement(document.getElementById("mydiv"));

function dragElement(elmnt) {
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  if (document.getElementById(elmnt.id + "header")) {
    // if present, the header is where you move the DIV from:
    document.getElementById(elmnt.id + "header").onmousedown = dragMouseDown;
  } else {
    // otherwise, move the DIV from anywhere inside the DIV:
    elmnt.onmousedown = dragMouseDown;
  }

  function dragMouseDown(e) {
   
  }

  function elementDrag(e) {
    e = e || window.event;
    e.preventDefault();
    // calculate the new cursor position:
    pos1 = pos3 - e.clientX;
    pos2 = pos4 - e.clientY;
    pos3 = e.clientX;
    pos4 = e.clientY;
    // set the element's new position:
    elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
    elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
  }

  function closeDragElement() {
    // stop moving when mouse button is released:
    document.onmouseup = null;
    document.onmousemove = null;
  }
}

}

