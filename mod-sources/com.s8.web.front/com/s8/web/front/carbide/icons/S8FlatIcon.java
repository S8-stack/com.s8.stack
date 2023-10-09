package com.s8.web.front.carbide.icons;

/**
 * 
 * @author pierreconvert
 *
 */
public enum S8FlatIcon {

	/* <octicons: 0x08xx> */
	alert(0x0802, "octicons/alert.svg"),
	archive(0x0803, "octicons/archive.svg"),
	broadcast(0x0804, "octicons/broadcast.svg"),
	browser(0x0805, "octicons/browser.svg"),
	
	checklist(0x0810, "octicons/checklist.svg"),
	compare(0x08011, "octicons/git-compare.svg"),
	
	lab(0x0812, "octicons/beaker.svg"),
	file(0x0813, "octicons/file.svg"),
	fork(0x0814, "octicons/git-branch.svg"),
	gear(0x0815, "octicons/gear.svg"),
	
	pencil(0x0821, "octicons/pencil.svg"),
	person(0x0822, "octicons/person.svg"),
	play(0x0823, "octicons/play.svg"),
	pulse(0x0824, "octicons/pulse.svg"),
	
	sync(0x0831, "octicons/sync.svg"),
	/* </octicons: 0x08xx> */
	
	

	/* <set2: 0x02xx> */
	arrow_double_curved(0x0202, "set2/arrow-curve-left-right-winding-icon.svg"),
	change(0x0203, "set2/change-arrow-icon.svg"),	
	draw_curve(0x0204, "set2/draw-curve-icon.svg"),
	forward(0x0205, "set2/forward.svg"),
	geo_point(0x0206, "set2/geopoint.svg"),
	select(0x0207, "set2/object-select-icon.svg"),
	robot(0x0208, "set2/robot-icon.svg"),
	screenshot(0x0209, "set2/screenshot-icon.svg"),
	structure(0x020a, "set2/sitemap-icon.svg"),
	techno(0x020b, "set2/tech-icon.svg"),
	thermodynamic(0x020c, "set2/thermometer-icon.svg");
	/* </set2: 0x02xx> */


	public final String pathname;

	public final int code;

	private S8FlatIcon(int code, String pathname) {
		this.pathname = pathname;
		this.code = code;
	}

	public static void main(String[] args) {
		compile();
	}
	
	
	/**
	 * 
	 */
	public final static String JS_MAP_NAME = "S8_FlatIcons_Map";
	

	public static void compile() {
		StringBuilder builder = new StringBuilder();
		for(S8FlatIcon icon : S8FlatIcon.values()) {
			builder.append("/** "+icon.name()+" */\n");	
			builder.append(JS_MAP_NAME+"["+String.format("0x%04x", icon.code)+"] = \""+icon.pathname+"\";\n");	
			builder.append("\n");	
		}
		String codeSection = builder.toString();
		System.out.println(codeSection);
	}


}
