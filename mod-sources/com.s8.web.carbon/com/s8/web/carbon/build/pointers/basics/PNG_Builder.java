package com.s8.web.carbon.build.pointers.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.PNG_WebAsset;
import com.s8.web.carbon.web.AssetContainerModule;


@XML_Type(name="png")	
public class PNG_Builder extends BasicWebAssetPointer {

	public PNG_Builder() {
		super();
		fragmentLength = 2048;
	}
	

	@Override
	public WebAsset createAsset(AssetContainerModule container, String webPathname, Path path, CachePolicy policy,
			int fragmentLength) {
		return new PNG_WebAsset(container, webPathname, path, cacheControl, fragmentLength);
	}	
}


