package com.s8.web.front.carbide.dock;

import java.util.ArrayList;
import java.util.List;

import com.s8.api.objects.web.WebS8Session;
import com.s8.web.front.carbide.cube.CubeElement;


/**
 * 
 * @author pierreconvert
 *
 */
public class Dock extends CubeElement {

	
	/**
	 * 
	 */
	public List<DockItem> items;
	
	/**
	 * 
	 */
	public Dock(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/dock/Dock");
		this.items = new ArrayList<DockItem>();
	}
	
	
	
	public void setItems(List<DockItem> items) {
		vertex.fields().setObjectListField("items", items);
	}
	
	/**
	 * 
	 */
	public static Dock createPreset00(WebS8Session branch) {
		
		Dock dock = new Dock(branch);
		List<DockItem> items = new ArrayList<DockItem>();
		String root = "/s8-web-front/carbide/icons/photores";
		
		items.add(new DockItem(branch, "Home", root+"/compass-icon-128px.png"));
		items.add(new DockItem(branch, "Fluid Properties", root+"/atom-icon-128px.png"));
		items.add(new DockItem(branch, "Lattice", root+"/lattice-icon-128px.png"));
		items.add(new DockItem(branch, "Sharing...", root+"/network-icon-128px.png"));
		items.add(new DockItem(branch, "Projects...", root+"/project-icon-128px.png"));
		items.add(new DockItem(branch, "Settings", root+"/settings-icon-128px.png"));
		dock.setItems(items);
		
		return dock;
	}
	
}
