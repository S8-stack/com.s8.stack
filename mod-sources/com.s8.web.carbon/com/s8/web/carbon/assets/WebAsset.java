package com.s8.web.carbon.assets;

import java.util.ArrayList;
import java.util.List;

import com.s8.arch.magnesium.handlers.h1.H1Handler;
import com.s8.arch.silicon.SiliconEngine;
import com.s8.stack.arch.helium.mime.MIME_Type;
import com.s8.web.carbon.web.AssetContainerModule;


/**
 * @author pc
 *
 */
public abstract class WebAsset extends H1Handler<Payload> {
	
	
	
	
	protected CachePolicy cacheControl = CachePolicy.DEBUG;
	
	
	/**
	 * Canonical web pathname
	 */
	private final String webPathname;
	
	
	protected final AssetContainerModule module;

	
	protected List<WebAssetWatcher> watchers;
	
	
	/**
	 * 
	 * @param webPathname
	 * @param cacheControl
	 */
	public WebAsset(AssetContainerModule module, String webPathname, CachePolicy cacheControl) {
		super();
		this.module = module;
		this.webPathname = webPathname;
		this.cacheControl = cacheControl;
		
		// watchers
		
	}

	public void addWatcher(WebAssetWatcher watcher) {
		if(watchers==null) {
			watchers = new ArrayList<>();
		}
		watchers.add(watcher);
	}
	
	public abstract MIME_Type mime_getType();
	

	/**
	 * <b>Canonical</b> Web pathname (pathname for web loading)
	 * @return
	 */
	public String getWebPathname() {
		return webPathname;
	}

	
	/**
	 * 
	 * @return cache control policy
	 */
	public CachePolicy getCacheControl() {
		return cacheControl;
	}

	
	
	
	
	/**
	 * 
	 */
	/*
	protected void unpark() {
		if(status==HTTP2_Status.OK) {
			while(!callbacks.isEmpty()) {
				callbacks.poll().onSuccessfullyLoaded(getBytes());
			}
		}
		else {
			while(!callbacks.isEmpty()) {
				callbacks.poll().onFailedLoad(status, message);
			}
		}
	}*/

	/**
	 * Reset asset loaded status (post disk event notification)
	 */
	public abstract void reset();
	


	//protected abstract void clearCache();

	
	@Override
	public SiliconEngine getAppEngine() {
		return module.getApp();
	}





}
