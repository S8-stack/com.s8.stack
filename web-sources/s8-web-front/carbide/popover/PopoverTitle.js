import { Popover } from "./Popover.js";
import { NeObject } from "/s8-io-bohr-neon/NeObject.js";




export class PopoverTitle extends NeObject {



    /**
     * @type {Popover}
     */
    menu;

    
    constructor(){
        super();

        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("popover-element");

        this.titleNode = document.createElement("h1");
        this.wrapperNode.appendChild(this.titleNode);
    }


    /**
     * 
     * @returns 
     */
    getEnvelope(){
        return this.wrapperNode;
    }


    /**
     * 
     * @param {string} name 
     */
    S8_set_text(text){
        this.titleNode.innerHTML = text;
    }

    /**
     * 
     */
    S8_render(){
    }


    /**
     * 
     */
    S8_dispose(){
    }


}