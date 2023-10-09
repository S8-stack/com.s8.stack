package com.s8.web.front.websvg;

public enum WebSVG_StrokeSolidity {

	SOLID(0x02, "solid"),

	SMALL_DASH(0x03, "small-dash"),

	LONG_DASH(0x04, "long-dash");



	public final int code;

	public final String name;


	private WebSVG_StrokeSolidity(int code, String name) {
		this.code = code;
		this.name = name;
	}

}
