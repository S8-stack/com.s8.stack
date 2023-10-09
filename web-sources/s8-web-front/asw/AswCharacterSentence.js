
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';

/**
 * 
 */
export class AswCharacterSentence extends NeObject {

    /**
     * @type {string}
     */
    text;
   
    /**
     * @type {number}
     */
    attitudeIndex;


    /**
     * @type {number}
     */
    pause = 250;



 

    constructor() {
        super();
    }

    /**
     * 
     * @param {number} index 
     */
    S8_set_attitudeIndex(index) {
        this.attitudeIndex = index;
    }

    /**
     * 
     * @param {string} text 
     */
     S8_set_text(text) {
        this.text = text;
    }


     /**
     * 
     * @param {number} pause 
     */
      S8_set_pause(pause) {
        this.pause = pause;
    }

    S8_render() {  }

    S8_dispose() { /* no disposing to be done... */ }
}




