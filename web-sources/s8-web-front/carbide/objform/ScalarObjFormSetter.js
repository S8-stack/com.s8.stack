

import { S8 } from '/s8-io-bohr-atom/S8.js';
import { S8_NumberFormats } from '/s8-web-front/carbide/S8NumberFormat.js';
import { PrimtiveObjFormSetter } from '/s8-web-front/carbide/objform/PrimtiveObjFormSetter.js';


export class ScalarObjFormSetter extends PrimtiveObjFormSetter {


    /**
     * 
     */
    value = "(unset)";

    /**
     * @type{Intl.NumberFormat}
     */
    format = S8_NumberFormats[0x22];


    constructor() {
        super(2);
    }

    createInputNode() {

        /* <input> */
        let inputWrapperNode = document.createElement("div");
        inputWrapperNode.classList.add("objform-input");
        this.inputNode = document.createElement("input");
        this.inputNode.setAttribute("type", "text");
        inputWrapperNode.appendChild(this.inputNode);
        this.fieldNode.appendChild(inputWrapperNode);

        const _this = this;
        this.inputNode.addEventListener("blur", function(event){
            S8.branch.loseFocus();
            _this.sendValue();
            event.stopPropagation();
        });
        /* </input> */

        /* <unit> */
        let unitWrapperNode = document.createElement("div");
        unitWrapperNode.classList.add("objform-unit");
        this.unitNode = document.createElement("span");
        this.unitNode.innerHTML = "(unit)";
        unitWrapperNode.appendChild(this.unitNode);
        this.fieldNode.appendChild(unitWrapperNode);
        /* </unit> */
    }


    sendValue(){
        let value = parseFloat(this.inputNode.value);
        this.S8_vertex.runFloat32("on-value-changed", value);
    }

    setUnit(abbreviation){
        this.unitNode.innerHTML = abbreviation;
    }


    S8_set_unit(abbreviation){
        this.setUnit(abbreviation);
    }


    /**
     * 
     * @param {number} value 
     */
    S8_set_value(value){
        this.inputNode.value = this.format.format(value);
    }

    S8_set_format(code){
        this.format = S8_NumberFormats[code];
    }

}
