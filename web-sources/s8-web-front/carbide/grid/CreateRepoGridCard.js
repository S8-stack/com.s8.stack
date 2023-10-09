
import { GridCard } from "/s8-web-front/carbide/grid/GridCard.js";




export class CreateRepoGridCard extends GridCard {

    constructor(){
        super();

        this.containerNode = document.createElement("div");
        this.containerNode.classList.add("grid-card-create");
        this.cardNode.appendChild(this.containerNode);


        this.headerNode = document.createElement("div");
        this.headerNode.classList.add("grid-card-create-header");
        this.headerNode.innerHTML = "+";
        this.containerNode.appendChild(this.headerNode);


        this.bodyNode = document.createElement("div");
        this.bodyNode.classList.add("grid-card-create-body");
        this.bodyNode.innerHTML = "Create a new project...";
        this.containerNode.appendChild(this.bodyNode);


        let _this = this;
        this.isPopoverAttached = false;
        this.containerNode.addEventListener("click", function (event) {
            _this.S8_vertex.runVoid("on-click");
            event.stopPropagation();
        }, false);
    }


    /**
     * 
     * @param {string} cards
     */
    S8_set_isSelected(isSelected) {
        if (isSelected) {
            this.containerNode.setAttribute("selected", '');
        }
        else {
            this.containerNode.removeAttribute("selected");
        }
    }

    S8_render() { /* continuous rendering approach... */ }

    S8_dispose() { /* continuous rendering approach... */ }
    
}