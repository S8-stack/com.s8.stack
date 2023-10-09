package com.s8.io.csv.tests;


import java.nio.file.Path;
import java.nio.file.Paths;

import com.s8.io.csv.CSV_Engine;

public class TestUnit02 {

	public static void main(String[] args) throws Exception {
		CSV_Engine<NominalPipeSize> engine = new CSV_Engine<>(NominalPipeSize.class, QxUnit2.FACTORY);
		Path path = Paths.get("data/nominal_pipe_sizes.csv");
		engine.forEachRow(path, item -> {
			System.out.println(item);
		});
		
	}

}
