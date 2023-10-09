


export class DynamicMeshScreen {


	opts;

	/**
	 * @type{number} screen width
	 */
	w;


	/**
	 * @type{number} screen height
	 */
	h;

	drawArea;


	/**
	 * @type{Array<Particle>}
	 */
	particles;



	rgb;

	delay;


	/**
	 * @type{string}
	 */
	requestAnimationID;

	constructor(options) {
		this.opts = options;

		this.rgb = this.opts.lineColor.match(/\d+/g);
		this.delay = 200;
		
		this.canvasBody = document.createElement("canvas");
		this.canvasBody.setAttribute("id", "inboard-canvas");
	}
	
	
	getEnvelope(){
		return this.canvasBody;
	}



	resizeReset() {
		this.w = this.canvasBody.width = window.innerWidth;
		this.h = this.canvasBody.height = window.innerHeight;
	}


	deBounce() {
		let tid = 0;
		clearTimeout(tid);
		let _this = this;
		tid = setTimeout(function () {
			_this.resizeReset();
		}, this.delay);
	}

	checkDistance(x1, y1, x2, y2) {
	
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}


	linkPoints(point1, hubs) {
		for (let i = 0; i < hubs.length; i++) {
			let distance = this.checkDistance(point1.x, point1.y, hubs[i].x, hubs[i].y);
			let opacity = 1 - distance / this.opts.linkRadius;
			if (opacity > 0) {
				this.drawArea.lineWidth = 0.25;
				this.drawArea.strokeStyle = `rgba(${this.rgb[0]}, ${this.rgb[1]}, ${this.rgb[2]}, ${opacity})`;
				this.drawArea.beginPath();
				this.drawArea.moveTo(point1.x, point1.y);
				this.drawArea.lineTo(hubs[i].x, hubs[i].y);
				this.drawArea.closePath();
				this.drawArea.stroke();
			}
		}
	}

	redraw() {
		this.drawArea.clearRect(0, 0, this.w, this.h);
		let nParticles = this.particles.length;
		for (let i = 0; i < nParticles; i++) {
			let particle = this.particles[i];
			particle.update();
			particle.draw();
		}
		for (let i = 0; i < nParticles; i++) {
			this.linkPoints(this.particles[i], this.particles);
		}


		let _this = this;
		this.requestAnimationID = window.requestAnimationFrame(function(){ _this.redraw() });
	}

	start() {

		let _this = this;

		this.drawArea = this.canvasBody.getContext("2d");


		window.addEventListener("resize", function () {
			_this.deBounce();
		});

		this.resizeReset();

		this.particles = [];
		for (let i = 0; i < this.opts.particleAmount; i++) {
			this.particles.push(new Particle(this));
		}
		this.requestAnimationID = window.requestAnimationFrame(function () {
			_this.redraw();
		});
	}
	
	stop(){
		window.cancelAnimationFrame(this.requestAnimationID);
	}
}



class Particle {



	/**
	 * @type{DynamicMeshScreen} screen
	 */
	screen;


	/**
	 * 
	 * @param {DynamicMeshScreen} screen 
	 */
	constructor(screen) {
		this.screen = screen;

		this.x = Math.random() * screen.w;
		this.y = Math.random() * screen.h;


		let opts = this.screen.opts;

		this.speed = opts.defaultSpeed + Math.random() * opts.variantSpeed;
		this.directionAngle = Math.floor(Math.random() * 360);
		this.color = opts.particleColor;
		this.radius = opts.defaultRadius + Math.random() * opts.variantRadius;
		this.vector = {
			x: Math.cos(this.directionAngle) * this.speed,
			y: Math.sin(this.directionAngle) * this.speed
		};
	}

	update() {
		this.border();
		this.x += this.vector.x;
		this.y += this.vector.y;
	}

	border() {
		if (this.x >= this.screen.w || this.x <= 0) {
			this.vector.x *= -1;
		}
		if (this.y >= this.screen.h || this.y <= 0) {
			this.vector.y *= -1;
		}
		if (this.x > this.screen.w) this.x = this.screen.w;
		if (this.y > this.screen.h) this.y = this.screen.h;
		if (this.x < 0) this.x = 0;
		if (this.y < 0) this.y = 0;
	}

	draw() {
		let drawArea = this.screen.drawArea;

		drawArea.beginPath();
		drawArea.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
		drawArea.closePath();
		drawArea.fillStyle = this.color;
		drawArea.fill();
	};
};




export function launch() {


	const opts = {
		particleColor: "rgb(200,200,200)",
		lineColor: "rgb(200,200,255)",
		particleAmount: 64,
		defaultSpeed: 1,
		variantSpeed: 1,
		defaultRadius: 2,
		variantRadius: 2,
		linkRadius: 256,
	};

	let screen = new DynamicMeshScreen(opts);
	screen.start();
}
