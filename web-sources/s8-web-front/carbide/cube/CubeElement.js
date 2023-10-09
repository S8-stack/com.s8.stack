
import { S8WebFront } from '/s8-web-front/S8WebFront.js';
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';



/**
 * 
 */
S8WebFront.CSS_import('/s8-web-front/carbide/cube/Cube.css');

export class CubeElement extends NeObject {


    constructor(){
        super();
    }

    S8_render(){ /* continuous rendering approach... */ }

    S8_set_zIndex(level){
        this.getEnvelope().style.zIndex = level;
    }

    S8_dispose(){ /* continuous rendering approach... */ }
}