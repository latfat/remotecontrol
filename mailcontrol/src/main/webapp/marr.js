function Marquee(instance, marquee, direction, delay, amount) {
	this.instance = instance;// 实例名称
	this.marquee = document.getElementById(marquee);
	this.delay = delay;
	if (this.delay == null) {
		this.delay = 1;
	}
	this.amount = amount;
	if (this.amount == null) {
		this.amount = 1;
	}
	this.direction = direction;
	this.width = this.marquee.clientWidth;
	this.height = this.marquee.clientHeight;
	this.container = document.createElement("table");
	this.container.border = 0;
	this.container.cellspacing = "0"
	this.container.cellpadding = "0"
	var _HTML = this.marquee.innerHTML;
	this.marquee.innerHTML = "";
	this.marquee.appendChild(this.container);
	var _tr = this.container.insertRow(this.container.rows.length);
	var _td = _tr.insertCell(_tr.cells.length);
	_td.innerHTML = _HTML;
	this.scrollHeight = this.container.clientHeight;
	this.scrollWidth = this.container.clientWidth;
	switch (this.direction.toLowerCase()) {
	case "up":
	case "down":
		if (this.scrollHeight >= this.height) {
			var _tr = this.container.insertRow(this.container.rows.length);
			var _td = _tr.insertCell(_tr.cells.length);
			_td.innerHTML = _HTML;
			this.scrollHeight = this.container.clientHeight;
		}
		if (this.direction.toLowerCase() == "down") {
			this.marquee.scrollTop = this.scrollHeight / 2;
		}
		break;
	default:
		if (this.scrollWidth >= this.width) {
			var _td = _tr.insertCell(_tr.cells.length);
			_td.innerHTML = _HTML;
			this.scrollWidth = this.container.clientWidth;
		}

	}

	this.marquee.m = this;

	this.intervalId = null;
	this.marquee.onmouseover = function() {
		this.m.Stop();
	}
	this.marquee.onmouseout = function() {
		this.m.Start();
	}
	this.Start();
}
Marquee.prototype.Start = function() {
	this.intervalId = setInterval(this.instance + ".Scroll()", this.delay)
	// this.Scroll();
}
Marquee.prototype.Stop = function(sender) {
	if (this.intervalId != null) {
		clearInterval(this.intervalId);
	}
}
Marquee.prototype.Scroll = function() {
	switch (this.direction.toLowerCase()) {
	case "up":
		if ((this.marquee.scrollTop + this.height) >= this.scrollHeight) {
			this.marquee.scrollTop -= this.scrollHeight / 2
		}
		if (this.scrollHeight > this.height) {
			this.marquee.scrollTop += this.amount;
		}
		break;
	case "down":
		if (this.marquee.scrollTop <= 0) {
			this.marquee.scrollTop += this.scrollHeight / 2
		}
		if (this.scrollHeight > this.height) {
			this.marquee.scrollTop -= this.amount;
		}
		break;
	case "right":
		if (this.marquee.scrollLeft <= 0) {
			this.marquee.scrollLeft += this.scrollWidth / 2
		}
		if (this.scrollWidth > this.width) {
			this.marquee.scrollLeft -= this.amount;
		}
		break;
	default:
		if ((this.marquee.scrollLeft + this.width) >= this.scrollWidth) {
			this.marquee.scrollLeft -= this.scrollWidth / 2
		}
		if (this.scrollWidth > this.width) {
			this.marquee.scrollLeft += this.amount;
		}
	}
}