
import { S8 } from '/s8-io-bohr-atom/S8.js';
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';

/**
 * 
 */
S8.import_CSS('/s8-web-front/asw/Asw.css');


/**
 * 
 */
export class AswScreen extends NeObject {


    constructor() {
        super();
        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("asw-screen");

    }

    getEnvelope() {
        return this.wrapperNode;
    }


    S8_render() { /* continuous rendering approach... */ }


    /**
     * 
     * @param {*[]} elements 
     */
    S8_set_elements(elements) {
        elements.forEach(element => {
            if (element != null) {
                this.wrapperNode.appendChild(element.getEnvelope());
            }
        });
    }


    S8_set_backgroundColor(color){
        this.wrapperNode.style.backgroundColor = color;
    }


    S8_dispose() { /* no disposing to be done... */ }
}
