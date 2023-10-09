package com.s8.web.carbon.build.filters.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.JS_WebAsset;
import com.s8.web.carbon.build.CarbonBuildContext;


@XML_Type(name="js-filter")	
public class JS_Filter extends BasicWebAssetFilter {


	@Override
	public WebAsset createAsset(CarbonBuildContext ctx, String webPathname, Path localPath) {
		return new JS_WebAsset(ctx.getContainer(), webPathname, localPath,
				cacheControl, fragmentLength);
	}
	
	
	@Override
	public String[] expose(String webPathname) {
		// String abbreviated = webPathname.substring(0, webPathname.length()-3);
		//return new String[]{ webPathname, abbreviated};
		
		return new String[]{ webPathname };
	}
	
}