
class Overlay extends Container {

	constructor(params) {
		super("div", "modal-background-overlay");

	}


}


class ModalBox {

	constructor(params) {
		super();

		// create root node
		this.wrapperNode = document.createElement("div");

		// create background overlay
		this.overlayNode = document.createElement("div");
		this.overlayNode.id = "modal-background-overlay";
		this.wrapperNode.appendChild(this.overlayNode);

		// container
		this.node = document.createElement("div");
		this.node.className = "modal-box";
		this.wrapperNode.appendChild(this.node);

		// close handler
		this.closeHandleNode = document.createElement("div");
		this.closeHandleNode.className = "modal-box-close";

		this.closeIcon = new Icon({ shape: 'cross_red_circle', width: 24, height: 24 });
		this.closeHandleNode.appendChild(this.closeIcon.getNode());
		this.node.appendChild(this.closeHandleNode);

		// click on exit
		let _this = this;
		this.closeListener = function (event) {
			_this.close(true);
		}
		this.closeHandleNode.addEventListener("click", this.closeListener, false);

		// click outside
		this.outsideListener = function (event) {
			_this.close(true);
		}
		this.overlayNode.addEventListener("click", this.outsideListener, false);
	}





	// setup subcomponents
	for (let component of components) {
		this.node.appendChild(component.getNode());
	}

	ui.modalBoxZone.appendChild(this.modalBoxNode);
},

close: function(isSkipped) {
	this.dispose();
	ui.modalBoxZone.removeChild(this.modalBoxNode);
	if (isSkipped) {
		this.onSkipped();
	}
},

dispose: function() {
	this.closeHandleNode.removeEventListener("click", this.closeListener);
	this.overlayNode.removeEventListener("click", this.outsideListener);
	for (let component of this.components) {
		component.dispose();
	}
}
	};

let modalBox = new ModalBox(components, onClose);
modalBox.build();
return modalBox;
}

