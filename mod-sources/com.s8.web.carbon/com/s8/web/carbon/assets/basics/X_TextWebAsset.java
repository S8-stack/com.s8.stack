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
public class X_TextWebAsset extends BasicWebAsset {
	
	public @Override MIME_Type mime_getType() { return MIME_Type.TEXT; }
	public @Override String getTypeName() { return "x (undefined)"; }

	/**
	 * 
	 */
	public X_TextWebAsset(AssetContainerModule module, 
			String webPathname, Path localPath, CachePolicy policy, int fragmentLength) {
		super(module, webPathname, localPath, policy, fragmentLength);
	}
	
}
