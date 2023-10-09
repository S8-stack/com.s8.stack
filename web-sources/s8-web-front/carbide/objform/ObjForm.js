

import { S8 } from '../../s8/S8.js';
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';


S8.import_CSS("/air/objform/ObjForm.css");



const getColor = function (code) {
    switch (code) {
        case 0x00: return "white";
        case 0x01: return "grey";
        case 0x02: return "blue";
        case 0x03: return "purple";
        case 0x04: return "yellow";
        case 0x05: return "green";
        default: throw "Unsupported color code";
    }
}

const getSVGShape = function(code) {
    switch (code) {
        
        case 0x02: return "octicons/alert";
        case 0x03: return "octicons/beaker";
        case 0x04: return "octicons/check";
        case 0x05: return "octicons/database";
        case 0x06: return "octicons/gift";

        case 0x22: return "set2/forward";
        case 0x23: return "set2/geopoint";
        default: throw "Unsupported shape code";
    }
}



export class ObjFormField extends NeObject {

    /** @type{HTMLDivElement} */
    fieldNode;


    constructor(){
        this.fieldNode = document.createElement("div");
    }

    getEnvelope() {
        return this.fieldNode;
    }
}


export class ObjectObjFormField extends ObjFormField {

    constructor() {
        
        super();

        // default setup
        this.isExpanded = false;
        this.iconColorCode = 4;
        this.markupColorCode = 3;

        this.fieldNode.classList.add("objform-object-field");

        /* <header> */
        this.headerNode = document.createElement("div");
        this.headerNode.classList.add("objform-object-header");
        this.fieldNode.appendChild(this.headerNode);

        this.triangleNode = document.createElement("div");
        this.triangleNode.classList.add("objform-markup-" + getColor(this.markupColorCode));
        this.triangleNode.classList.add("objform-icon-triangle-collapsed");
        this.triangleNode.innerHTML = ` <svg height="10" width="10" viewBox="0 0 16 16">
            <polygon points="2,0 14,8 2,16" />
            </svg>`;
        this.headerNode.appendChild(this.triangleNode);


        //<div class=><span>Folder_00:</span></div>
        let nameWrapperNode = document.createElement("div");
        nameWrapperNode.classList.add("objform-field-name-object");
        this.headerNode.appendChild(nameWrapperNode);

        this.nameNode = document.createElement("span");
        this.nameNode.innerHTML = "${field_name}:";
        nameWrapperNode.appendChild(this.nameNode);

        this.iconNode = document.createElement("div");
        this.iconNode.classList.add("objform-icon-size-16");
        this.iconNode.classList.add("objform-icon-color-" + getColor(this.iconColorCode));
        this.iconNode.innerHTML = ` <svg 
        width="16px" height="16px" viewBox="0 0 16 16" xmlns="http://www.w3.org/2000/svg">
            <path clip-rule="evenodd" d="M2 6V6.29266C2 7.72154 2.4863 9.10788 3.37892 10.2236L8 16L12.6211 10.2236C13.5137 9.10788 14 7.72154 14 6.29266V6C14 2.68629 11.3137 0 8 0C4.68629 0 2 2.68629 2 6ZM8 8C9.10457 8 10 7.10457 10 6C10 4.89543 9.10457 4 8 4C6.89543 4 6 4.89543 6 6C6 7.10457 6.89543 8 8 8Z"
            fill-rule="evenodd" />
        </svg>`;

        this.headerNode.appendChild(this.iconNode);


        // <div><span class="">R3Container</span></div>
        let typeWrapperNode = document.createElement("div");
        typeWrapperNode.classList.add("objform-object-header-type");
        this.headerNode.appendChild(typeWrapperNode);

        this.typeNode = document.createElement("span");
        this.typeNode.innerHTML = "${field_type}:";
        typeWrapperNode.appendChild(this.typeNode);

        this.plusNode = document.createElement("div");
        this.plusNode.classList.add("objform-icon-dots");
        this.plusNode.innerHTML = ` <svg 
            width="16px" height="16px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 64">
            <circle cx="8" cy="32" r="8" />
            <circle cx="32" cy="32" r="8" />
            <circle cx="56" cy="32" r="8" />
        </svg>`;
        this.headerNode.appendChild(this.plusNode);
        /* </header> */

        /* <body> */
        this.bodyNode = document.createElement("div");
        this.bodyNode.classList.add("objform-object-body");
        this.fieldNode.appendChild(this.bodyNode);

        /* </body> */
    }

