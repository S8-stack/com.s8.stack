
import { S8 } from '../../s8/S8.js';

/**
 * Flow base feature
 */
export class Button {

	constructor(name, className, onClick){

		// import component syle
		S8.import_CSS("/air/button/Button.css");

		// root node
		this.node = document.createElement("button");
		this.node.innerHTML = name;
		this.node.className = className;
		this.onClick = onClick;

		/* <listeners> */
		let _this = this;

		// input
		this.clickListener = function(event){
			if(_this.isEnabled){
				_this.onClick(event);	
			}
		}
		this.node.addEventListener("click", this.clickListener, false);
		/* </listeners> */

		this.isEnabled = true;
	}

	getNode(){
		return this.node;
	}

	enable(){
		this.isEnabled = true;
		this.node.removeAttribute("disabled");
		return this; // fluent mode
	}

	disable() {
		this.isEnabled = false;
		this.node.setAttribute("disabled", "");
		return this; // fluent mode
	}

	dispose(){
		this.node.removeEventListener("click", this.clickListener);
	}
}
