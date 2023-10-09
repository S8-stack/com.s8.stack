package com.s8.web.front.carbide.objform;

import java.util.ArrayList;
import java.util.List;

import com.s8.api.objects.web.WebS8Session;
import com.s8.api.objects.web.functions.none.VoidNeFunction;
import com.s8.api.objects.web.lambdas.none.VoidLambda;
import com.s8.api.objects.web.lambdas.primitives.Bool8Lambda;
import com.s8.api.objects.web.lambdas.primitives.Float32Lambda;
import com.s8.api.objects.web.lambdas.primitives.Int32Lambda;
import com.s8.api.objects.web.lambdas.primitives.StringUTF8Lambda;
import com.s8.web.front.S8WebDirection;
import com.s8.web.front.S8WebStatus;
import com.s8.web.front.S8WebTheme;
import com.s8.web.front.carbide.S8NumberFormat;
import com.s8.web.front.carbide.icons.S8FlatIcon;
import com.s8.web.front.carbide.popover.Popover;



public class SetObjFormElement extends ObjFormElement {




	public SetObjFormElement(WebS8Session branch) {
		super(branch, "/s8-web-front/carbide/objform/SetObjFormElement");

		vertex.methods().setVoidMethodLambda("on-expanded", () -> {});
		vertex.methods().setVoidMethodLambda("on-collapsed", () -> {});
		vertex.methods().setVoidMethodLambda("on-sync", () -> {});
	}


	/**
	 * 
	 * @param lambda
	 */
	public void onExpanded(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-expanded", lambda);
	}


	/**
	 * 
	 * @param lambda
	 */
	public void onCollapsed(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("on-collapsed", lambda);
	}



	public void setMarkupColor(ObjFormColor color) {
		vertex.fields().setUInt8Field("markupColor", color.code);
	}

	public void setFieldName(String name) {
		vertex.fields().setStringUTF8Field("fieldName", name);
	}

	public void setTypeName(String name) {
		vertex.fields().setStringUTF8Field("typeName", name);
	}



