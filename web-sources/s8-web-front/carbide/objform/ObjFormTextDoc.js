

import { NeObject } from '/s8-io-bohr-neon/NeObject.js';


/**
 * 
 */
export class ObjFormTextDoc extends NeObject {

    constructor() {
        super();

         /* <output> */
         this.node = document.createElement("div");
         this.node.classList.add("objform-doc-text");
         /* </output> */

    }

    getEnvelope(){
        return this.node;
    }


    /**
     * 
     * @param {string} value 
     */
    S8_set_text(value){
        this.node.innerHTML = value;
    }

    S8_render(){ /* no post processing */ }
    S8_dispose(){ /* continuous */ }
    
}
