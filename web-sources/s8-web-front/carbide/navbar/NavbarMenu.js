
import { S8WebFront } from "/s8-web-front/S8WebFront.js";
import { NeObject } from "/s8-io-bohr-neon/NeObject.js";
import { Navbar } from "/s8-web-front/carbide/navbar/Navbar.js";
import { S8 } from "/s8-io-bohr-atom/S8.js";


/**
 * 
 */
S8WebFront.CSS_import('/s8-web-front/carbide/navbar/Navbar.css');


/**
 * 
 */
export class NavbarMenu extends NeObject {



    /**
     * @type{Navbar}
     */
    navbar;
    

    /**
     * @type{number}
     */
    index;



    constructor(){
        super();
        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("navbar-menu");

        this.labelNode = document.createElement("div");
        this.labelNode.classList.add("navbar-menu-label");
        this.wrapperNode.appendChild(this.labelNode);

        this.iconNode = document.createElement("div");
        this.iconNode.classList.add("navbar-menu-icon");
        this.labelNode.appendChild(this.iconNode);

        this.nameNode = document.createElement("div");
        this.nameNode.classList.add("navbar-menu-name");
        this.labelNode.appendChild(this.nameNode);
        
        this.wrapperNode.setAttribute("selected", "true");

        const _this = this;
        this.wrapperNode.addEventListener("click", function (event) {
            S8.branch.loseFocus();
            _this.navbar.select(_this.index);
            _this.S8_vertex.runVoid("on-selected");
            event.stopPropagation();
        }, false);
    }




    /**
     * 
     * @param {boolean} isSelected 
     */
    setSelected(isSelected){
        this.wrapperNode.setAttribute("selected", isSelected ? "true" : "false");
    }

    
    /**
     * 
     * @returns 
     */
    getEnvelope(){
        return this.wrapperNode;
    }


    /**
     * icon
     * @param {*} code 
     */
    S8_set_icon(code){
        S8WebFront.SVG_insertByCode(this.iconNode, code, 16, 16);
    }


    /**
     * name
     * @param {*} name 
     */
    S8_set_name(name){
       this.nameNode.innerHTML = name;
    }

    
    /**
     * 
     */
    S8_render(){
    }


    S8_unfocus(){ /* lose focus by other means */ }

    /**
     * 
     */
    S8_dispose(){
    }


}