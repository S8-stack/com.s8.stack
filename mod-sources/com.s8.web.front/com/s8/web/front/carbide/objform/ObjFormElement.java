package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Object;
import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.web.front.S8WebDirection;
import com.s8.web.front.S8WebTheme;
import com.s8.web.front.carbide.popover.Popover;



public class ObjFormElement extends WebS8Object {

	
	/**
	 * 
	 * @param branch
	 * @param typeName
	 */
	public ObjFormElement(WebS8Session branch, String typeName) {
		super(branch, typeName);
		onOptionsRequiredLambda(() -> {});
	}
	

	
	/**
	 * 
	 * @param text
	 */
	public void setTooltipDoc(String text) {
		WebS8Session branch = vertex.getSession();
		Popover tooltip = new Popover(branch);
		tooltip.setTheme(S8WebTheme.LIGHT);
		tooltip.setDirection(S8WebDirection.BOTTOM);
		tooltip.setElements(ObjFormTextDoc.create(vertex.getSession(), text));
		vertex.fields().setObjectField("tooltip", tooltip);
	}
	
	
	
	
	public void setOptionsAvailability(boolean isAvailable) {
		vertex.fields().setBool8Field("hasOptions", isAvailable);
	}
	
	
	/**
	 * 
	 * @param lambda
	 */
	public void onOptionsRequiredLambda(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-options-required", lambda);
	}
	
	
	/**
	 * 
	 * @param lambda
	 */
	public void onOptionsRequired(VoidNeFunction function) {
		vertex.methods().setVoidMethod("on-options-required", function);
	}
	
	
	/**
	 * 
	 * @param options
	 */
	public void setOptions(ObjFormOption... options) {
		vertex.fields().setObjectListField("options", options);
	}
	
	
	
	
	

	
}
