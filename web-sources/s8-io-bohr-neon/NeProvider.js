
import { ByteOutflow } from "/s8-io-bytes/ByteOutflow.js";

import { BOHR_Keywords, BOHR_Types } from "/s8-io-bohr-atom/BOHR_Protocol.js";

import { NeObjectTypeHandler } from "./NeObjectTypeHandler.js";

export class NeProvider {



    /**
     * @type {NeObjectTypeHandler}
     */
    type;

    /**
     * @type {string}
     */
    name;

    /**
     * @type {number}
     */
    code;

    /**
     * @type {boolean}
     */
    isPublished = false;

    
    constructor(name, code) {
        this.name = name;
        this.code = code;
    }


    /**
     * 
     * @param {ByteOutflow} outflow 
     */
    publish_DECLARE_PROVIDER(outflow){
        if(!this.isPublished){
            
            // declare method
            outflow.putUInt8(BOHR_Keywords.DECLARE_PROVIDER);

            // type code
            outflow.putUInt7x(this.type.code);

            // provider name
            outflow.putStringUTF8(this.name);

            // provider code
            outflow.putUInt8(this.code);
            
            this.isPublished = true;
        }
    }

}



export class RawNeProvider extends NeProvider {

    /**
     * 
     * @param {string} name 
     * @param {number} code 
     */
    constructor(name, code) {
        super(name, code);
    }

    /**
     * 
     * @param {NeVertex} vertex 
     * @param { * } value 
     */
    setValue(vertex, value) {
        let object = vertex.object;
        this.setter.call(object, value);
    }
}
