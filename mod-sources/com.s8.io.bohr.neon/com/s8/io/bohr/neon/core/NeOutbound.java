package com.s8.io.bohr.neon.core;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import com.s8.api.bohr.BOHR_Keywords;
import com.s8.api.bytes.ByteOutflow;
import com.s8.api.objects.web.WebS8Vertex;

/**
 * 
 * @author pierreconvert
 *
 */
public class NeOutbound {


	/**
	 * 
	 */
	private Queue<WebS8Vertex> unpublishedChanges;
	
	
	/**
	 * 
	 */
	public NeOutbound() {
		super();
		unpublishedChanges = new LinkedList<WebS8Vertex>();
	}
	
	
	/**
	 * 
	 * @param outflow
	 * @throws IOException 
	 */
	public void publish(ByteOutflow outflow) throws IOException {
		
		outflow.putUInt8(BOHR_Keywords.OPEN_JUMP);
		
		while(!unpublishedChanges.isEmpty()) {
			unpublishedChanges.poll().publish(outflow);
		}
		
		outflow.putUInt8(BOHR_Keywords.CLOSE_JUMP);
	}



	/**
	 * 
	 * @param object
	 */
	public void notifyChanged(WebS8Vertex object) {
		unpublishedChanges.add(object);
	}
	
}
