package com.s8.io.csv;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Use this method to declare that a method or a field msut be mapped to a .csv file column
 * @author pierreconvert
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value={METHOD, FIELD, PARAMETER})
public @interface CSV_Mapping {

	public String tag();
}
