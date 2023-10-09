
import { S8 } from '/s8-io-bohr-atom/S8.js';

S8.import_CSS("/s8-web-front/carbide/popover/Popover.css");


// the renderable
export class Popover {

	constructor() {

		// initialize state
		this.isVisible = false;
		this.node = document.createElement("div");
		this.node.classList.add("popover-hidden");

		this.direction = "top-left";
		this.style = "default";

		this.containerNode = document.createElement("div");

		/* ClassList Order MATTERS!!! */
		this.node.classList.add("popover-"+this.direction, "popover-"+this.style);

		this.node.appendChild(this.containerNode);

		this.target = null;
		this.content = [];
	}


	/**
	 * 
	 * @param {*} direction 
	 */
	setDirection(direction){
		/* ClassList Order MATTERS!!! */
		this.node.classList.replace("popover-"+this.direction, "popover-"+direction);
		this.direction = direction;
	}


	/**
	 * 
	 * @param {*} style 
	 */
	setStyle(style){
		/* ClassList Order MATTERS!!! */
		this.node.classList.replace("popover-"+this.style, "popover-"+style);
		this.style = style;
	}

	/**
	 * 
	 * @param {*} isVisible 
	 */
	setVisibility(isVisible){
		this.node.classList.remove(this.isVisible ? "popover-visible" : "popover-hidden");
		this.isVisible = isVisible;
		this.node.classList.add(this.isVisible ? "popover-visible" : "popover-hidden");
	}


	setObjects(elements){
		S8.removeChildren(this.containerNode);
		elements.forEach(element => { this.containerNode.appendChild(element.getEnvelope()); });
	}

	setContent(content){
		S8.removeChildren(this.containerNode);
		this.containerNode.appendChild(content);
	}



	getEnvelope(){
		return this.node;
	}

	computeWrapperPosition = () => {

		let target = this.state.target;

		if(target==null){
			return "left: 256px; bottom: 256px;";
		}

		/* choose where to land: check available space around target */
		let boundingBox = this.target.getBoundingClientRect();
		//let lzHeight = ui.popoversLandingZone.offsetHeight;

		// main anchors
		let windowWidth = window.innerWidth;
		let windowHeight = window.innerHeight;

		let xTargetLeft = window.scrollX + boundingBox.left;
		let xTargetRight = window.scrollX + boundingBox.right;
		let yTargetTop = window.scrollY + boundingBox.top;
		let yTargetBottom = window.scrollY + boundingBox.bottom;

		return {left: xTargetLeft+"px", bottom:(windowHeight - yTargetTop)+"px" };

		/*
		switch (this.direction) {

			case "top-left":
				return {left: xTargetLeft+"px", bottom:(windowHeight - yTargetTop)+"px" };
				
			case "top-center":
				return {left: xTargetLeft+"px", bottom:(windowHeight - yTargetTop)+"px" };

			case "top-right":
				return {right: (windowWidth - xTargetRight) + "px", bottom:(windowHeight - yTargetTop)+"px" };

			case "right-top":
				return {left: xTargetRight+"px", top:yTargetTop+"px"};

			case "right-center":
				return {left: xTargetRight + "px", top:yTargetTop +"px"};

			case "right-bottom":
				return {left: xTargetRight + "px", bottom:(windowHeight - yTargetBottom) + "px"};

			case "bottom-right":
				return "right:" + (windowWidth - xTargetRight) + "px; top:" + yTargetBottom + "px;";

			case "bottom-center":
				return "left:" + xTargetLeft + "px; top:" + yTargetBottom + "px;";

			case "bottom-left":
				return "left:" + xTargetLeft + "px; top:" + yTargetBottom + "px;";

			case "left-bottom":
				return "right:" + (windowWidth - xTargetLeft) + "px; bottom:" + (windowHeight - yTargetBottom) + "px;";

			case "left-center":
				return "right:" + (windowWidth - xTargetLeft) + "px; top:" + yTargetTop + "px;";

			case "left-top":
				return "right:" + (windowWidth - xTargetLeft) + "px; top:" + yTargetTop + "px;";
		}
		*/
	}
}
