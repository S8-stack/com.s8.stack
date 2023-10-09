package com.s8.web.carbon.build.filters.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.assets.basics.X_TextWebAsset;
import com.s8.web.carbon.build.CarbonBuildContext;


/** svg-filter */
@XML_Type(name="x-filter")
public class X_TextFilter extends BasicWebAssetFilter {

	@Override
	public WebAsset createAsset(CarbonBuildContext ctx, String webPathname, Path localPath) {
		return new X_TextWebAsset(ctx.getContainer(), 
				webPathname, localPath,
				cacheControl, fragmentLength);
	}
	
	@Override
	public String[] expose(String webPathname) {
		return new String[]{ webPathname };
	}
}