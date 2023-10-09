

import { ObjFormElement } from '/s8-web-front/carbide/objform/ObjFormElement.js';


/**
 * 
 */
export class MethodObjFormLauncher extends ObjFormElement {

    constructor() {

        super();

        // setup
        this.markupColor = 0;
        this.isEnabled = false;

        this.fieldNode.classList.add("objform-method");
        this.fieldNode.classList.add("objform-method-disabled");
        
        this.arrowNode = document.createElement("div");
        this.arrowNode.classList.add("objform-icon-arrow-disabled");
        this.arrowNode.innerHTML = ` <svg height="10" width="20" viewBox="0 0 20 10">
            <polygon points="0,4.5 16,4.5 16,1 20,5 16,9 16,5.5 0,5.5"/>
            </svg>`;
        this.fieldNode.appendChild(this.arrowNode);

        /* <name> */
        this.nameNode = document.createElement("div");
        this.nameNode.classList.add("objform-field-name-primitive");
        this.nameNode.innerHTML = "<span>field_name:</span>";
        this.fieldNode.appendChild(this.nameNode);
        /* </name> */
    }


    getEnvelope() {
        return this.fieldNode;
    }

    S8_set_name(name) {
        this.nameNode.innerHTML = `<span>${name}</span>`;
    }

    enable(){
        if(!this.isEnabled){
            this.fieldNode.classList.replace("objform-method-disabled", "objform-method-enabled");
            this.arrowNode.classList.replace("objform-icon-arrow-disabled", "objform-icon-arrow-enabled");
            this.isEnabled = true;
        }
    }

    disable(){
        if(this.isEnabled){
            this.fieldNode.classList.replace("objform-method-enabled", "objform-method-disabled");
            this.arrowNode.classList.replace("objform-icon-arrow-enabled", "objform-icon-arrow-disabled");
            this.isEnabled = false;
        }
    }

    S8_render(){ /* continuous rendering approach... */ }

    S8_dispose(){ /* nothing to dispose*/ }
}

