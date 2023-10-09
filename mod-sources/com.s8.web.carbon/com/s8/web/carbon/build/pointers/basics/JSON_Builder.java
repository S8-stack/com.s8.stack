package com.s8.web.carbon.build.pointers.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.JS_WebAsset;
import com.s8.web.carbon.web.AssetContainerModule;

@XML_Type(name="json")	
public class JSON_Builder extends BasicWebAssetPointer {
	
	public JSON_Builder() {
		super();
		fragmentLength = 2048;
	}


	@Override
	public WebAsset createAsset(AssetContainerModule container, String webPathname, Path path,
			CachePolicy policy, int fragmentLength) {
		return new JS_WebAsset(container, webPathname, path, cacheControl, fragmentLength);
	}
	
}


