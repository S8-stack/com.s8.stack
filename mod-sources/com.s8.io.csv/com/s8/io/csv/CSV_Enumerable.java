package com.s8.io.csv;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author pierreconvert
 *
 */
public abstract class CSV_Enumerable {

	public static class Prototype<E extends CSV_Enumerable> {
		
		private String doc;		

		private E[] mapByCode;

		private Map<String, E> mapByName;
		
		public Prototype(Class<E> type, String commonFilename) {
			
			// load documentation
			String docFilename = commonFilename+".html";
			if(docFilename!=null) {
				loadDoc(type, docFilename, StandardCharsets.UTF_8);	
			}
			
			// load table
			String tableFilename = commonFilename+".csv";
			loadTable(type, tableFilename);
			
		}

		public E getByCode(int code) {
			return mapByCode[code];
		}

		public E getByName(String name) {
			return mapByName.get(name);
		}

		public String getDoc() {
			return doc;
		}

		private void loadDoc(Class<?> type, String filename, Charset encoding) {
			try {
				
				String pathname = type.getResource(filename).getPath();
				Path path = Paths.get(pathname);
				if(Files.exists(path)) {
					byte[] encoded = Files.readAllBytes(path);
					String page = new String(encoded, encoding);
					int i0 = page.indexOf("<body>");
					page = page.substring(i0+6);
					int i1 = page.indexOf("</body>");
					doc = page.substring(0, i1);
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		@SuppressWarnings("unchecked")
		private void loadTable(Class<E> type, String tableFilename) {
			
			try {

				/* path */
				Path path = Path.of(this.getClass().getClassLoader().getResource(tableFilename).toURI());
				
				CSV_Engine<E> engine = new CSV_Engine<E>(type);
				List<E> enumerables = engine.toList(path);
				Integer maxCode = 0;
				for(E item : enumerables) { maxCode = Math.max(maxCode, item.getCode()); }
				
				
				// build maps
				int nEnumerables = enumerables.size();
				int length = maxCode+8;
				mapByCode = (E[]) Array.newInstance(type, length);
				for(E item : enumerables) {
					mapByCode[item.getCode()] = item;	
				}
				
				// build name-indexed map
				mapByName = new HashMap<String, E>(nEnumerables);
				for(E item : enumerables) {
					for(String name : item.getNames()) {
						mapByName.put(name, item);	
					}	
				}
				
			} 
			catch (NoSuchMethodException 
					| SecurityException 
					| IOException 
					| NoSuchFieldException 
					| IllegalArgumentException 
					| IllegalAccessException 
					| URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	public abstract int getCode();
	
	/**
	 * 
	 * @return preferred name
	 */
	public abstract String getName();
	
	public abstract String[] getNames();

	public abstract Prototype<? extends CSV_Enumerable> getPrototype();

}
