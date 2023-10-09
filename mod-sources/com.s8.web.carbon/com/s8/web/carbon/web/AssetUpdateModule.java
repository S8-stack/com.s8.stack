package com.s8.web.carbon.web;


import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
/* 
 * Code from Oracle, quoted on StackOverflow
 * 
 */
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.s8.arch.silicon.SiliconEngine;
import com.s8.web.carbon.assets.WebAsset;


/**
 */
public class AssetUpdateModule {
	
	private final SiliconEngine server;

	Map<Path, WebAsset> watched;
	
	final WatchService watchService;

	final Map<WatchKey,Path> keys;

	private final boolean isVerbose;

	



	/**
	 * Creates a WatchService and registers the given directory
	 */
	public AssetUpdateModule(SiliconEngine server, boolean isVerbose) throws IOException {
		super();
		this.server = server;
		this.watched = new ConcurrentHashMap<>();
		
		this.watchService = FileSystems.getDefault().newWatchService();
		this.keys = new ConcurrentHashMap<WatchKey,Path>();


		// enable trace after initial registration
		this.isVerbose = isVerbose;
	}
	


	/**
	 * Process all events for keys queued to the watcher
	 */
	public void start() {
		server.pushWatchTask(new WatchAndUpdateTask(this, isVerbose));
	}

	
	
	/**
	 * 
	 * @param path
	 * @param asset
	 */
	public void appendWatched(Path path, WebAsset asset) {
		watched.put(path, asset);
	}
	



	public void register(Path path) throws IOException {
		WatchKey key = path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		if (isVerbose) {
			Path prev = keys.get(key);
			if (prev == null) {
				System.out.format("register: %s\n", path);
			} else {
				if (!path.equals(prev)) {
					System.out.format("update: %s -> %s\n", prev, path);
				}
			}
		}
		keys.put(key, path);
	}



	public void stop() {
	}






}
