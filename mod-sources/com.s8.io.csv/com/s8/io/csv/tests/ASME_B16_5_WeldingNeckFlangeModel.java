package com.s8.io.csv.tests;

import com.s8.io.csv.CSV_Mapping;

/**
 * 
 * @author pc
 * From ASME B16.5-2003 (see for instance p.70)
 */
public class ASME_B16_5_WeldingNeckFlangeModel {

	public @CSV_Mapping(tag="index") String index;

	public @CSV_Mapping(tag="size") String nominalPipeSize;

	public @CSV_Mapping(tag="class") String pressureRating;

	public @CSV_Mapping(tag="O") double outsideDiameter;

	public @CSV_Mapping(tag="tf") double thickness;

	public @CSV_Mapping(tag="X") double hubDiameter;

	public @CSV_Mapping(tag="A") double neckDiameter;

	public @CSV_Mapping(tag="Y") double hubLength;

	public @CSV_Mapping(tag="B") double bore;

	public @CSV_Mapping(tag="fr") double faceRaise;

	public @CSV_Mapping(tag="R") double raisedFaceOutsideDiamater;

	public @CSV_Mapping(tag="W") double boltsCircleDiameter;

	public @CSV_Mapping(tag="Jd") double drillDiameter;

	public @CSV_Mapping(tag="Jb") double boltsDiameter;

	public @CSV_Mapping(tag="nHoles") int nBolts;

	public @CSV_Mapping(tag="weight") double weight;
	
	

	@Override
	public String toString() {
		return "ASME_B16_5_WeldingNeckFlangeModel [nominalPipeSize=" + nominalPipeSize + ", pressureRating="
				+ pressureRating + ", outsideDiameter=" + outsideDiameter + ", thickness=" + thickness
				+ ", hubDiameter=" + hubDiameter + ", neckDiameter=" + neckDiameter + ", hubLength=" + hubLength
				+ ", bore=" + bore + ", faceRaise=" + faceRaise + ", raisedFaceOutsideDiamater="
				+ raisedFaceOutsideDiamater + ", boltsCircleDiameter=" + boltsCircleDiameter + ", drillDiameter="
				+ drillDiameter + ", boltsDiameter=" + boltsDiameter + ", nBolts=" + nBolts + ", weight=" + weight
				+ "]";
	}





}