    setIconColor(colorCode) {
        let previous = "objform-icon-color-" + getColor(this.iconColorCode);
        let next = "objform-icon-color-" + getColor(colorCode);
        this.iconNode.classList.replace(previous, next);
        this.iconColorCode = colorCode;
    }
    
    setMarkupColor(colorCode) {
        let previous = "objform-markup-" + getColor(this.markupColorCode);
        let next = "objform-markup-" + getColor(colorCode);
        this.triangleNode.classList.replace(previous, next);
        this.markupColorCode = colorCode;
    }

    setFieldName(name) {
        this.nameNode.innerHTML = name;
    }

    setTypeName(name) {
        this.typeNode.innerHTML = name;
    }

    setIconShapeByCode(code){
       S8.insert_SVG(this.iconNode, getSVGShape(code), 16, 16);
    }

    setIconShape(name){
        S8.insert_SVG(this.iconNode, name, 16, 16);
     }

    setFields(fields){
        // remove previous nodes
        S8.removeChildren(this.bodyNode);

        // add new ones
        let _this = this;
        fields.forEach(field => {
            _this.bodyNode.appendChild(field.getEnvelope());
        });
    }

    setTogglingState(mustBeExpanded){
        if(!this.isExpanded && mustBeExpanded){
            this.triangleNode.classList.replace("objform-icon-triangle-collapsed", "objform-icon-triangle-expanded");
            this.isExpanded = true;
        }
        else if(this.isExpanded && !mustBeExpanded){
            this.triangleNode.classList.replace("objform-icon-triangle-expanded", "objform-icon-triangle-collapsed");
            this.isExpanded = false;
        }
        else{
            throw "error: illegal toggling state";
        }
    }

    removeFields(){
         S8.removeChildren(this.bodyNode);
    }
}


/**
 * 
 */
class PrimtiveObjFormGetter {

    constructor(markupColor) {

        super();

        // setup
        this.markupColor = markupColor;

        this.fieldNode.classList.add("objform-primitive-field");

        this.createNameNode();
        this.createOutputNode();
        this.createPlusNode();
    }

    createNameNode() {
        this.nameNode = document.createElement("div");
        this.nameNode.classList.add("objform-field-name-primitive");
        this.nameNode.classList.add("objform-markup-" + getColor(this.markupColor));
        this.nameNode.innerHTML = "<span>field_name:</span>";
        this.fieldNode.appendChild(this.nameNode);
    }

    createPlusNode() {
        this.plusNode = document.createElement("div");
        this.plusNode.classList.add("objform-icon-dots");
        this.plusNode.innerHTML = `<svg 
      width="16px" height="16px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 64">
      <circle cx="8" cy="32" r="8" />
      <circle cx="32" cy="32" r="8" />
      <circle cx="56" cy="32" r="8" /></svg>`;
        this.fieldNode.appendChild(this.plusNode);
    }

    setMarkupColor(colorCode) {
        let previous = "objform-markup-" + getColor(this.iconColor);
        this.markupColor = colorCode;
        this.nameNode.classList.replace(previous, "objform-markup-" + getColor(this.markupColor));
    }

    setName(name) {
        this.nameNode.innerHTML = `<span>${name}</span>`;
    }
}



export class ScalarObjFormGetter extends PrimtiveObjFormGetter {

    constructor() {
        super(2);
    }

    createOutputNode() {

        /* <output> */
        this.outputNode = document.createElement("div");
        this.outputNode.classList.add("objform-output");
        this.fieldNode.appendChild(this.outputNode);
        this.outputNode.innerHTML = `<span>(value)</span>`;
        /* </output> */

        /* <output> */
        let unitWrapperNode = document.createElement("div");
        unitWrapperNode.classList.add("objform-unit");
        this.unitNode = document.createElement("span");
        this.unitNode.innerHTML = "(unit)";
        unitWrapperNode.appendChild(this.unitNode);
        this.fieldNode.appendChild(unitWrapperNode);
        /* </unit> */
    }


    setValue(value){
        this.outputNode.innerHTML = `<span>${value}</span>`;
    }

    setUnit(abbreviation){
        this.unitNode.innerHTML = abbreviation;
    }
}


export class BooleanObjFormGetter extends PrimtiveObjFormGetter {

    constructor() {
        super(5);
    }

    createOutputNode() {

        /* <output> */
        this.outputNode = document.createElement("div");
        this.outputNode.classList.add("objform-output");
        this.fieldNode.appendChild(this.outputNode);
        this.outputNode.innerHTML = `<span>(value)</span>`;
        /* </output> */
    }


