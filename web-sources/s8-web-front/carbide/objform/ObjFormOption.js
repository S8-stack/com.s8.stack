

import { S8 } from '/s8-io-bohr-atom/S8.js';

import { S8WebFront } from '/s8-web-front/S8WebFront.js';

import { NeObject } from '/s8-io-bohr-neon/NeObject.js';

S8WebFront.CSS_import("/s8-web-front/carbide/objform/ObjFormOptions.css");


/**
 * 
 */
export class ObjFormOption extends NeObject {


    constructor() {

        super();

        // default setup
        this.iconColorCode = 4;

        this.wrapperNode = document.createElement("div");

        // field node already created by super
        this.wrapperNode.classList.add("objform-option");


        /* <icon> */
        this.iconNode = document.createElement("div");
        this.iconNode.classList.add("objform-option-icon");
        this.iconNode.innerHTML = ` <svg 
        width="16px" height="16px" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
            <path clip-rule="evenodd" d="M2 6V6.29266C2 7.72154 2.4863 9.10788 3.37892 10.2236L8 16L12.6211 10.2236C13.5137 9.10788 14 7.72154 14 6.29266V6C14 2.68629 11.3137 0 8 0C4.68629 0 2 2.68629 2 6ZM8 8C9.10457 8 10 7.10457 10 6C10 4.89543 9.10457 4 8 4C6.89543 4 6 4.89543 6 6C6 7.10457 6.89543 8 8 8Z"
            fill-rule="evenodd" />
        </svg>`;
        this.wrapperNode.appendChild(this.iconNode);
        /* </icon> */

        /* <name> */
        //<div class=><span>Folder_00:</span></div>
        let nameWrapperNode = document.createElement("div");
        nameWrapperNode.classList.add("objform-option-text");

        this.nameNode = document.createElement("span");
        this.nameNode.innerHTML = "${field_name}:";
        nameWrapperNode.appendChild(this.nameNode);
        this.wrapperNode.appendChild(nameWrapperNode);
        /* </name> */



        const _this = this;
        this.wrapperNode.addEventListener("click", function (event) {
            S8.branch.loseFocus();
            _this.onClick();
            event.stopPropagation();
        }, false);
    }


    onClick(){
        this.S8_vertex.runVoid("on-click");
    }

    getEnvelope(){
        return this.wrapperNode;
    }


    S8_set_iconShapeByCode(code) {
        S8WebFront.SVG_insertByCode(this.iconNode, code, 16, 16);
    }

    S8_set_iconShape(name) {
        S8WebFront.SVG_insertByName(this.iconNode, name, 16, 16);
    }

    S8_set_name(name) {
        this.nameNode.innerHTML = name;
    }





    S8_render() { /* continuous rendering approach... */ }

    S8_dispose() { /* nothing to dispose*/ }

    S8_unfocus() { /* stable when unfocussing : do nothing */ }
}

