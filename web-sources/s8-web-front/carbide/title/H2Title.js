
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';
import { S8WebFront } from '/s8-web-front/S8WebFront.js';


/**
 * basics
 */
S8WebFront.CSS_import('/s8-web-front/carbide/title/Title.css');


export class H2Title extends NeObject {

    /**
     * 
     */
    constructor() {
        super();
        this.node = document.createElement("h2");
        this.node.classList.add("title");
    }

    S8_render() { /* continuous rendering approach... */ }

    getEnvelope() {
        return this.node;
    }


    /**
     * 
     * @param {Popover} popoverBox 
     */
    S8_set_text(text) {
        this.node.innerHTML = text;
    }


    S8_dispose() { /* continuous rendering approach... */ }
}