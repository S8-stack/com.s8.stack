package com.s8.web.carbon.assets.basics;

import java.nio.file.Path;

import com.s8.stack.arch.helium.mime.MIME_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.web.AssetContainerModule;


/**
 * 
 * @author pierreconvert
 *
 */
public class HTML_WebAsset extends BasicWebAsset {
	
	
	@Override
	public MIME_Type mime_getType() {
		return MIME_Type.HTML;
	}

	@Override
	public String getTypeName() {
		return "HTML";
	}

	/**
	 * 
	 */
	public HTML_WebAsset(AssetContainerModule module, 
			String webPathname, 
			Path path, 
			CachePolicy policy, 
			int fragmentLength) {
		super(module, webPathname, path, policy, fragmentLength);
	}
	
	
}
