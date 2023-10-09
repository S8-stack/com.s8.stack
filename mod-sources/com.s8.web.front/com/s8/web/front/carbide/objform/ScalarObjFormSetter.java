package com.s8.web.front.carbide.objform;

import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.primitives.Float32NeFunction;
import com.s8.api.objects.web.lambdas.primitives.Float32Lambda;
import com.s8.web.front.carbide.S8NumberFormat;


/**
 * 
 * @author pierreconvert
 *
 */
public class ScalarObjFormSetter extends PrimitiveObjFormSetter {


	public static ScalarObjFormSetter create(WebS8Session branch, 
			String name, 
			String unit,
			S8NumberFormat format,
			double initialValue, 
			Float32Lambda lambda) {
		ScalarObjFormSetter fieldView = new ScalarObjFormSetter(branch);
		fieldView.setName(name);
		fieldView.setUnit(unit);
		fieldView.setValue((float) initialValue);
		fieldView.onValueChanged(lambda);
		return fieldView;
	}


	public static ScalarObjFormSetter create(WebS8Session branch, 
			String name, 
			String unit,
			S8NumberFormat format,
			double initialValue, 
			Float32Lambda lambda, 
			String doc) {
		ScalarObjFormSetter fieldView = new ScalarObjFormSetter(branch);
		fieldView.setName(name);
		fieldView.setUnit(unit);
		fieldView.setValue((float) initialValue);
		fieldView.onValueChanged(lambda);	
		fieldView.setTooltipDoc(doc);
		return fieldView;
	}



	/**
	 * 
	 * @param branch
	 */
	public ScalarObjFormSetter(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/ScalarObjFormSetter");
	}


	/**
	 * 
	 * @param unit
	 */
	public void setUnit(String unit) {
		vertex.fields().setStringUTF8Field("unit", unit);
	}



	/**
	 * 
	 * @param unit
	 */
	public void setFormat(S8NumberFormat format) {
		vertex.fields().setUInt8Field("format", format.code);
	}


	/**
	 * 
	 * @param value
	 */
	public void setValue(float value) {
		vertex.fields().setFloat32Field("value", value);
	}


	/**
	 * 
	 * @param lambda
	 */
	public void onValueChanged(Float32Lambda lambda) {
		vertex.methods().setFloat32MethodLambda("on-value-changed", lambda);
	}


	/**
	 * 
	 * @param func
	 */
	public void onValueChanged(Float32NeFunction func) {
		vertex.methods().setFloat32Method("on-value-changed", func);
	}
}
