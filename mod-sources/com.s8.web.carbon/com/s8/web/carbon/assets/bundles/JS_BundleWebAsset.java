package com.s8.web.carbon.assets.bundles;

import java.util.List;

import com.s8.io.bytes.linked.LinkedBytes;
import com.s8.stack.arch.helium.mime.MIME_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.JS_WebAsset;
import com.s8.web.carbon.web.AssetContainerModule;

public class JS_BundleWebAsset extends BundleWebAsset {


	private int fragmentLength;

	private List<JS_WebAsset> sources;

	private LinkedBytes bytes;

	public JS_BundleWebAsset(AssetContainerModule module, 
			String webPathname, 
			CachePolicy cacheControl, 
			int fragmentLength,
			List<JS_WebAsset> sources) {
		super(module, webPathname, cacheControl);
		this.fragmentLength = fragmentLength;
		this.sources = sources;
		
		// add this bundle as a watcher for all sources
		for(WebAsset source : sources) {
			source.addWatcher(this);
		}
	}

	@Override
	public MIME_Type mime_getType() {
		return MIME_Type.JS;
	}
	

	/**
	 * 
	 */
	public void concatenate() {
		int nFragments = sources.size();
		LinkedBytes head = null, tail = null, fragmentBytes;
		for(int i=0; i<nFragments; i++) {
			fragmentBytes = fragments[i];
			if(head==null) {
				head = fragmentBytes;
				tail = fragmentBytes.tail();
			}
			else {
				tail = tail.append(fragmentBytes);
			}
		}

		// recut
		bytes = head.recut(fragmentLength);
	}

	@Override
	public LinkedBytes getBytes() {
		if(bytes==null) {
			concatenate();
		}
		return bytes;
	}

	@Override
	public int getNbSources() {
		return sources.size();
	}

	@Override
	public JS_WebAsset getSource(int index) {
		return sources.get(index);
	}


}
