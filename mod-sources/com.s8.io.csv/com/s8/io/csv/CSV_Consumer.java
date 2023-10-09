package com.s8.io.csv;

public interface CSV_Consumer<T> {

	
	public void consumeRow(T object);
	
}
