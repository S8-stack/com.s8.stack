
import { S8WebFront } from "/s8-web-front/S8WebFront.js";
import { NeObject } from "/s8-io-bohr-neon/NeObject.js";

import { WebSVG } from "/s8-web-front/websvg/WebSVG.js";
import { WebSVG_Canvas } from "/s8-web-front/websvg/WebSVG_Canvas.js";



S8WebFront.CSS_import("/s8-web-front/websvg/WebSVG.css");


/**
 * 
 */
export class WebSVG_Element extends NeObject {

	/**
	 * @type{WebSVG_Canvas}
	 */
	canvas = null;


	/**
	 * @type{boolean}
	 */
	isBoundingBoxRelevant;


	/**
	 * @type{SVGElement}
	 */
	SVG_node;




	/**
	 * @type{String} the css suffix for the solid style
	 */
	strokeSolidityStyle = WebSVG.STROKE_SOLIDITY_DEFAULT;



	constructor() {
		super();
	}



	setupStroke() {
		this.SVG_node.classList.add("websvg-element");
		this.SVG_node.setAttribute("stroke", "black");
		this.SVG_node.setAttribute("stroke-width", "1");
	}


	/**
	 * 
	 * @param {WebSVG_Canvas} canvas 
	 */
	link(canvas) {
		this.canvas = canvas;
	}


	/**
	 * 
	 * @returns 
	 */
	getEnvelope() {
		return this.SVG_node;
	}


	S8_set_isBoundingBoxRelevant(state) {
		this.isBoundingBoxRelevant = state;
	}


	/**
	 * 
	 * @param solidity
	 */
	S8_set_strokeSolidity(code) {
		let style = WebSVG.getStrokeSolidity(code);
		if(style != null){
			this.SVG_node.setAttribute("stroke-dasharray", style);
		}
		else{
			this.SVG_node.removeAttribute("stroke-dasharray");
		}
	}


	/**
	 * 
	 * @param {number} color
	 */
	S8_set_strokeColor(code) {
		let color = WebSVG.getStrokeColor(code);
		this.SVG_node.setAttribute("stroke", color);
	}



	/**
	 * 
	 * @param {number} value 
	 */
	S8_set_strokeThickness(value) {
		this.SVG_node.setAttribute("stroke-width", value.toPrecision(3));
	}



	requestRedraw() {
		if (this.canvas != null) {
			// notify required redrawing
			this.canvas.isRedrawingRequired = true;
		}
	}


	S8_render() { /* no post-processing */ }

	S8_dispose() {  /* nothing to dispose */ }
}