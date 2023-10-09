
import React from 'react';


// the handler
class BohrPopover {

	constructor(target, direction, style) {
		// save parameters
		this.target = target;
		this.direction = direction;
		this.style = style;
	}

	attach(mounted) {
		this.mounted = mounted;

	}


	build() {
		return <AirPopover style="test"></AirPopover>
	}
}

// the renderable
class Popover extends AirComponent {

	constructor(props) {
		super(props);

		// initialize state
		this.state = {
			direction: "top-left",
			style: "standard",
			target: null,
			content: []
		}
	}

	render() {
		return (<div className="hidden">
			<div className={this.computePopoverNodeClass()}>

			</div>
		</div>);
	}

	computePopoverNodeClass = () => {
		let className = "popover-" + this.state.style;
		switch (this.state.direction) {

			case "top-left":
				className += " popover-top-left";
				break;

			case "top-center":
				className += " popover-top-center";
				break;

			case "top-right":
				className += "cpopover-top-right";
				break;

			case "right-top":
				className += " popover-right-top";
				break;

			case "right-center":
				className += "popover-right-center";
				break;

			case "right-bottom":
				className += "popover-right-bottom";
				break;

			case "bottom-right":
				className += "popover-bottom-right";
				break;

			case "bottom-center":
				className += "popover-bottom-center";
				break;

			case "bottom-left":
				className += "popover-bottom-left";
				break;

			case "left-bottom":
				className += "popover-left-bottom";
				break;

			case "left-center":
				className += "popover-left-center";
				break;

			case "left-top":
				className += "popover-left-top";
				break;
		}
		return className;
	}


	build() {

		// create wrapper node
		this.wrapperNode = document.createElement("div");
		this.wrapperNode.className = "hidden"; //"popover-error-bottom";

		// create popover node
		this.popoverNode = document.createElement("div");
		this.wrapperNode.appendChild(this.popoverNode);

		// recompute z-index

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

		// append
		ui.popoversLandingZone.appendChild(this.wrapperNode);

		this.place();

		// components
		if (this.components) {
			for (let component of this.components) {
				this.popoverNode.appendChild(component.getNode());
			}
		}
	}

	getNode() {
		return this.wrapperNode;
	}

	place() {

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

		switch (this.direction) {

			case "top-left":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; bottom:" + (windowHeight - yTargetTop) + "px;";
				break;

			case "top-center":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; bottom:" + (windowHeight - yTargetTop) + "px;";
				break;

			case "top-right":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetRight) + "px; bottom:" + (windowHeight - yTargetTop) + "px;";
				break;

			case "right-top":
				this.wrapperNode.style = "left:" + xTargetRight + "px; top:" + yTargetTop + "px;";
				break;

			case "right-center":
				this.wrapperNode.style = "left:" + xTargetRight + "px; top:" + yTargetTop + "px;";
				break;

			case "right-bottom":
				this.wrapperNode.style = "left:" + xTargetRight + "px; bottom:" + (windowHeight - yTargetBottom) + "px;";
				break;

			case "bottom-right":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetRight) + "px; top:" + yTargetBottom + "px;";
				break;

			case "bottom-center":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; top:" + yTargetBottom + "px;";
				break;

			case "bottom-left":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; top:" + yTargetBottom + "px;";
				break;

			case "left-bottom":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetLeft) + "px; bottom:" + (windowHeight - yTargetBottom) + "px;";
				break;

			case "left-center":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetLeft) + "px; top:" + yTargetTop + "px;";
				break;

			case "left-top":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetLeft) + "px; top:" + yTargetTop + "px;";
				break;
		}
	}

	show() {
		this.place();
		this.wrapperNode.className = "vertical-popover-wrapper";
	}

	hide() {
		this.wrapperNode.className = "hidden";
	}


	dispose() {
		if (this.components) {
			for (let component of this.components) {
				component.dispose();
			}
		}
		ui.popoversLandingZone.removeChild(this.wrapperNode);
	}
}


export function popover2(target, direction, style0, components0, style1, components1) {
	let popover = new UI_Popover2(target, direction, style0, components0, style1, components1);
	popover.build();
	/*popover.show();*/
	return popover;
}


//define 
class UI_Popover2 {

	constructor(target, direction, style0, components0, style1, components1) {

		// save parameters
		this.target = target;
		this.direction = direction;
		this.style0 = style0;
		this.components0 = components0;
		this.style1 = style1;
		this.components1 = components1;
	}


