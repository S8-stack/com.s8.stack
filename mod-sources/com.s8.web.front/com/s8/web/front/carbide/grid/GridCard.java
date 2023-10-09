package com.s8.web.front.carbide.grid;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.web.front.carbide.popover.Popover;


/**
 * 
 * @author pierreconvert
 *
 */
public class GridCard extends WebS8Object {

	
	/**
	 * 
	 * @param branch
	 * @param typeName
	 */
	public GridCard(WebS8Session branch, String typeName) {
		super(branch, typeName);
	}

	

	/**
	 * 
	 * @param name
	 */
	public void setSelected(boolean isSelected) {
		vertex.fields().setBool8Field("isSelected", isSelected);
	}
	
	
	public void clearPopover() {
		vertex.fields().setObjectField("popover", null);
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setPopover(Popover popover) {
		vertex.fields().setObjectField("popover", popover);
	}
	

	
	
	/**
	 * 
	 * @param func
	 */
	public void onClick(VoidNeFunction func) {
		vertex.methods().setVoidMethod("on-click", func);
	}
	
	

	/**
	 * 
	 * @param func
	 */
	public void onClickLambda(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-click", lambda);
	}
	
	
}
