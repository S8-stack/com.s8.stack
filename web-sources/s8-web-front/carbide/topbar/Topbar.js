
import { S8WebFront } from "/s8-web-front/S8WebFront.js";
import { NeObject } from "/s8-io-bohr-neon/NeObject.js";
import { TopbarElement } from "/s8-web-front/carbide/topbar/TopbarElement.js";




/**
 * 
 */
S8WebFront.CSS_import('/s8-web-front/carbide/topbar/Topbar.css');


/**
 * 
 */
export class Topbar extends NeObject {


    /**
     * @type {TopbarElement[]}
     */
    elements;


    /**
     * 
     */
    selectionIndex = 1;

    
    /**
     * 
     */
    constructor(){
        super();
        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("topbar-wrapper");
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
     * @param {TopbarElement[]} elements 
     */
    S8_set_elements(elements){
        let n = elements.length;
        for(let i=0; i<n; i++){
            let element = elements[i];

            element.bar = this;
            element.index = i;
            element.navbar = this;
            this.wrapperNode.appendChild(element.getEnvelope());
        }
        this.elements = elements;
    }

    /**
     * 
     */
    deselectAll(){
        if(this.elements){ this.elements.forEach(element => element.deselect()); }
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