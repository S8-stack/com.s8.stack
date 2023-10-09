package com.s8.io.csv.tests;


import java.nio.file.Path;
import java.nio.file.Paths;

import com.s8.io.csv.CSV_Engine;

public class TestUnit03 {

	public static void main(String[] args) throws Exception {
		CSV_Engine<ASME_B16_5_WeldingNeckFlangeModel> engine 
			= new CSV_Engine<>(ASME_B16_5_WeldingNeckFlangeModel.class, QxUnit2.FACTORY);
		Path path = Paths.get("data/ASME_B16.5_welded_neck_flanges.csv");
		engine.forEachRow(path, item -> {
			System.out.println(item);
		});
		
	}

}
