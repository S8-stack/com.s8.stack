
import { getColor, ObjFormElement } from "/s8-web-front/carbide/objform/ObjFormElement.js";

/**
 * 
 */
export class PrimtiveObjFormGetter extends ObjFormElement {



    constructor(markupColor) {

        super();

        // setup
        this.markupColor = markupColor;

        this.fieldNode.classList.add("objform-primitive-field");


        /* <ribbon> */
        this.ribbonNode = document.createElement("div");
        this.ribbonNode.classList.add("objform-markup");
        this.ribbonNode.classList.add("objform-markup-" + getColor(markupColor));
        this.fieldNode.appendChild(this.ribbonNode);
        this.markupColor = markupColor;
        /* </ribbon> */

        /* <name> */
        this.fieldNameNode = document.createElement("div");
        this.fieldNameNode.classList.add("objform-field-name-primitive");
        this.fieldNameNode.innerHTML = "<span>field_name:</span>";
        this.fieldNode.appendChild(this.fieldNameNode);
        /* </name> */


        this.createOutputNode();
        
        /* tooltip node */
        this.fieldNode.appendChild(this.createInfoNode());

        /* options node */
        this.fieldNode.appendChild(this.createPlusNode());
    }


    



    setMarkupColor(colorCode) {
        let previous = "objform-markup-" + getColor(this.markupColor);
        this.markupColor = colorCode;
        this.ribbonNode.classList.replace(previous, "objform-markup-" + getColor(this.markupColor));
    }


    S8_render(){ /* continuous rendering approach... */ }

    S8_set_fieldName(name) {
        this.fieldNameNode.innerHTML = `<span>${name}</span>`;
    }

    S8_dispose(){ /* nothing to dispose*/ }
}
