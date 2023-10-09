package com.s8.web.carbon.build.filters.basics;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.build.CarbonBuildContext;
import com.s8.web.carbon.build.filters.WebAssetFilter;

@XML_Type(name = "basic-filter", sub = {
		HTML_Filter.class,
		CSS_Filter.class,
		JS_Filter.class,
		JSON_Filter.class,
		PNG_Filter.class,
		JPG_Filter.class,
		SVG_Filter.class,
		X_TextFilter.class
})
public abstract class BasicWebAssetFilter extends WebAssetFilter {
	
	public int fragmentLength;
	
	@XML_SetAttribute(name = "frag-length")
	public void setFragmentLength(int length) {
		this.fragmentLength = length;
	}

	//public abstract void build(ContainerModule module, UpdateModule updater, String webPathname, Path path);
	
	
	/**
	 * 
	 * @param context
	 * @param webPathname
	 * @param localPath
	 * @return
	 */
	public abstract WebAsset createAsset(CarbonBuildContext context, String webPathname, Path localPath);
	
	

	
	@Override
	public void capture(CarbonBuildContext context, String webPathname, Path localPath) {
		if(!context.isWebPathnameRegistered(webPathname)) {

			
			// build asset
			WebAsset asset = createAsset(context, webPathname, localPath);
			
			// advertise
			if(context.isVerbose()) {
				System.out.println("> new asset captured: "+asset);
			}
			
			// append the asset
			for(String variant : expose(webPathname)) {
				context.getContainer().appendAsset(variant, asset);		
			}
			
			context.getUpdater().appendWatched(localPath, asset);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	public abstract String[] expose(String webPathname);
	

}