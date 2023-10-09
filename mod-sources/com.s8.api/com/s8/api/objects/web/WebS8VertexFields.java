package com.s8.api.objects.web;

import java.util.List;

public interface WebS8VertexFields {


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setBool8Field(String name, boolean value);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean getBool8Field(String name);
	
	
	


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setBool8ArrayField(String name, boolean[] value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean[] getBool8ArrayField(String name);
	
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt8Field(String name, int value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public int getUInt8Field(String name);
	
	

	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt8ArrayField(String name, int[] value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public int[] getUInt8ArrayField(String name);
	
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt16Field(String name, int value);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public int getUInt16Field(String name);
	
	

	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt16ArrayField(String name, int[] value);

	/**
	 * 
	 * @param name
	 * @return
	 */
	public int[] getUInt16ArrayField(String name);
	
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt32Field(String name, long value);
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public long getUInt32Field(String name);
	

	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt32ArrayField(String name, long[] value);
	
	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public long[] getUInt32ArrayField(String name);
	
	


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setUInt64Field(String name, long value);
	


	/**
	 * 
	 * @param name
	 * @return
	 */
	public long getUInt64Field(String name);
	
	

	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt8Field(String name, int value);
	
	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public int getInt8Field(String name);
	


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt16Field(String name, int value);


	/*
	 * 
	 */
	public int getInt16Field(String name);
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt32Field(String name, int value);
	


	/**
	 * 
	 * @param name
	 * @return
	 */
	public int getInt32Field(String name);
	
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt64Field(String name, long value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public long getInt64Field(String name);
	
	

	


	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setInt64ArrayField(String name, long[] value);
	

	/**
	 * 
	 * @param name
	 * @return
	 */
	public long[] getInt64ArrayField(String name);
	


	
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setFloat32Field(String name, float value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public float getFloat32Field(String name);
	

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setFloat32ArrayField(String name, float[] value);



	/**
	 * 
	 * @param name
	 * @return
	 */
	public float[] getFloat32ArrayField(String name);

	


	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setFloat64Field(String name, double value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public double getFloat64Field(String name);
	

	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setFloat64ArrayField(String name, double[] value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public double[] getFloat64ArrayField(String name);

	

	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setStringUTF8Field(String name, String value);


	public String getStringUTF8Field(String name);


	
	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setStringUTF8ArrayField(String name, String[] value);


	/**
	 * 
	 * @param name
	 * @return
	 */
	public String[] getStringUTF8ArrayField(String name);
	

	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param value
	 */
	public <T extends WebS8Object> void setObjectField(String name, T value);


	/**
	 * 
	 * @param <T>
	 * @param name
	 * @return
	 */
	public <T extends WebS8Object> T getObjectField(String name);
	
	


	public <T extends WebS8Object> void setObjectListField(String name, List<T> value);
	
	public <T extends WebS8Object> void setObjectListField(String name, T[] value);


	
	/**
	 * 
	 * @param <T>
	 * @param name
	 * @return a <b>COPY</b> of the underlying list
	 */
	public <T extends WebS8Object> List<T> getObjectListField(String name);

	
	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param obj
	 */
	public <T extends WebS8Object> void addObjToList(String name, T obj);
	
	
	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param obj
	 */
	public <T extends WebS8Object> void removeObjFromList(String name, T obj);

	
	
}
