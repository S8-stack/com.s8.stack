package com.s8.io.csv;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.s8.io.csv.type.CSV_TypeHandler;
import com.s8.io.csv.type.Setter;

/**
 * 
 * @author pc
 *
 * @param <T>
 */
public class CSV_Engine<T> {

	public final static String DELIMITERS = "[ ]*,[ ]*";

	public final static String TAG_REGEX = "([a-zA-Z0-0\\-_#\\?]+) *(\\[([a-zA-Z0-9\\.\\-]+)\\])? *";

	private Pattern tagPattern;

	private CSV_Unit.Base unitsFactory;


	private Constructor<T> constructor;

	private CSV_TypeHandler typeHandler;

	/**
	 * 
	 * @param type
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	public CSV_Engine(Class<T> type) 
			throws 
			NoSuchMethodException, 
			SecurityException, 
			NoSuchFieldException, 
			IllegalArgumentException, 
			IllegalAccessException 
	{
		super();
		initialize(type, null);
	}


	/**
	 * 
	 * @param type
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @throws NoSuchFieldException 
	 */
	public CSV_Engine(Class<T> type, CSV_Unit.Base factory) 
			throws 
			NoSuchMethodException, 
			SecurityException, 
			NoSuchFieldException, 
			IllegalArgumentException, 
			IllegalAccessException 
	{
		super();
		initialize(type, factory);
	}


	private void initialize(Class<T> type, CSV_Unit.Base unitsFactory) 
			throws 
			NoSuchMethodException, 
			SecurityException, 
			NoSuchFieldException, 
			IllegalArgumentException, 
			IllegalAccessException {

		constructor = type.getConstructor(new Class<?>[]{});

		typeHandler = new CSV_TypeHandler(type);

		// compile tag regex in a pattern
		tagPattern = Pattern.compile(TAG_REGEX);

		this.unitsFactory = unitsFactory;
	}
	

	/**
	 * 
	 * @param headerLine
	 * @return
	 * @throws IOException
	 */
	private Setter[] parseHeader(String headerLine) throws IOException {
		String[] headers = headerLine.split(DELIMITERS);
		int n = headers.length;

		Setter[] setters = new Setter[n];

		try {
			String tag, unit, header;
			for(int i=0; i<n; i++){
				Matcher matcher = tagPattern.matcher(header = headers[i]);
				matcher.find();
				tag = matcher.group(1);
				unit = matcher.group(3);
				Setter setter = typeHandler.getSetter(tag, 
						(unit!=null && unitsFactory!=null) ? unitsFactory.getUnit(unit) : null);
				if(setter == null) {
					throw new IOException("Failed to find a match for header: "+header+" for type "+typeHandler.getType());
				}
				setters[i] = setter;

			}
			return setters;
		}
		catch (NoSuchFieldException 
				| SecurityException 
				| IllegalArgumentException 
				| IllegalAccessException e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
	}






	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public T[] toArray(Path path) throws IOException {
		List<T> list = new ArrayList<T>();
		forEachRow(path, object -> list.add(object));
		int length = list.size();

		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(typeHandler.getType(), length);
		list.toArray(array);
		return array;
	}



	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public void forEachRow(Path path, CSV_Consumer<T> consumer) throws IOException {

		/* retrieve rows */
		List<String> lines = Files.readAllLines(path);
		int nRows = lines.size();
		
		
		/* retrieve columns */
		Setter[] setters = parseHeader(lines.get(0));
		int nColumns = setters.length;
		
		
		for(int iRow = 1; iRow < nRows; iRow++) {
			String line = lines.get(iRow);
			
			T object = null;
			try {
				String[] values = line.split(DELIMITERS);
				object = constructor.newInstance(new Object[]{});
				for(int iColumn = 0; iColumn < nColumns; iColumn++){
					setters[iColumn].set(values[iColumn], object);
				}
			}
			catch (InstantiationException 
					| IllegalAccessException 
					| IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
				throw new IOException("Failed to parse file due to: "+e.getMessage());
			}

			consumer.consumeRow(object);
		}	
	}
	
	
	
	
	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public List<T> toList(Path path) throws IOException {
		List<T> output = new ArrayList<T>();
		forEachRow(path, object -> output.add(object));
		return output;
	}
}

