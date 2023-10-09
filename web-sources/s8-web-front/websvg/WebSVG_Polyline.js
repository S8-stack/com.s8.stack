import { WebSVG_Element } from "/s8-web-front/websvg/WebSVG_Element.js";
import { WebSVG, WebSVG_ViewPort } from "/s8-web-front/websvg/WebSVG.js";


/**
 * 
 */
export class WebSVG_Polyline extends WebSVG_Element {



	/** @type{number[]} */
	coordinates;


    constructor(){
        super();

		/* <polyline points="100,100 150,25 150,75 200,0" fill="none" stroke="black" /> */
		this.SVG_node = document.createElementNS("http://www.w3.org/2000/svg", "polyline");
		this.setupStroke();
		
    }


	/**
	 * 
	 * @param {number[]} coordinates 
	 */
	S8_set_coordinates(coordinates){
		this.coordinates = coordinates;
		this.requestRedraw();
	}

	
	/**
	 * 
	 * @param {WebSVG_ViewPort} viewPort 
	 */
	redraw(viewPort){
		const nPoints = this.coordinates.length / 2;
		const points = new Array(nPoints);
		for(let i = 0; i<nPoints; i++){
			const x = viewPort.xTranform(this.coordinates[2*i+0]);
			const y = viewPort.yTranform(this.coordinates[2*i+1]);
			points[i] = `${x.toPrecision(6)},${y.toPrecision(6)}`;
		}
		this.SVG_node.setAttribute("points", points.join(' '));
	}


	/**
	 * 
	 * @param {WebSVG_ViewPort} box 
	 */
	updateBoundingBox(box){
		const n = this.coordinates.length;
		for(let i = 0; i<n; i+=2){
			box.updateBoundingBox(this.coordinates[i+0], this.coordinates[i+1]);
		}
	}
}