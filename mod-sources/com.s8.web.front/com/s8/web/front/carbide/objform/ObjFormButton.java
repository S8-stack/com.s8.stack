package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.web.front.carbide.icons.S8FlatIcon;


/**
 * 
 * @author pierreconvert
 *
 */
public class ObjFormButton extends ObjFormElement {
	
	/**
	 * 
	 * @param branch
	 * @param label
	 * @param icon
	 * @param color
	 * @return
	 */
	public static ObjFormButton create(WebS8Session branch, String label) {
		ObjFormButton objFormButton = new ObjFormButton(branch);
		objFormButton.setLabel(label);
		return objFormButton;
	}
	
	
	
	
	public ObjFormButton(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/ObjFormButton");
	}
	
	
	/**
	 * 
	 * @param lambda
	 */
	public void onClickLambda(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-click", lambda);
	}
	
	
	/**
	 * 
	 * @param lambda
	 */
	public void onClick(VoidNeFunction function) {
		vertex.methods().setVoidMethod("on-click", function);
	}
	
	


	public void setLabel(String name) {
		vertex.fields().setStringUTF8Field("label", name);
	}


	
	/**
	 * 
	 * @param icon
	 */
	public void setIconShape(S8FlatIcon icon){
		vertex.fields().setUInt16Field("iconShapeByCode", icon.code);
	}

	/**
	 * 
	 * @param name
	 */
	public void setIconShapeByName(String name){
		vertex.fields().setStringUTF8Field("iconShape", name);
	}

	
	/**
	 * 
	 * @param lambda
	 */
	public void onSyncLambda(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-sync", lambda);
	}
	
	
	/**
	 * 
	 * @param lambda
	 */
	public void onSync(VoidNeFunction function) {
		vertex.methods().setVoidMethod("on-sync", function);
	}

	
	

}
