
import { CubeElement } from '/s8-web-front/carbide/cube/CubeElement.js';
import { S8WebFront } from '../../S8WebFront.js';


S8WebFront.CSS_import("/s8-web-front/carbide/dock/Dock.css");

/**
 * 
 */
export class ObjFormWrapper extends CubeElement {

    /**
     * 
     */
    constructor(){
        super();

        this.node = document.createElement("div");
        this.node.classList.add("objform-box");

        this.node.addEventListener("scroll", function(event){ event.stopPropagation(); });
    }


    S8_render(){ /* continuous rendering approach... */ }

    /**
     * 
     * @param {*} root 
     */
    S8_set_root(root) {
       this.node.appendChild(root.getEnvelope());
    }


    getEnvelope() {
        return this.node;
    }

    S8_dispose(){ /* TODO */ }
}
