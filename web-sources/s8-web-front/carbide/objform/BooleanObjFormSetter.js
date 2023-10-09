

import { S8 } from '/s8-io-bohr-atom/S8.js';
import { PrimtiveObjFormSetter } from '/s8-web-front/carbide/objform/PrimtiveObjFormSetter.js';

export class BooleanObjFormSetter extends PrimtiveObjFormSetter {

    constructor() {
        super(5);
    }

    createInputNode() {

        /* <input> */
        let inputWrapperNode = document.createElement("div");
        inputWrapperNode.classList.add("objform-input");
        this.inputNode = document.createElement("input");
        this.inputNode.setAttribute("type", "checkbox");
        inputWrapperNode.appendChild(this.inputNode);
        this.fieldNode.appendChild(inputWrapperNode);
        
        const _this = this;
        this.inputNode.addEventListener("blur", function(event){
            S8.branch.loseFocus();
            _this.sendValue();
            event.stopPropagation();
        });
        /* </input> */


    }

    sendValue(){
        let value = this.inputNode.value;
        this.S8_vertex.runInt32("on-value-changed", value);
    }
}

