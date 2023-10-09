package com.s8.web.carbon.web;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;

import com.s8.arch.silicon.watch.WatchSiTask;
import com.s8.web.carbon.assets.WebAsset;

/**
 * 
 * @author pierreconvert
 *
 */
public class WatchAndUpdateTask implements WatchSiTask {


	private final AssetUpdateModule module;

	private boolean isVerbose;

	/**
	 * 
	 * @param server
	 * @param module
	 * @param isVerbose
	 */
	public WatchAndUpdateTask(AssetUpdateModule module, boolean isVerbose) {
		super();
		this.module = module;
		this.isVerbose = isVerbose;
	}


	@Override
	public WatchSiTask run() {

		WebAsset asset;


		// wait for key to be signalled
		WatchKey key;
		try {
			key = module.watchService.take();
		} catch (InterruptedException x) {
			return null; // re-inject null task -> cause this task to abort its infinte loop
		}

		Path dir = module.keys.get(key);
		if (dir!= null) {


			for (WatchEvent<?> signalledEvent: key.pollEvents()) {
				WatchEvent.Kind<?> kind = signalledEvent.kind();

				// TBD - provide example of how OVERFLOW event is handled
				if (kind == OVERFLOW) {
					continue;
				}

				// Context for directory entry event is the file name of entry
				@SuppressWarnings("unchecked")
				WatchEvent<Path> event = (WatchEvent<Path>) signalledEvent;

				Path relativePathname = event.context();
				Path path = dir.resolve(relativePathname);

				// print out event
				if(isVerbose) {
					System.out.format("%s: %s\n", signalledEvent.kind().name(), path);						
				}


				if(kind == ENTRY_MODIFY) {
					if((asset = module.watched.get(path))!=null) {
						asset.reset();
					}	
				}

				// if directory is created, and watching recursively, then
				// register it and its sub-directories
				else if (kind == ENTRY_CREATE) {
					try {
						if(Files.isDirectory(path, NOFOLLOW_LINKS)) {
							module.register(path);
						}
					} catch (IOException x) {
						// ignore to keep sample readable
					}
				}
			}

			// reset key and remove from set if directory no longer accessible
			boolean valid = key.reset();
			if (!valid) {
				module.keys.remove(key);

				// all directories are inaccessible
				if (module.keys.isEmpty()) {
					//break;
				}
			}
			
		}
		else {
			System.err.println("WatchKey not recognized!!");
		}
		
		// re-inject
		return this;
	}
	
	@Override
	public String describe() {
		return "(Carbon) WATCH_AND_UPDATE task";
	}

}
