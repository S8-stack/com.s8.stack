


import { NeObject } from '/s8-io-bohr-neon/NeObject.js';
import { S8WebFront } from '/s8-web-front/S8WebFront.js';



/**
 * 
 */
S8WebFront.CSS_import('/s8-web-front/carbide/basics/Basics.css');

/**
 * 
 */
export class H1HtmlElement extends NeObject {

    /**
     * 
     */
    constructor() {
        super();
        this.node = document.createElement("h1");
        this.node.classList.add("basics");
    }

    S8_render() { /* continuous rendering approach... */ }

    getEnvelope() {
        return this.node;
    }


    /**
     * 
     * @param {Popover} popoverBox 
     */
    S8_set_content(elements) {

        /* clear child nodes */
        while(this.node.hasChildNodes()){ this.node.removeChild(this.node.lastChild); }

        /* elements */
        elements.forEach(element => this.node.appendChild(element.getEnvelope()));
    }


    S8_dispose() { /* continuous rendering approach... */ }
}