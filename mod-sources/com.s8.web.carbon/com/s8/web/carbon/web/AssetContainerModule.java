package com.s8.web.carbon.web;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.s8.arch.silicon.SiliconEngine;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.syntax.CarbonSyntax;


/**
 * 
 * @author pierreconvert
 *
 */
public class AssetContainerModule {
	
	private final SiliconEngine server;
	
	public final static int MAX_CONCURRENT_TASKS = 65536;


	//private Map<String, WebSourceModule> webSourcesModules;

	ConcurrentMap<String, WebAsset> assets;


	private boolean isVerbose;
	
	public AssetContainerModule(SiliconEngine server, boolean isVerbose) {
		super();
		
		this.server = server;

		// build map
		this.assets = new ConcurrentHashMap<>();

		// is verbose
		if(isVerbose) {
			System.out.println("[CarbonWebService]: adding assets...");
		}
	
		this.isVerbose = isVerbose;
	}
	
	


	public void appendAsset(String webPathname, WebAsset asset) {
		assets.put(webPathname, asset);
	}


	public WebAsset get(String webPathname) {
		return assets.get(webPathname);
	}

	

	public void start() throws Exception {
		// specifiying base
		WebAsset indexWebAsset = this.assets.get(CarbonSyntax.INDEX_WEB_PATHNAME);
		if(indexWebAsset==null) {
			throw new Exception("Missing /index.html");
		}
		this.assets.put(CarbonSyntax.ROOT_WEB_PATHNAME, indexWebAsset);

	}

	/**
	 * 
	 * @return
	 */
	public boolean isVerbose() {
		return isVerbose;
	}
	
	


	public static abstract class WebAssetOperator {

		private boolean hasBeenFound;

		public WebAssetOperator() {
			super();
			this.hasBeenFound = false;
		}

		public abstract void on(WebAsset asset);

		public boolean hasBeenFound() {
			return hasBeenFound;
		}
	}



	public WebAsset getAsset(String webPathname) {
		return assets.get(webPathname);
	}

	

	
	public void pushTask(FileDiskTask task) {
		server.pushAsyncTask(task);
	}

	public void stop() {
	}



	public boolean hasEntry(String webPathname) {
		return assets.containsKey(webPathname);
	}




	public SiliconEngine getApp() {
		return server;
	}

}
