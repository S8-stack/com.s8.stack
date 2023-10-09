

import { PrimtiveObjFormGetter } from '/s8-web-front/carbide/objform/PrimitiveObjFormGetter.js';

/**
 * 
 */
export class BooleanObjFormGetter extends PrimtiveObjFormGetter {

    constructor() {
        super(5);
    }

    createOutputNode() {

        /* <output> */
        this.outputNode = document.createElement("div");
        this.outputNode.classList.add("objform-output");
        this.fieldNode.appendChild(this.outputNode);
        this.outputNode.innerHTML = `<span>(value)</span>`;
        /* </output> */
    }


    S8_set_value(value){
        this.outputNode.innerHTML = `<span>${value?"TRUE":"FALSE"}</span>`;
    }
}

