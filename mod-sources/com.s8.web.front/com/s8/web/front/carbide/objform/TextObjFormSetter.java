package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.primitives.StringUTF8NeFunction;
import com.s8.api.objects.web.lambdas.primitives.StringUTF8Lambda;


/**
 * 
 * @author pierreconvert
 *
 */
public class TextObjFormSetter extends PrimitiveObjFormSetter {

	public TextObjFormSetter(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/TextObjFormSetter");
	}
	
	
	public void setValue(String value) {
		vertex.fields().setStringUTF8Field("value", value);
	}
	

	
	public void onValueChanged(StringUTF8NeFunction func) {
		vertex.methods().setStringUTF8Method("on-value-changed", func);
	}
	
	public void onValueChangedLambda(StringUTF8Lambda lambda) {
		vertex.methods().setStringUTF8MethodLambda("on-value-changed", lambda);
	}
}
