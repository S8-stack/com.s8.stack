    package com.s8.io.bohr.neodymium.demos.repo2;

import com.s8.api.objects.annotations.S8Field;
import com.s8.api.objects.annotations.S8ObjectType;
import com.s8.api.objects.repo.RepoS8Object;



/**
 * 
 * 
 * 
 * 
 * @author Pierre Convert
 * Copyright (C) 2022, Pierre Convert. All rights reserved.
 *
 */
@S8ObjectType(name = "my-floor", sub= {
		MyCommercialFloor.class,
		MyEmptyFloor.class
})
public abstract class MyFloor extends RepoS8Object {

	public final static long HAS_CHANGED = 0x02;


	public @S8Field(name = "x0") double x0;

	public @S8Field(name = "x1") double x1;

	public @S8Field(name = "y0") double y0;

	public @S8Field(name = "y1") double y1;

	public @S8Field(name = "ceiling-height") double ceilingHeight;

	
	public enum Face {
		
		POS_X, NEG_X, POS_Y, NEG_Y;
		
	}
	

	

	public MyFloor() {
		super();
	}

	

	public static MyFloor create() {
		MyFloor element = null;
		if(Math.random()<0.3){
			element = MyEmptyFloor.create();
		}
		else {
			element = MyCommercialFloor.create();
		}
		return element;
	}



	
	protected abstract void init();
	
	protected abstract void variate();



}
