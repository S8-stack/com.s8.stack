package com.s8.io.csv.utilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import com.s8.io.csv.CSV_Consumer;
import com.s8.io.csv.CSV_Engine;
import com.s8.io.csv.CSV_Unit;

public class CSV_FileLoader {

	
	public final static String CLASS_FILE_EXTENSION = ".class";

	
	
	/**
	 * 
	 * @param <T> the type of item for parsing
	 * @param baseClass the base class for staring pathname
	 * @param itemClass
	 * @param pathname
	 * @param consumer
	 */
	public static <T> void load(
			Class<?> baseClass, 
			Class<T> itemClass, 
			String pathname, 
			CSV_Unit.Base units,
			CSV_Consumer<T> consumer) {
		try {
			
			Path baseClassPath = Path.of(baseClass.getResource(baseClass.getSimpleName()+".class").toURI());
			Path path = baseClassPath.getParent().resolve(pathname);

			CSV_Engine<T> engine = new CSV_Engine<>(itemClass, units);
			engine.forEachRow(path, consumer);
		} 
		catch (URISyntaxException 
				| NoSuchMethodException 
				| SecurityException 
				| NoSuchFieldException 
				| IllegalArgumentException 
				| IllegalAccessException 
				| IOException e) {
			e.printStackTrace();
		}				
	}
	
	
	
	/**
	 * 
	 * @param <T> the type of item for parsing
	 * @param baseClass the base class for staring pathname
	 * @param itemClass
	 * @param pathname
	 * @param consumer
	 */
	public static <T> void load(
			Class<?> baseClass, 
			Class<T> itemClass, 
			String pathname, 
			CSV_Consumer<T> consumer) {
		try {
			
			Path baseClassPath = Path.of(baseClass.getResource(baseClass.getSimpleName()+".class").toURI());
			Path path = baseClassPath.getParent().resolve(pathname);

			CSV_Engine<T> engine = new CSV_Engine<>(itemClass, CSV_Unit.STUB_FACTORY);
			engine.forEachRow(path, consumer);
		} 
		catch (URISyntaxException 
				| NoSuchMethodException 
				| SecurityException 
				| NoSuchFieldException 
				| IllegalArgumentException 
				| IllegalAccessException 
				| IOException e) {
			e.printStackTrace();
		}				
	}
}
