package com.s8.io.csv.tests;

import com.s8.io.csv.CSV_Mapping;

/**
 * 
 * @author pc
 *
 */
public class NominalPipeSize {


	@CSV_Mapping(tag="index")
	public int code;
	
	@CSV_Mapping(tag="NPS-id")
	public String NPS_id;
	
	@CSV_Mapping(tag="DN-id") 
	public String DN_id;

	@CSV_Mapping(tag="OD") 
	public double outerDiameter;

	public NominalPipeSize() {
		super();
	}

	@Override
	public String toString() {
		return "NominalPipeSize [code=" + code + ", NPS_id=" + NPS_id + ", DN_id=" + DN_id + ", outerDiameter="
				+ outerDiameter + "]";
	}
	
	
	

}
