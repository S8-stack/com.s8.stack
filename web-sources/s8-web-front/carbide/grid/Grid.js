
import { GridCard } from './GridCard.js';
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';
import { S8WebFront } from '/s8-web-front/S8WebFront.js';



/**
 * 
 */
S8WebFront.CSS_import('/s8-web-front/carbide/grid/Grid.css');

export class Grid extends NeObject {


    constructor(){
        super();
        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("grid-wrapper");

        this.coreNode = document.createElement("div");
        this.coreNode.classList.add("grid-core");
        this.wrapperNode.appendChild(this.coreNode);

        let _this = this;
        this.wrapperNode.addEventListener("click", function (event) {
            _this.S8_vertex.runVoid("on-click");
            event.stopPropagation();
        }, false);
    }

    S8_render(){ /* continuous rendering approach... */ }

    getEnvelope(){
        return this.wrapperNode;
    }
    

    /**
     * 
     * @param {GridCard[]} cards 
     */
    S8_set_cards(cards){

        /* clear wrapper node content */
       while(this.coreNode.hasChildNodes()){ this.coreNode.removeChild(this.coreNode.lastChild); }
       
       /* append cards */
       cards.forEach(card => this.coreNode.appendChild(card.getEnvelope()));
    }

    S8_dispose(){ /* continuous rendering approach... */ }
}