
import { S8WebFront } from "/s8-web-front/S8WebFront.js";
import { NeObject } from "/s8-io-bohr-neon/NeObject.js";
import { NavbarMenu } from "/s8-web-front/carbide/navbar/NavbarMenu.js";




/**
 * 
 */
S8WebFront.CSS_import('/s8-web-front/carbide/navbar/Navbar.css');


/**
 * 
 */
export class Navbar extends NeObject {


    /**
     * @type {NavbarMenu[]}
     */
    menus;


    /**
     * 
     */
    selectionIndex = 1;

    
    /**
     * 
     */
    constructor(){
        super();
        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("navbar-header");
    }


    /**
     * 
     * @returns 
     */
    getEnvelope(){
        return this.wrapperNode;
    }


    /**
     * 
     * @param {NavbarMenu[]} menus 
     */
    S8_set_menus(menus){
        let n = menus.length;
        for(let i=0; i<n; i++){
            let menu = menus[i];
            menu.index = i;
            menu.navbar = this;
            this.wrapperNode.appendChild(menu.getEnvelope());
        }
        this.menus = menus;

        this.updateSelection();
    }


    S8_set_selectedIndex(index){
        this.select(index);
    }


    /**
     * 
     * @param {*} index 
     */
    select(index){
        this.selectionIndex = index;
        this.updateSelection();
    }


    /**
     * 
     * @param {*} index 
     */
    updateSelection(){
        let n = this.menus.length;
        for(let i=0; i<n; i++){
            this.menus[i].setSelected(i == this.selectionIndex);
        }
    }

    /**
     * 
     */
    S8_render(){
    }


    /**
     * 
     */
    S8_dispose(){
    }


}