    setValue(value){
        this.outputNode.innerHTML = `<span>${value?"TRUE":"FALSE"}</span>`;
    }
}


/**
 * 
 */
class PrimtiveObjFormSetter {

    constructor(markupColor) {

        // setup
        this.markupColor = markupColor;

        this.fieldNode = document.createElement("div");
        this.fieldNode.classList.add("objform-primitive-field");

        this.createNameNode();
        this.createInputNode();
        this.createPlusNode();
    }

    createNameNode() {
        this.nameNode = document.createElement("div");
        this.nameNode.classList.add("objform-field-name-primitive");
        this.nameNode.classList.add("objform-markup-" + getColor(this.markupColor));
        this.nameNode.innerHTML = "<span>field_name:</span>";
        this.fieldNode.appendChild(this.nameNode);
    }

    createPlusNode() {
        this.plusNode = document.createElement("div");
        this.plusNode.classList.add("objform-icon-dots");
        this.plusNode.innerHTML = `<svg 
      width="16px" height="16px" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 64">
      <circle cx="8" cy="32" r="8" />
      <circle cx="32" cy="32" r="8" />
      <circle cx="56" cy="32" r="8" /></svg>`;
        this.fieldNode.appendChild(this.plusNode);
    }

    getEnvelope() {
        return this.fieldNode;
    }

    setMarkupColor(colorCode) {
        let previous = "objform-markup-" + getColor(this.iconColor);
        this.markupColor = colorCode;
        this.nameNode.classList.replace(previous, "objform-markup-" + getColor(this.markupColor));
    }

    setName(name) {
        this.nameNode.innerHTML = `<span>${name}</span>`;
    }

}


export class BooleanObjFormSetter extends PrimtiveObjFormSetter {

    constructor() {
        super(5);
    }

    createInputNode() {

        /* <input> */
        let inputWrapperNode = document.createElement("div");
        inputWrapperNode.classList.add("objform-input");
        this.inputNode = document.createElement("input");
        this.inputNode.setAttribute("type", "checkbox");
        inputWrapperNode.appendChild(this.inputNode);
        this.fieldNode.appendChild(inputWrapperNode);
        /* </input> */
    }

}


export class ScalarObjFormSetter extends PrimtiveObjFormSetter {

    constructor() {
        super(2);
    }

    createInputNode() {

        /* <input> */
        let inputWrapperNode = document.createElement("div");
        inputWrapperNode.classList.add("objform-input");
        this.inputNode = document.createElement("input");
        this.inputNode.setAttribute("type", "text");
        inputWrapperNode.appendChild(this.inputNode);
        this.fieldNode.appendChild(inputWrapperNode);
        /* </input> */

        /* <unit> */
        let unitWrapperNode = document.createElement("div");
        unitWrapperNode.classList.add("objform-unit");
        this.unitNode = document.createElement("span");
        this.unitNode.innerHTML = "(unit)";
        unitWrapperNode.appendChild(this.unitNode);
        this.fieldNode.appendChild(unitWrapperNode);
        /* </unit> */
    }


    setUnit(abbreviation){
        this.unitNode.innerHTML = abbreviation;
    }
}



export class EnumObjFormSetter extends PrimtiveObjFormSetter {

    constructor() {
        super(4);
    }

    createInputNode() {

        /* <select> */
        let selectWrapperNode = document.createElement("div");
        selectWrapperNode.classList.add("objform-input");

        this.selectNode = document.createElement("select");
        this.selectNode.setAttribute("name", "list");
        selectWrapperNode.appendChild(this.selectNode);
        this.fieldNode.appendChild(selectWrapperNode);

        // populate with options
        for(let i=0; i<4; i++){
            let optionNode = document.createElement("option");
            optionNode.setAttribute("value", `i=${i}`);
            optionNode.innerText = "Option "+i;
            this.selectNode.appendChild(optionNode);
        }
        /* </select> */
    }

    setOptions(options){
        // remove previous nodes
        S8.removeChildren(this.selectNode);

        // add new ones
        let _this = this;
        let n = options.length;
        for(let i=0; i<n; i++){
            let optionNode = document.createElement("option");
            optionNode.setAttribute("value", `i=${i}`);
            optionNode.innerText = options[i];
            this.selectNode.appendChild(optionNode);
        }
    }

}




