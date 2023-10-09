import { ByteInflow } from "../bytes/ByteInflow";
import { ByteOutflow } from "../bytes/ByteOutflow";
import { bohr } from "../bohr/BohrContext";

import * as BohrProtocol from '../bohr/BohrProtocol'
import * as ByteEncoding from "../bytes/ByteEncoding";



class FakeBox {

    static BOHR_NAME = "FakeBox"

    constructor(){
        this.state = {
            hello : "ghtoi",
            ertyu : 1879789
        };
    }


    update(){

    }



}


class FakeLine {

	static BOHR_NAME = "FakeLine"

    constructor(){
        this.state = {
            hello : "ghtoi",
            ertyu : 1879789
        };
    }

    update(){
        
    }


}


export function test(){

    let type = FakeBox;

    let instance = new type();
    let instance2 = new FakeBox();
    
    console.log(instance+ " created");
    console.log(type.BOHR_NAME);

    bohr.appendType([ FakeBox, FakeLine ]);
    console.log("Hi!");
}


export function test_fire(){

    bohr.appendType([ FakeBox, FakeLine ]);

    let buffer = new ArrayBuffer(1024);
    let inflow = new ByteInflow(buffer);
    let outflow = new ByteOutflow(buffer);

    outflow.putUInt8(BohrProtocol.OPEN_DELTA);


    /* <FakeBox> */
    outflow.putUInt8(BohrProtocol.DEFINE_MODEL_AND_CREATE_INDEXED_NODE);
    outflow.putBohrHashCode("0000000012345678");
    outflow.putUInt16(19082);
    outflow.putL8StringASCII("FakeBox");
    
    // fields
    outflow.putUInt8(BohrProtocol.DEFINE_ENTRY_AND_SET_FIELD_VALUE);
    outflow.putUInt8(26);
    outflow.putL8StringASCII("velocity");
    outflow.putUInt8(ByteEncoding.FLOAT32);
    outflow.putFloat32(756.123);

    outflow.putUInt8(BohrProtocol.DEFINE_ENTRY_AND_SET_FIELD_VALUE);
    outflow.putUInt8(36);
    outflow.putL8StringASCII("a-factor");
    outflow.putUInt8(ByteEncoding.FLOAT32);
    outflow.putFloat32(182987.123);

    outflow.putUInt8(BohrProtocol.DEFINE_ENTRY_AND_SET_FIELD_VALUE);
    outflow.putUInt8(37);
    outflow.putL8StringASCII("line-1");
    outflow.putUInt8(ByteEncoding.BOHR_OBJECT);
    outflow.putUInt8(BohrProtocol.NODE_INDEX);
    outflow.putBohrHashCode("00000000123456ea");

    outflow.putUInt8(BohrProtocol.DEFINE_ENTRY_AND_SET_FIELD_VALUE);
    outflow.putUInt8(38);
    outflow.putL8StringASCII("line-2");
    outflow.putUInt8(ByteEncoding.BOHR_OBJECT);
    outflow.putUInt8(BohrProtocol.NODE_INDEX);
    outflow.putBohrHashCode("00000000123456eb");

    outflow.putUInt8(BohrProtocol.CLOSE_NODE);
    /* </FakeBox> */


    /* 00000000123456ea : <FakeLine> */
    outflow.putUInt8(BohrProtocol.DEFINE_MODEL_AND_CREATE_INDEXED_NODE);
    outflow.putBohrHashCode("00000000123456ea");
    outflow.putUInt16(19086);
    outflow.putL8StringASCII("FakeLine");
    
    // fields
    outflow.putUInt8(BohrProtocol.DEFINE_ENTRY_AND_SET_FIELD_VALUE);
    outflow.putUInt8(26);
    outflow.putL8StringASCII("velocity");
    outflow.putUInt8(ByteEncoding.FLOAT32);
    outflow.putFloat32(756.123);

    outflow.putUInt8(BohrProtocol.DEFINE_ENTRY_AND_SET_FIELD_VALUE);
    outflow.putUInt8(36);
    outflow.putL8StringASCII("pressure");
    outflow.putUInt8(ByteEncoding.FLOAT32);
    outflow.putFloat32(182987.123);

    outflow.putUInt8(BohrProtocol.CLOSE_NODE);
    /* </FakeLine> */

    /* 00000000123456eb : <FakeLine> */
    outflow.putUInt8(BohrProtocol.CREATE_INDEXED_NODE);
    outflow.putBohrHashCode("00000000123456eb");
    outflow.putUInt16(19086);
    
    // fields
    outflow.putUInt8(BohrProtocol.SET_FIELD_VALUE);
    outflow.putUInt8(26);
    outflow.putUInt8(ByteEncoding.FLOAT32);
    outflow.putFloat32(756.123);

    outflow.putUInt8(BohrProtocol.SET_FIELD_VALUE);
    outflow.putUInt8(36);
    outflow.putUInt8(ByteEncoding.FLOAT32);
    outflow.putFloat32(182987.123);

    outflow.putUInt8(BohrProtocol.CLOSE_NODE);
    /* </FakeLine> */

    outflow.putUInt8(BohrProtocol.CLOSE_DELTA);

    bohr.update(inflow);
    console.log("parsing done...");

}