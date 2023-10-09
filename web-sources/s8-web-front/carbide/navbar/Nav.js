
import { S8 } from "../../s8/S8.js";
import { Icon } from "../icons/Icon.js";

export class Nav {

    constructor() {
        S8.import_CSS('/air/nav/Nav.css');

        this.menus = null;
        this.onSelectionChanged = function (index) { /* to be orriden */ };

        this.isDrawn = false;
    }


    draw() {
        if (!this.isDrawn) {
            let _this = this;

            this.navNode = document.createElement("nav");
            this.navNode.className = "he-nav";

            this.ulNode = document.createElement("ul");
            this.navNode.appendChild(this.ulNode);

            this.redrawMenus();
            this.isDrawn = true;
        }
    }


    setMenus(menus) {
        this.menus = menus;
        if(this.isDrawn){ this.redrawMenus(); }
    }

    redrawMenus(){
        S8.removeChildren(this.ulNode);
        if (this.menus) {
            let _this = this;
            let n = this.menus.length;
            for (let i = 0; i < n; i++) {
                let menu = this.menus[i];
                menu.index = i;
                this.ulNode.appendChild(menu.getEnvelope());
                let index = i;
                menu.onClickCallback = function (e) {
                    _this.getSelectedMenu().toggle();
                    _this.currentSelectedIndex = index;
                    _this.getSelectedMenu().toggle();
                }
            }

            this.currentSelectedIndex = 0;
            this.menus[this.currentSelectedIndex].toggle(); // select
        }
    }


    getSelectedMenu() {
        return this.menus[this.currentSelectedIndex];
    }

    getEnvelope() {
        this.draw();
        return this.navNode;
    }
}


export class NavMenu {

    constructor(props) {
        // default
        this.iconShape = "alert";
        this.tabName = "<Tab name here>";
        this.onClickCallback = function (event) { };
        this.isDrawn = false;

        this.isSelected = false;
    }

    setIconShape(iconShape) {
        this.iconShape = iconShape;
        if (this.isDrawn) {
            this.icon.setShape(this.iconShape);
        }
    }

    setTabName(tabName) { /** (passive) */
        this.tabName = tabName;
        if (this.isDrawn) {
            this.textNode.innerHTML = this.tabName;
            this.textNode.setAttribute("data-content", this.tabName);
        }
    }

    draw() { /** private method */
        if (!this.isDrawn) {  this.isDrawn = true;

            this.liNode = document.createElement("li");
            this.liNode.classList.add("he-nav-menu-" + (this.isSelected ? "selected" : "unselected"));


            this.icon = new Icon();
            this.icon.setShape(this.iconShape);
            this.icon.setSize(16);
            this.liNode.appendChild(this.icon.getEnvelope());

            // text node
            this.textNode = document.createElement("span");
            this.textNode.innerHTML = this.tabName;

            // for the ::before trick on stable width for menu
            this.textNode.setAttribute("data-content", this.tabName);
            this.liNode.appendChild(this.textNode);

            let _this = this;
            this.onClickListener = this.liNode.addEventListener("click", function (event) {
                _this.onClickCallback(event);
            });
        }
    }


    getEnvelope() {
        this.draw();
        return this.liNode;
    }

    toggle() {
        if (!this.isSelected) {
            this.isSelected = true;
            if (this.isDrawn) {
                this.liNode.classList = "he-nav-menu-selected";
                this.icon.setStyle("black");
            }
        }
        else {
            this.isSelected = false;
            if (this.isDrawn) {
                this.liNode.classList = "he-nav-menu-unselected";
                this.icon.setStyle("grey");
            }
        }
    }

    dispose() {
        this.liNode.removeEventListener(this.onClickListener);
    }
}