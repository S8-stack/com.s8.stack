package com.s8.web.carbon.build.pointers.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.HTML_WebAsset;
import com.s8.web.carbon.web.AssetContainerModule;


/**
 * 
 * @author pierreconvert
 *
 */

@XML_Type(name="html")	
public class HTML_Pointer extends BasicWebAssetPointer {

	public HTML_Pointer() {
		super();
		fragmentLength = 2048;
	}
	

	@Override
	public WebAsset createAsset(AssetContainerModule container, String webPathname, Path path, CachePolicy policy,
			int fragmentLength) {
		return new HTML_WebAsset(container, webPathname, path, cacheControl, fragmentLength);
	}
}