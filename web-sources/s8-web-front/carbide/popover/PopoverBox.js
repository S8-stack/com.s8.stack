
import { S8WebFront } from "/s8-web-front/S8WebFront.js";



S8WebFront.CSS_import("/s8-web-front/carbide/popover/Popover.css");


export class PopoverBox {


    /**
     * @type {HTMLDivElement}
     */
    parentNode = null;

    /**
     * @type {HTMLDivElement}
     */
    node;



    /**
     * @type {boolean}
     */
    isDisplayed;


    /**
     * 
     * @param {HTMLDivElement} parentNode 
     */
    constructor() {


        this.node = document.createElement("div");
        this.node.classList.add("popover");

        // visibility
        this.isDisplayed = false;
        this.node.classList.add("popover-hidden");

        // theme
        this.theme = "light";
        this.node.classList.add("popover-" + this.theme);

        // direction
        this.direction = "left-top";
        this.node.classList.add("popover-" + this.direction);

    }

    /**
     * 
     * @param {*} parentNode 
     */
    attach(parentNode) {
        this.parentNode = parentNode;
        this.parentNode.appendChild(this.node);
        this.arrange();
    }


    /**
     * 
     * @param {*} elements 
     */
    clearContent() {

        /* clear child */
        while (this.node.lastChild) { this.node.removeChild(this.node.lastChild); }
    }


    hide() {
        if (this.isDisplayed) {
            this.node.classList.replace("popover-visible", "popover-hidden");
            this.isDisplayed = false;
        }
    }


    show() {
        if (!this.isDisplayed) {
            this.node.classList.replace("popover-hidden", "popover-visible");
            this.isDisplayed = true;
        }
    }


    /**
     * 
     * @param {*} direction 
     */
    setTheme(theme) {
        /* ClassList Order MATTERS!!! because theme define colors for direction */
        this.node.classList.replace("popover-" + this.theme, "popover-" + theme);
        this.theme = theme;
    }


    /**
     * 
     * @param {*} direction 
     */
    setDirection(direction) {
        /* ClassList Order MATTERS!!! because theme define colors for direction */
        this.node.classList.replace("popover-" + this.direction, "popover-" + direction);
        this.direction = direction;

        this.arrange();
    }


    arrange() {
        /* choose where to land: check available space around target */
        if (this.parentNode != null) {
            let boundingBox = this.parentNode.getBoundingClientRect();

            let parentHeight = boundingBox.height;
            let parentWidth = boundingBox.width;


            switch (this.direction) {

                /* <top> */
                case "top-left":
                    this.node.style.top = (parentHeight).toString() + "px";
                    this.node.style.left = (parentWidth * 0.5).toString() + "px";
                    break;

                case "top":
                    this.node.style.top = (parentHeight).toString() + "px";
                    this.node.style.left = (parentWidth * 0.5).toString() + "px";
                    break;

                case "top-right":
                    this.node.style.top = (parentHeight).toString() + "px";
                    this.node.style.left = (parentWidth * 0.5).toString() + "px";
                    break;

                /* </top> */

                /* <right> */
                case "right-top":
                    this.node.style.top = (parentHeight * 0.5).toString() + "px";
                    this.node.style.left = "0 px";
                    break;

                case "right":
                    this.node.style.top = (parentHeight * 0.5).toString() + "px";
                    this.node.style.left = "0 px";
                    break;

                case "right-bottom":
                    this.node.style.top = (parentHeight * 0.5).toString() + "px";
                    this.node.style.left = "0 px";
                    break;
                /* </right> */


                /* <bottom> */
                case "bottom-left":
                    this.node.style.top = "0 px";
                    this.node.style.left = (parentWidth * 0.5).toString() + "px";
                    break;

                case "bottom":
                    this.node.style.top = "0 px";
                    this.node.style.left = (parentWidth * 0.5).toString() + "px";
                    break;

                case "bottom-right":
                    this.node.style.top = "0 px";
                    this.node.style.left = (parentWidth * 0.5).toString() + "px";
                    break;
                /* </bottom> */


                /* <left> */
                case "left-top":
                    this.node.style.top = (parentHeight * 0.5).toString() + "px";
                    this.node.style.left = (parentWidth).toString() + "px";
                    break;

                case "left":
                    this.node.style.top = (parentHeight * 0.5).toString() + "px";
                    this.node.style.left = (parentWidth).toString() + "px";
                    break;

                case "left-bottom":
                    this.node.style.top = (parentHeight * 0.5).toString() + "px";
                    this.node.style.left = (parentWidth).toString() + "px";
                    break;
                /* </left> */

                default: this.node.style.transform = "translate(0px, 0px);";
            }
        }
    }



    /**
     * 
     */
    detach() {
        if (this.parentNode) {
            this.parentNode.removeChild(this.node);
            this.parentNode = null;
        }
    }
}