	/**
	 * 
	 * @param icon
	 */
	public void setIcon(S8FlatIcon icon, ObjFormColor color){
		setIconShape(icon);
		setIconColor(color);
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
	 * @param color
	 */
	public void setIconColor(ObjFormColor color) {
		vertex.fields().setUInt8Field("iconColor", color.code);
	}


	public void setFields(List<ObjFormElement> elements){
		vertex.fields().setObjectListField("fields", elements);
	}

	public void setFields(ObjFormElement... elements){
		List<ObjFormElement> list = new ArrayList<>();
		for(ObjFormElement element : elements) { list.add(element); }
		vertex.fields().setObjectListField("fields", list);
	}

	public void setTogglingState(boolean mustBeExpanded){
		vertex.fields().setBool8Field("togglingState", mustBeExpanded);
	}




	/**
	 * Helper method 
	 * @param name
	 * @param unit
	 * @param value
	 */
	public void addElement(ObjFormElement element) {
		vertex.fields().addObjToList("fields", element);
	}





	/**
	 * Helper method 
	 * @param name
	 * @param unit
	 * @param value
	 */
	public void addScalarGetter(String name, String unit, S8NumberFormat format, double value) {
		ScalarObjFormGetter fieldView = new ScalarObjFormGetter(vertex.getSession());
		fieldView.setFieldName(name);
		fieldView.setUnit(unit);
		fieldView.setFormat(format);
		fieldView.setValue(value);
		addElement(fieldView);
	}


	/**
	 * Helper method
	 * @param name
	 * @param value
	 */
	public void addIntegerGetter(String name, int value) {
		IntegerObjFormGetter fieldView = new IntegerObjFormGetter(vertex.getSession());
		fieldView.setFieldName(name);
		fieldView.setValue(value);
		addElement(fieldView);
	}


	/**
	 * Helper method
	 * @param name
	 * @param value
	 */
	public void addTextGetter(String name, String value) {
		TextObjFormGetter fieldView = new TextObjFormGetter(vertex.getSession());
		fieldView.setFieldName(name);
		fieldView.setValue(value);
		addElement(fieldView);
	}




	/**
	 * helper method
	 * @param name
	 * @param unit
	 * @param initialValue
	 * @param lambda
	 */
	public void addBooleanSetter(String name, boolean initialValue, Bool8Lambda lambda) {
		addElement(BooleanObjFormSetter.create(vertex.getSession(), name, initialValue, lambda));
	}

	/**
	 * helper method
	 * @param name
	 * @param unit
	 * @param initialValue
	 * @param lambda
	 */
	public void addBooleanSetter(String name, boolean initialValue, Bool8Lambda lambda, String doc) {
		addElement(BooleanObjFormSetter.create(vertex.getSession(), name, initialValue, lambda, doc));
	}



	/**
	 * helper method
	 * @param name
	 * @param unit
	 * @param initialValue
	 * @param lambda
	 */
	public void addScalarSetter(String name, String unit, S8NumberFormat format, double initialValue, 
			Float32Lambda lambda) {
		addElement(ScalarObjFormSetter.create(vertex.getSession(), name, unit, format, initialValue, lambda));
	}


	/**
	 * helper method
	 * @param name
	 * @param unit
	 * @param initialValue
	 * @param lambda
	 */
	public void addScalarSetter(String name, String unit, S8NumberFormat format, double initialValue, 
			Float32Lambda lambda, String doc) {
		addElement(ScalarObjFormSetter.create(vertex.getSession(), name, unit, format, initialValue, lambda, doc));
	}



	/**
	 * helper method
	 * 
	 * @param name
	 * @param initialValue
	 * @param lambda
	 */
	public void addIntegerSetter(String name, int initialValue, Int32Lambda lambda) {
		addElement(IntegerObjFormSetter.create(vertex.getSession(), name, initialValue, lambda));
	}


	/**
	 * 
	 * @param name
	 * @param initialValue
	 * @param lambda
	 */
	public void addIntegerSetter(String name, int initialValue, Int32Lambda lambda, String doc) {
		addElement(IntegerObjFormSetter.create(vertex.getSession(), name, initialValue, lambda, doc));
	}



	/**
	 * helper method 
	 * 
	 * @param name
	 * @param initialValue
	 * @param lambda
	 */
	public void addTextSetter(String name, String initialValue, StringUTF8Lambda lambda) {
		TextObjFormSetter fieldView = new TextObjFormSetter(vertex.getSession());
		fieldView.setName(name);
		fieldView.setValue(initialValue);
		fieldView.onValueChangedLambda(lambda);
		addElement(fieldView);
	}



	public void setStatus(S8WebStatus status) {
		vertex.fields().setUInt8Field("status", status.code);
	}


	public void setStatusPopover(Popover popover) {
		vertex.fields().setObjectField("statusPopover", popover);
	}
	
	
	/**
	 * 
	 * @param status
	 * @param message
	 */
	public void setStatusPopover(S8WebStatus status, String message) {
		WebS8Session branch = vertex.getSession();
		Popover popover = new Popover(branch);
		switch(status) {
		case WARNING : popover.setTheme(S8WebTheme.WARNING); break;
		case ERROR : popover.setTheme(S8WebTheme.DANGER); break;
		default : popover.setTheme(S8WebTheme.LIGHT); break;
		}
		popover.setDirection(S8WebDirection.BOTTOM);
		popover.setElements(ObjFormTextDoc.create(vertex.getSession(), message));
		setStatusPopover(popover);
	}


	/**
	 * 
	 * @param lambda
	 */
	public void onStatusMessageRequired(VoidLambda lambda) {
		vertex.methods().setVoidMethodLambda("get-status-info", lambda);
	}


	/**
	 * 
	 * @param state
	 */
	public void setUpToDate(boolean state) {
		vertex.fields().setBool8Field("isUpToDate", state);
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
