

import { getColor, ObjFormElement } from '/s8-web-front/carbide/objform/ObjFormElement.js';



/**
 * 
 */
export class PrimtiveObjFormSetter extends ObjFormElement {

    constructor(markupColor) {
        super();
        // setup
        this.markupColor = markupColor;

        this.fieldNode = document.createElement("div");
        this.fieldNode.classList.add("objform-primitive-field");


        /* <ribbon> */
        this.ribbonNode = document.createElement("div");
        this.ribbonNode.classList.add("objform-markup");
        this.ribbonNode.classList.add("objform-markup-" + getColor(markupColor));
        this.fieldNode.appendChild(this.ribbonNode);
        this.markupColor = markupColor;
        /* </ribbon> */

        this.createNameNode();
        this.createInputNode();
        
        /* tooltip node */
        this.fieldNode.appendChild(this.createInfoNode());

         /* options node */
         this.fieldNode.appendChild(this.createPlusNode());
    }

    createNameNode() {
        this.fieldNameNode = document.createElement("div");
        this.fieldNameNode.classList.add("objform-field-name-primitive");
        this.fieldNameNode.innerHTML = "<span>field_name:</span>";
        this.fieldNode.appendChild(this.fieldNameNode);
    }


    getEnvelope() {
        return this.fieldNode;
    }

    setMarkupColor(colorCode) {
        let previous = "objform-markup-" + getColor(this.markupColor);
        this.markupColor = colorCode;
        this.fieldNameNode.classList.replace(previous, "objform-markup-" + getColor(this.markupColor));
    }

    S8_set_fieldName(name) {
        this.fieldNameNode.innerHTML = `<span>${name}</span>`;
    }


    S8_render(){ /* continuous rendering approach... */ }

    S8_dispose(){ /* nothing to dispose*/ }
}

