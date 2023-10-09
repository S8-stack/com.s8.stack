

import { WebSVG_Element } from "/s8-web-front/websvg/WebSVG_Element.js";
import { WebSVG, WebSVG_ViewPort } from "/s8-web-front/websvg/WebSVG.js";




/**
 * 
 */
export class WebSVG_Line extends WebSVG_Element {

	/** @type{number} */
	x1;

	/** @type{number} */
	y1;

	/** @type{number} */
	x2;

	/** @type{number} */
	y2;

    
    constructor(){
        super();

		/* <line x1="0" y1="80" x2="100" y2="20" stroke="black" /> */
		this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "line");
		this.setupStroke();

    }


	/**
	 * 
	 * @param {number[]} coordinates 
	 */
	S8_set_coordinates(coordinates){
		this.x1 = coordinates[0];
		this.y1 = coordinates[1];
		this.x2 = coordinates[2];
		this.y2 = coordinates[3];
		this.requestRedraw();
	}


	/**
	 * 
	 * @param {WebSVG_ViewPort} viewPort 
	 */
	redraw(viewPort){
		this.SVG_node.setAttribute("x1", viewPort.xTranform(this.x1).toPrecision(6));
		this.SVG_node.setAttribute("y1", viewPort.yTranform(this.y1).toPrecision(6));

		this.SVG_node.setAttribute("x2", viewPort.xTranform(this.x2).toPrecision(6));
		this.SVG_node.setAttribute("y2", viewPort.yTranform(this.y2).toPrecision(6));
	}




	/**
	 * 
	 * @param {WebSVG_ViewPort} box 
	 */
	updateBoundingBox(box){
		box.updateBoundingBox(this.x1, this.y1);
		box.updateBoundingBox(this.y1, this.y2);
	}
}