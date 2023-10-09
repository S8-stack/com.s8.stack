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
public class JPG_WebAsset extends BasicWebAsset {
	
	@Override
	public MIME_Type mime_getType() {
		return MIME_Type.JPEG;
	}
	
	@Override
	public String getTypeName() {
		return "JPG";
	}

	/**
	 * 
	 */
	public JPG_WebAsset(AssetContainerModule module, 
			String webPathname, 
			Path localPath, 
			CachePolicy policy, 
			int fragmentLength) {
		super(module, webPathname, localPath, policy, fragmentLength);
	}
	
}
