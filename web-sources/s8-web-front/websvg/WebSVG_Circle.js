
import { WebSVG_Element } from "/s8-web-front/websvg/WebSVG_Element.js";
import { WebSVG, WebSVG_ViewPort } from "/s8-web-front/websvg/WebSVG.js";




/**
 * 
 */
export class WebSVG_Circle extends WebSVG_Element {


	/** @type{number} */
	xCenter = 0.0;

	/** @type{number} */
	yCenter = 0.0;

	/** @type{number} */
	radius = 1.0;
    
    constructor(){
        super();

		/*   <circle cx="50" cy="50" r="50" /> */
		this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "circle");
		this.setupStroke();
		
    }


	/**
	 * 
	 * @param {number[]} coordinates 
	 */
	S8_set_center(coordinates){
		this.xCenter = coordinates[0];
		this.yCenter = coordinates[1];
		this.requestRedraw();
	}

	/**
	 * 
	 * @param {number} radius 
	 */
	S8_set_radius(radius){
		this.radius = radius;
		this.requestRedraw();
	}

	
	/**
	 * 
	 * @param {WebSVG_ViewPort} viewPort 
	 */
	redraw(viewPort){
		this.SVG_node.setAttribute("cx", viewPort.xTranform(this.xCenter).toPrecision(6));
		this.SVG_node.setAttribute("cy", viewPort.yTranform(this.yCenter).toPrecision(6));
		this.SVG_node.setAttribute("r", viewPort.sTranform(this.radius).toPrecision(6));
	}


	/**
	 * 
	 * @param {WebSVG_ViewPort} box 
	 */
	updateBoundingBox(box){
		box.updateBoundingBox(this.xCenter - this.radius, this.yCenter - this.radius);
		box.updateBoundingBox(this.xCenter + this.radius, this.yCenter + this.radius);
	}
}