/**
 * 
 */
 class MethodObjFormLauncher {

    constructor() {

        // setup
        this.markupColor = 0;
        this.isEnabled = false;

        this.fieldNode = document.createElement("div");
        this.fieldNode.classList.add("objform-method");
        this.fieldNode.classList.add("objform-method-disabled");
        
        this.arrowNode = document.createElement("div");
        this.arrowNode.classList.add("objform-icon-arrow-disabled");
        this.arrowNode.innerHTML = ` <svg height="10" width="20" viewBox="0 0 20 10">
            <polygon points="0,4.5 16,4.5 16,1 20,5 16,9 16,5.5 0,5.5"/>
            </svg>`;
        this.fieldNode.appendChild(this.arrowNode);

        /* <name> */
        this.nameNode = document.createElement("div");
        this.nameNode.classList.add("objform-field-name-primitive");
        this.nameNode.innerHTML = "<span>field_name:</span>";
        this.fieldNode.appendChild(this.nameNode);
        /* </name> */
    }


    getEnvelope() {
        return this.fieldNode;
    }

    setName(name) {
        this.nameNode.innerHTML = `<span>${name}</span>`;
    }

    enable(){
        if(!this.isEnabled){
            this.fieldNode.classList.replace("objform-method-disabled", "objform-method-enabled");
            this.arrowNode.classList.replace("objform-icon-arrow-disabled", "objform-icon-arrow-enabled");
            this.isEnabled = true;
        }
    }

    disable(){
        if(this.isEnabled){
            this.fieldNode.classList.replace("objform-method-enabled", "objform-method-disabled");
            this.arrowNode.classList.replace("objform-icon-arrow-enabled", "objform-icon-arrow-disabled");
            this.isEnabled = false;
        }
    }
}



export const objformTest02 = function(){
    
    let obj0 = new ObjectObjFormField();    
    obj0.setFieldName("Folder0:");
    obj0.setTypeName("Axial Config");
    obj0.setIconShapeByCode(0x23);
    obj0.setIconColor(3);

    let obj00 = new ObjectObjFormField();    
    obj00.setFieldName("Folder00:");
    obj00.setTypeName("Sub Axial Config");
    obj00.setIconShapeByCode(0x22);

    let prim000 = new ScalarObjFormSetter();
    prim000.setName("Axial thrust factor:");
    prim000.setUnit("bar");
   
    let prim001 = new ScalarObjFormSetter();
    prim001.setName("special-og mod:");
    prim001.setUnit("°C");
   
    let prim002 = new EnumObjFormSetter();
    prim002.setName("376special-og mod:");
    prim002.setOptions(["Axial", "Radial", "Axial-Radial"]);

    let prim003 = new BooleanObjFormSetter();
    prim003.setName("is-vertical?");


    let method004 = new MethodObjFormLauncher();
    method004.setName("Export as .png");
    method004.enable();

    let method005 = new MethodObjFormLauncher();
    method005.setName("Export as .step");

    let get006 = new ScalarObjFormGetter();
    get006.setName("result:");
    get006.setUnit("kJ.kg-1");
    get006.setValue("9809.09e3");

    let get007 = new BooleanObjFormGetter();
    get007.setName("is compliant?");
    get007.setValue(true);

    let get008 = new BooleanObjFormGetter();
    get008.setName("is up to date?");
    get008.setValue(false);

    obj00.setTogglingState(true);
    obj00.setFields([prim000, prim001, prim002, prim003, method004, method005, get006, get007, get008]);


    let obj01 = new ObjectObjFormField();    
    obj01.setFieldName("Folder01:");
    obj01.setTypeName("Sub Axial Config");
    obj01.setIconShape("octicons/tools");
    obj01.setIconColor(0x02);

    let prim010 = new ScalarObjFormSetter();
    prim010.setName("Axial thrust factor:");
    prim010.setUnit("bar");
   
    let prim011 = new ScalarObjFormSetter();
    prim011.setName("special-og mod:");
    prim011.setUnit("°C");
   
    let prim012 = new EnumObjFormSetter();
    prim012.setName("376special-og mod:");
    prim012.setOptions(["Axial", "Radial", "Axial-Radial"]);

    let prim013 = new BooleanObjFormSetter();
    prim013.setName("is-vertical?");

    obj01.setFields([prim010, prim011, prim012, prim013]);

    let obj02 = new ObjectObjFormField();    
    obj02.setFieldName("Folder02:");
    obj02.setTypeName("Sub Axial Config");
    obj02.setIconShape("octicons/pulse");

    let obj03 = new ObjectObjFormField();    
    obj03.setFieldName("Folder02:");
    obj03.setTypeName("Sub Axial Config");
    obj03.setIconShape("octicons/radio-tower");
    obj03.setIconColor(0x05);


    obj0.setFields([obj00, obj01, obj02, obj03]);

    return obj0;
    
};



