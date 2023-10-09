package com.s8.web.carbon.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import com.s8.io.bytes.linked.LinkedBytes;


/**
 * 
 * @author pc
 *
 */
public abstract class FileDiskTask extends CarbonDiskTask {


	
	private Path path;

	private int fragmentLength;

	public final static int MAX_NB_LOAD_ATTEMPTS = 8;

	//private int nAttemptsSoFar = 0;



	/**
	 * payload
	 */


	/**
	 * 
	 * @param source
	 * @throws IOException 
	 */
	public FileDiskTask(AssetContainerModule module, Path path, int fragmentLength) {
		super(module);
		this.path = path;
		this.fragmentLength = fragmentLength;
		if(fragmentLength==0) {
			throw new RuntimeException("Zero fragment length");
		}
	}
	
	
	/**
	 * Called on disk asset load successfully
	 * @return true if need to requeue this taks, false if terminate correctly
	 */
	public abstract void onLoadedSuccessfully(LinkedBytes bytes);

	/**
	 * Called on disk asset load successfully
	 * @return true if need to requeue this taks, false if terminate correctly
	 */
	public abstract void onNotFound();
	
	/**
	 * Called on disk asset load successfully
	 * @return true if need to requeue this taks, false if terminate correctly
	 */
	public abstract void onFailedLoad();



	/**
	 * 
	 */
	@Override
	public void run() {

		File file = path.toFile();
		if(file.exists()) {
			// trigger loading
			try(InputStream inputStream = new FileInputStream(file)){

				LinkedBytes block = new LinkedBytes(new byte[fragmentLength], 0, 0), nextBlock;

				LinkedBytes head = block;
				boolean isDone = false;
				int nBytes;
				int capacity = block.bytes.length, position = block.offset;
				while(!isDone){

					nBytes = inputStream.read(block.bytes, position, capacity-position);
					// end of stream reached
					if(nBytes==-1){ 
						isDone = true;
					}
					else {
						position+=nBytes;
						block.length+=nBytes;

						if(position==capacity){ // full loading of block

							// create next block
							nextBlock = new LinkedBytes(new byte[fragmentLength], 0, 0);

							// link next block
							block.next = nextBlock;

							// reset scope vars
							block = nextBlock;
							capacity = block.bytes.length;
							position = block.offset;
						}
						// else{ : incomplete reading of block
					}
				}

				inputStream.close();
				onLoadedSuccessfully(head);
			}
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to load file: "+path);
				
				//nAttemptsSoFar++;
				//if(nAttemptsSoFar<MAX_NB_LOAD_ATTEMPTS) {
				onFailedLoad();
				
			}
		}
		else {
			System.out.println("File not found: "+path);
			onNotFound();
		}
	}



	
	/*
	@Override
	public void fail() {
		onFailedLoad();
	}
	*/


}