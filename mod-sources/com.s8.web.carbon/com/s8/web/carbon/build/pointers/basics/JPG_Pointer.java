package com.s8.web.carbon.build.pointers.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.JPG_WebAsset;
import com.s8.web.carbon.web.AssetContainerModule;


@XML_Type(name="jpg")
public class JPG_Pointer extends BasicWebAssetPointer {

	public JPG_Pointer() {
		super();
		fragmentLength = 2048;
	}
	
	
	@Override
	public WebAsset createAsset(AssetContainerModule container, 
			String webPathname,
			Path path, 
			CachePolicy policy,
			int fragmentLength) {
		return new JPG_WebAsset(container, webPathname, path, cacheControl, fragmentLength);
	}
}


