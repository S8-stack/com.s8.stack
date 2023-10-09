
import { S8WebFront } from "/s8-web-front/S8WebFront.js";
import { NeObject } from "/s8-io-bohr-neon/NeObject.js";

import { WebSVG, WebSVG_ViewPort } from "/s8-web-front/websvg/WebSVG.js";
import { WebSVG_Element } from "/s8-web-front/websvg/WebSVG_Element.js";



S8WebFront.CSS_import("/s8-web-front/websvg/WebSVG.css");


/**
 * 
 */
export class WebSVG_Canvas extends NeObject {



    /**
     * @type{ WebSVG_Element[]}
     */
    elements = [];


    /**
     * @type {WebSVG_ViewPort}
     */
    viewPort = new WebSVG_ViewPort();

    /**
     * @type{boolean}
     */
    isRedrawingRequired = false;


    /**
     * @type{SVGElement}
     */
    canvasNode;

    constructor() {
        super();

        this.canvasNode = document.createElementNS("http://www.w3.org/2000/svg", 'svg');
        this.canvasNode.setAttribute("xmlns", "http://www.w3.org/2000/svg");
        //this.canvasNode.setAttributeNS("http://www.w3.org/2000/xmlns/", "xmlns:xlink", "http://www.w3.org/1999/xlink");
      
        this.canvasNode.classList.add("websvg-canvas");
        this.canvasNode.setAttribute("viewBox", `0 0 ${this.viewPort.width} ${this.viewPort.height}`);
    }


    getEnvelope(){
        return this.canvasNode;
    }


    /**
     * 
     * @param {WebSVG_Element[]} elements 
     */
    S8_set_elements(elements) {
        // clear canvas current nodes
        while(this.canvasNode.hasChildNodes()){ 
            this.canvasNode.removeChild(this.canvasNode.lastChild); }

        this.elements = elements;

        const nElements = this.elements.length;

        /* update bounding box of the view port */
      

        let element;
        for(let i = 0; i<nElements; i++){
            element = elements[i];

            // link
            element.canvas = this;

            // append node
            this.canvasNode.appendChild(element.SVG_node);
        }

        this.isRedrawingRequired = true;
    }

    /**
     * 
     */
    redraw() {
        if (this.elements != null) {
            const nElements = this.elements.length;

            /* update bounding box of the view port */
            this.viewPort.clearBoundingBox();
            for(let i = 0; i<nElements; i++){
                const element = this.elements[i];
                if(element.isBoundingBoxRelevant){
                    element.updateBoundingBox(this.viewPort);
                }
            }

            /* rescale */
            this.viewPort.rescale();

            /* redraw */
            for(let i = 0; i<nElements; i++){
                this.elements[i].redraw(this.viewPort);
            }
        }
    }


    S8_render() {
        if(this.isRedrawingRequired){
            this.redraw();
            this.isRedrawingRequired = false;
        }
    }


	S8_dispose(){  /* nothing to dispose */ }
}