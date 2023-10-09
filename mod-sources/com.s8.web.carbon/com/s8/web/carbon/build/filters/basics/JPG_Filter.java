package com.s8.web.carbon.build.filters.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.JPG_WebAsset;
import com.s8.web.carbon.build.CarbonBuildContext;


@XML_Type(name="jpg-filter")	
public class JPG_Filter extends BasicWebAssetFilter {


	@Override
	public WebAsset createAsset(CarbonBuildContext ctx, String webPathname, Path localPath) {
		return new JPG_WebAsset(ctx.getContainer(), webPathname, localPath, 
				cacheControl, fragmentLength);
	}
	
	@Override
	public String[] expose(String webPathname) {
		return new String[]{ webPathname };
	}
	
}