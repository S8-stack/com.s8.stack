import { Icon } from "../icons/Icon";
import { S8 } from "../../s8/S8.js";


export class Bar {

    constructor(props) {
        S8.import_CSS('/air/bar/Bar.css');

        // build wrapper node
        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("he-bar-wrapper");

        // build header node
        this.headerNode = document.createElement("header");
        this.headerNode.classList.add("he-bar");
        this.wrapperNode.appendChild(this.headerNode);

        this.isDrawn = false;
        this.onSelectionChanged = function (index) { /* to be orriden */ };
    }

    draw() {
        if (!this.isDrawn) {
            this.redrawMenus();
            this.isDrawn = true;
        }
    }


    setMenus(menus) {
        this.menus = menus;
        if (this.isDrawn) {
            this.redrawMenus();
        }
    }

    redrawMenus() {
        let _this = this;
        S8.removeChildren(this.headerNode); // remove previous nods if any    
        let n = this.menus.length;
        for (let i = 0; i < n; i++) {
            let menu = this.menus[i];
            menu.index = i;
            this.headerNode.appendChild(menu.getEnvelope());
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

    getSelectedMenu() {
        return this.menus[this.currentSelectedIndex];
    }

    getEnvelope() {
        this.draw();
        return this.wrapperNode;
    }

    dispose() {
        if (this.menus) { this.menus.forEach(menu => menu.dispose()); }
    }
}


export class MenuBarElement {

    constructor(props) {
        this.isSelected = false;

        this.menuName = "<menu name>";
        this.onClickCallback = function (event) { };

        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("he-bar-menu-" + (this.isSelected ? "selected" : "unselected"));

        let _this = this;
        this.onClickListener = this.wrapperNode.addEventListener("click", function (event) {
            _this.onClickCallback(event);
        });

        this.isDrawn = false;
    }


    setName(name) {
        this.menuName = name;
        if (this.isDrawn) {
            this.textNode.innerHTML = this.menuName;
            this.textNode.setAttribute("data-content", this.menuName);
        }
    }

    draw() {
        if (!this.isDrawn) {
            // text node
            this.textNode = document.createElement("span");
            this.textNode.innerHTML = this.menuName;

            // for the ::before trick on stable width for menu
            this.textNode.setAttribute("data-content", this.menuName);
            this.wrapperNode.appendChild(this.textNode);

            this.isDrawn = true;
        }
    }

    getEnvelope() {
        this.draw();
        return this.wrapperNode;
    }

    toggle() {
        if (!this.isSelected) {
            this.isSelected = true;
            this.wrapperNode.classList = "he-bar-menu-selected";
            //this.icon.setStyle("black");
        }
        else {
            this.isSelected = false;
            this.wrapperNode.classList = "he-bar-menu-unselected";
            //this.icon.setStyle("grey");
        }
    }

    dispose() {
        this.liNode.removeEventListener(this.onClickListener);
    }
}



export class IconBarElement {

    constructor(props) {
        this.iconShape = "alert";

        this.wrapperNode = document.createElement("div");
        this.wrapperNode.classList.add("he-bar-icon")

        this.isDrawn = false;
    }


    setIconShape(shape) {
        this.iconShape = shape;
        if (this.isDrawn) { this.icon.setShape(this.iconShape); }
    }

    draw() {
        if (!this.isDrawn) {
            this.icon = new Icon()
            this.icon.setSize(40);
            this.icon.setStyle("white");
            this.icon.setShape(this.iconShape);
            this.wrapperNode.appendChild(this.icon.getEnvelope());

            this.isDrawn = true;
        }
    }


    getEnvelope() {
        this.draw();
        return this.wrapperNode;
    }

    toggle() { }

    dispose() { /* nothing to dispose */ }
}