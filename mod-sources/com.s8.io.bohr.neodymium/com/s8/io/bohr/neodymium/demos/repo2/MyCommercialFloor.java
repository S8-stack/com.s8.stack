package com.s8.io.bohr.neodymium.demos.repo2;

import java.util.ArrayList;
import java.util.List;

import com.s8.api.objects.annotations.S8Field;
import com.s8.api.objects.annotations.S8ObjectType;


/**
 * 
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 * 
 */
@S8ObjectType(name = "my-commercial-floor")
public class MyCommercialFloor extends MyFloor {

	
	
	
	public  @S8Field(name = "elements") List<MyCommercialFloorElement> elements;
	
	
	public MyCommercialFloor() {
		super();
	}
	
	
	@Override
	public void init() {
		int n = (int) (Math.random()*12) + 4;
		elements = new ArrayList<>();
		for(int i=0; i<n; i++) {
			elements.add(MyCommercialFloorElement.create());
		}
	}
	


	@Override
	protected void variate() {
		double u = Math.random();
		if(u<0.2) {
			init();
		}
		else {
			int n = (int) (Math.random()*0.999999*elements.size());
			for(int i=0; i<n; i++) {
				int index = (int) (Math.random()*0.999999*elements.size());
				elements.get(index).variate();
			}
		}
	}
	
	public static MyCommercialFloor create() {
		MyCommercialFloor floor = new MyCommercialFloor();
		floor.init();
		return floor;
	}


}
