
import { S8 } from '/s8-io-bohr-atom/S8.js';
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';



/**
 * 
 */
S8.import_CSS('/s8-web-front/carbide/cube/Cube.css');

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