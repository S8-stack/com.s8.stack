
import { S8 } from '../../s8/S8.js';

class IconDatabase {

    constructor() {
        this.map = new Map();
    }

    retrieveIconSVG(name, callback) {
        let svgNode = this.map.get(name);
        if (svgNode != undefined) {
            // callback
            callback(svgNode);
        }
        else {
            let _this = this;
            S8.sendRequest_GET("/air/icon/" + name + ".svg", "text", function (svgNode) {

                _this.map.set(name, svgNode);

                // callback
                callback(svgNode);
            });
        }
    }
}


const IconDb = new IconDatabase();


export class Icon {

    constructor() {

        // import component syle
        S8.import_CSS("/air/icon/Icon.css");

        this.node = document.createElement("span");
        
        // default config
        this.width = 32;
        this.height = 32;
        this.shape = "alert";
        this.style = "grey";
       
        // initial render
        this.isDrawn = false;
        this.isSVGNodeLoaded = false;
    }

   
    setSize(size){ 
        this.width = size; 
        this.height = size;
        this.resize();
    }

    setHeight(height){ 
        this.height = height;
        this.resize();
    }
    
    setWidth(width){  
        this.width = width;
        this.resize();
    }

    resize(){ /** private method */
        if(this.isDrawn){ // update properties
            svgNode.setAttribute("width", this.width);
            svgNode.setAttribute("height", this.height);
        }
    }

    setShape(shape){
        this.shape = shape;
        this.isDrawn = false;
        this.redrawShape();
    }

    redrawShape(){ /** private method */
        if(this.isDrawn){
            this.isSVGNodeLoaded = false;
            let _this = this;
            IconDb.retrieveIconSVG(this.shape, function (SVG_icon) {
                // insert icon
                _this.node.innerHTML = SVG_icon;
                let svgNode = _this.node.getElementsByTagName("svg")[0];
                svgNode.setAttribute("width", _this.width);
                svgNode.setAttribute("height", _this.height);
                svgNode.classList.add("he-icon-svg");
                svgNode.classList.add("he-icon-style-"+_this.style);
                _this.svgNode = svgNode;
                _this.isSVGNodeLoaded = true;
            });
        }
    }


    draw(){
        if(!this.isDrawn){ this.isDrawn = true;
            this.redrawShape();
        }
    }


    setStyle(style) {
        if(this.isSVGNodeLoaded){
            this.svgNode.classList.remove("he-icon-style-"+this.style);
            this.style = style;
            this.svgNode.classList.add("he-icon-style-"+this.style);
        }
        else {
            this.style = style;
        }
    }


    getEnvelope() {
        this.draw();
        return this.node;
    }
    

    dispose() { // nothing to dispose
    }
}
