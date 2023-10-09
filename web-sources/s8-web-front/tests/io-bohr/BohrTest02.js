import { ByteInflow } from "../bytes/ByteInflow";
import { ByteOutflow } from "../bytes/ByteOutflow";

import { bohr } from "../bohr/BohrContext";
import * as BohrProtocol from '../bohr/BohrProtocol'
import * as ByteEncoding from "../bytes/ByteEncoding";


// managed types
import { Wrapper } from "../wrapper/Wrapper";
import { Bar } from "../bar/Bar";
import { NavTab, Nav } from "../nav/Nav";


export function bohrTest02(){

    bohr.appendType([ Wrapper, Bar, Nav, NavTab ]);

    let buffer = new ArrayBuffer(1024);
    let inflow = new ByteInflow(buffer);
    let outflow = new ByteOutflow(buffer);

    outflow.putUInt8(BohrProtocol.OPEN_DELTA);


    /* <Wrapper> */
    outflow.putUInt8(BohrProtocol.DEFINE_TYPE_AND_CREATE_INDEXED_NODE);
    outflow.putBohrHashCode("0000000000000223"); // hashcode
    outflow.putUInt16(19082); // type code
    outflow.putL8StringASCII("Wrapper"); // type name
    
    // fields
    outflow.putUInt8(BohrProtocol.DEFINE_FIELD_AND_SET_FIELD_VALUE);
    outflow.putUInt8(37); // field code
    outflow.putL8StringASCII("elements"); // field name
    outflow.putUInt8(ByteEncoding.ARRAY_UINT8);
    outflow.putUInt8(2);
    outflow.putUInt8(ByteEncoding.BOHR_OBJECT);
    outflow.putUInt8(BohrProtocol.REFERENCE_INDEXED_NODE);
    outflow.putBohrHashCode("0000000000000012");
    outflow.putUInt8(BohrProtocol.REFERENCE_INDEXED_NODE);
    outflow.putBohrHashCode("0000000000000013");

    outflow.putUInt8(BohrProtocol.CLOSE_NODE);
    /* </Wrapper> */


    /* <Bar> */
    outflow.putUInt8(BohrProtocol.DEFINE_TYPE_AND_CREATE_INDEXED_NODE);
    outflow.putBohrHashCode("0000000000000012");
    outflow.putUInt16(19083);
    outflow.putL8StringASCII("Bar");

    // fields
    outflow.putUInt8(BohrProtocol.DEFINE_FIELD_AND_SET_FIELD_VALUE);
    outflow.putUInt8(26);
    outflow.putL8StringASCII("username");
    outflow.putUInt8(ByteEncoding.L32_STRING_UTF8);
    outflow.putL32StringUTF8("Gonzague de la Martnière");

    outflow.putUInt8(BohrProtocol.DEFINE_FIELD_AND_SET_FIELD_VALUE);
    outflow.putUInt8(36);
    outflow.putL8StringASCII("active-folder");
    outflow.putUInt8(ByteEncoding.L32_STRING_UTF8);
    outflow.putL32StringUTF8("/Root/ubuntoo/monkey-testing");

    outflow.putUInt8(BohrProtocol.CLOSE_NODE);
    /* </Bar> */

    /* <Nav> */
    outflow.putUInt8(BohrProtocol.DEFINE_TYPE_AND_CREATE_INDEXED_NODE);
    outflow.putBohrHashCode("0000000000000013");
    outflow.putUInt16(19084);
    outflow.putL8StringASCII("Nav");

    // fields
    outflow.putUInt8(BohrProtocol.DEFINE_FIELD_AND_SET_FIELD_VALUE);
    outflow.putUInt8(26);
    outflow.putL8StringASCII("tabs");
    outflow.putUInt8(ByteEncoding.ARRAY_UINT8);
    outflow.putUInt8(4);
    outflow.putUInt8(ByteEncoding.L32_STRING_UTF8);
    outflow.putL32StringUTF8("repositories");
    outflow.putL32StringUTF8("projects");
    outflow.putL32StringUTF8("workflows");
    outflow.putL32StringUTF8("settings");

    outflow.putUInt8(BohrProtocol.CLOSE_NODE);
    /* </Bar> */

    outflow.putUInt8(BohrProtocol.CLOSE_DELTA);

    bohr.update(inflow);
    console.log("parsing done...");

    let rootNode = bohr.getNode("0000000000000223");
    document.querySelector('#dz').appendChild(rootNode.DOM_node);

}