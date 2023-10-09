

export const WebSVG = {};










WebSVG.STROKE_COLOR_DEFAULT = "black";

/**
 * 
 * @param {*} code 
 * @returns {string}
 */
WebSVG.getStrokeColor = function(code) {
    switch(code){
        case 0x02 : return "black";
        case 0x03 : return "grey";
        case 0x04 : return "red";
        case 0x05 : return "green";
        case 0x06 : return "blue";
        default : return WebSVG.STROKE_COLOR_DEFAULT;
    }
};



WebSVG.STROKE_SOLIDITY_DEFAULT = null;

/**
 * 
 * @param {number} code 
 * @returns {string}
 */
WebSVG.getStrokeSolidity = function(code) {
    switch(code){
       
        case 0x02 : return null;
        case 0x03 : return "3 2";
        case 0x04 : return "4 2";
        default : return WebSVG.STROKE_SOLIDITY_DEFAULT;
    }
}



const STROKE_THICKNESS_PREFIX = "websvg-stroke-thickness-";

WebSVG.STROKE_THICKNESS_DEFAULT = STROKE_THICKNESS_PREFIX + "normal";

/**
 * 
 * @param {number} code 
 * @returns {string}
 */
WebSVG.getStrokeThickness = function(code) {
    switch(code){
        case 0x02 : STROKE_THICKNESS_PREFIX + "thin";
        case 0x03 : STROKE_THICKNESS_PREFIX + "normal";
        case 0x04 : STROKE_THICKNESS_PREFIX + "thick";
        case 0x05 : STROKE_THICKNESS_PREFIX + "highlighted";
        default : return WebSVG.STROKE_THICKNESS_DEFAULT;
    }
}



/**
 * 
 */
export class WebSVG_ViewPort {

    /**
     * 
     */
    width = 800;

    /**
     * 
     */
    height = 600;

    /**
     * 
     */
    marginTop = 40;
    marginBottom = 40;
    marginLeft = 40;
    marginRight = 40;



    scalingFactor = 1.0;


    /**
     * 
     */
    xOffset = 0.0;

    yOffset = 0.0;


    /** @type{number} */
    xMin = 0.0;

    /** @type{number} */
    xMax = 0.0;

    /** @type{number} */
    yMin = 0.0;
    
    /** @type{number} */
    yMax = 0.0;

    /** @type{boolean} */
    isBoundingBoxInitialized = false;


    clearBoundingBox(){
        this.isBoundingBoxInitialized = false;
    }

    updateBoundingBox(x, y){
        if(!this.isBoundingBoxInitialized){
            this.xMin = x;
            this.xMax = x;
            this.yMin = y;
            this.yMax = y;
            this.isBoundingBoxInitialized = true;
        }
        else{
            this.xMin = Math.min(this.xMin, x);
            this.xMax = Math.max(this.xMax, x);
            this.yMin = Math.min(this.yMin, y);
            this.yMax = Math.max(this.yMax, y);
        }
    }


    rescale(){

        /* constraint 1 : xScalingFactor * xMin + xOffset = margin */
        /* constraint 2 : xScalingFactor * xMax + xOffset = width - margin */
        let xScalingFactor = (this.width - this.marginLeft - this.marginRight) / (this.xMax - this.xMin);

         /* constraint 1 : yScalingFactor * yMin + yOffset = margin */
        /* constraint 2 : yScalingFactor * xMax + yOffset = height - margin */
        let yScalingFactor = (this.height - this.marginTop - this.marginBottom) / (this.yMax - this.yMin);

        /* x is limiting */
        if(xScalingFactor < yScalingFactor){
            this.scalingFactor = xScalingFactor;
            this.xOffset = this.marginLeft - this.scalingFactor * this.xMin;
            this.yOffset = 0.5 * this.height - this.scalingFactor * 0.5 * (this.yMin + this.yMax);
            /* constraint : scalingFactor * (yMin + yMax)/2 + yOffset = height/2 */
        }
        else {
            this.scalingFactor = yScalingFactor;
            this.yOffset = this.marginTop - this.scalingFactor * this.yMin;
            this.xOffset = 0.5 * this.width - this.scalingFactor * 0.5 * (this.xMin + this.xMax);
        }
    }


    /**
     * 
     * @param {double} x 
     * @returns 
     */
    xTranform(x){
        return x * this.scalingFactor + this.xOffset;
    }


    /**
     * 
     * @param {double} y 
     * @returns 
     */
    yTranform(y){
        return this.height - (y * this.scalingFactor + this.yOffset);
    }

     /**
     * 
     * @param {double} y 
     * @returns 
     */
    sTranform(l){
        return l * this.scalingFactor;
    }

}