	build() {

		// create wrapper node
		this.wrapperNode = document.createElement("div");

		// create popover node (zone 0)
		this.popoverNode0 = document.createElement("div");
		this.wrapperNode.appendChild(this.popoverNode0);

		// create popover node (zone 1)
		this.popoverNode1 = document.createElement("div");
		this.wrapperNode.appendChild(this.popoverNode1);

		// recompute z-index

		this.wrapperNodeClassName = "hidden";

		switch (this.direction) {

			case "top-left":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-bottom-none popover-head-top";
				this.popoverNode1.className = "popover-top-left popover-body-bottom";
				break;

			case "top-center":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-bottom-none popover-head-top";
				this.popoverNode1.className = "popover-top-center popover-body-bottom";
				break;

			case "top-right":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-bottom-none popover-head-top";
				this.popoverNode1.className = "popover-top-right popover-body-bottom";
				break;

			case "right-top":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-right-top popover-head-top";
				this.popoverNode1.className = "popover-right-none popover-body-bottom";
				break;

			case "right-center":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-right-center popover-head-top";
				this.popoverNode1.className = "popover-right-none popover-body-bottom";
				break;

			case "right-bottom":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-right-none popover-head-top";
				this.popoverNode1.className = "popover-right-bottom popover-body-bottom";
				break;

			case "bottom-right":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-bottom-right popover-head-top";
				this.popoverNode1.className = "popover-top-none popover-body-bottom";
				break;

			case "bottom-center":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-bottom-center popover-head-top";
				this.popoverNode1.className = "popover-top-none popover-body-bottom";
				break;

			case "bottom-left":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-bottom-left popover-head-top";
				this.popoverNode1.className = "popover-top-none popover-body-bottom";
				break;

			case "left-bottom":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-left-none popover-head-top";
				this.popoverNode1.className = "popover-left-bottom popover-body-bottom";
				break;

			case "left-center":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-left-center popover-head-top";
				this.popoverNode1.className = "popover-left-none popover-body-bottom";
				break;

			case "left-top":
				this.wrapperNodeClassName = "vertical-popover-wrapper";
				this.popoverNode0.className = "popover-left-top popover-head-top";
				this.popoverNode1.className = "popover-left-none popover-body-bottom";
				break;
		}

		this.place();

		this.wrapperNode.className = "hidden";
		this.popoverNode0.className += " popover-" + this.style0;
		this.popoverNode1.className += " popover-" + this.style1;

		// append
		ui.popoversLandingZone.appendChild(this.wrapperNode);

		// components
		if (this.components0) {
			for (let component0 of this.components0) {
				this.popoverNode0.appendChild(component0.getNode());
			}
		}
		if (this.components1) {
			for (let component1 of this.components1) {
				this.popoverNode1.appendChild(component1.getNode());
			}
		}
	}


	place() {
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


		switch (this.direction) {

			case "top-left":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; bottom:" + (windowHeight - yTargetTop) + "px;";
				break;

			case "top-center":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; bottom:" + (windowHeight - yTargetTop) + "px;";
				break;

			case "top-right":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetRight) + "px; bottom:" + (windowHeight - yTargetTop) + "px;";
				break;

			case "right-top":
				this.wrapperNode.style = "left:" + xTargetRight + "px; top:" + yTargetTop + "px;";
				break;

			case "right-center":
				this.wrapperNode.style = "left:" + xTargetRight + "px; top:" + yTargetTop + "px;";
				break;

			case "right-bottom":
				this.wrapperNode.style = "left:" + xTargetRight + "px; bottom:" + (windowHeight - yTargetBottom) + "px;";
				break;

			case "bottom-right":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetRight) + "px; top:" + yTargetBottom + "px;";
				break;

			case "bottom-center":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; top:" + yTargetBottom + "px;";
				break;

			case "bottom-left":
				this.wrapperNode.style = "left:" + xTargetLeft + "px; top:" + yTargetBottom + "px;";
				break;

			case "left-bottom":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetLeft) + "px; bottom:" + (windowHeight - yTargetBottom) + "px;";
				break;

			case "left-center":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetLeft) + "px; top:" + yTargetTop + "px;";
				break;

			case "left-top":
				this.wrapperNode.style = "right:" + (windowWidth - xTargetLeft) + "px; top:" + yTargetTop + "px;";
				break;
		}
	}

	show() {
		this.place();
		this.wrapperNode.className = this.wrapperNodeClassName;
	}

	hide() {
		this.wrapperNode.className = "hidden";
	}

	/* replace components 0 */
	setComponents0(components) {
		if (this.components0) {
			for (let component of this.components0) {
				component.dispose();
			}
		}
		this.components0 = components;
		if (this.components0) {
			for (let component of this.components0) {
				this.popoverNode0.appendChild(component.build());
			}
		}
	}

	/* replace components 1 */
	setComponents1(components) {
		if (this.components1) {
			for (let component of this.components1) {
				component.dispose();
			}
		}
		this.components1 = components;
		if (this.components1) {
			for (let component of this.components1) {
				this.popoverNode1.appendChild(component.build());
			}
		}
	}

	setStyle0(style) {
		this.popoverNode0.classList.remove("popover-" + this.style0);
		this.style0 = style;
		this.popoverNode0.classList.add("popover-" + this.style0);
	}

	setStyle1(style) {
		this.popoverNode1.classList.remove("popover-" + this.style1);
		this.style1 = style;
		this.popoverNode1.classList.add("popover-" + this.style1);
	}

	dispose() {
		ui.popoversLandingZone.removeChild(this.wrapperNode);
		if (this.components0) {
			for (let component0 of this.components0) {
				component0.dispose();
			}
		}
		if (this.components1) {
			for (let component1 of this.components1) {
				component1.dispose();
			}
		}
	}
}

export default { BohrPopover, Popover }
