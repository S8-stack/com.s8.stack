package com.s8.web.carbon.build.sources;

import java.nio.file.Path;

import com.s8.io.xml.annotations.XML_SetValue;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.io.xml.codebase.XML_Codebase;
import com.s8.web.carbon.build.CarbonBuildContext;

@XML_Type(name = "module")
public class WebModule {
	
	
	public final static String MODULE_CONFIG_PATHNAME = "module.xml";
	

	public String relativeLocalPathname;
	
	@XML_SetValue
	public void setRelativeLocalPathname(String pathname) {
		relativeLocalPathname = pathname;
	}
	
	
	/**
	 * 
	 * @param context
	 * @throws Exception 
	 */
	public void build(XML_Codebase context, CarbonBuildContext ctx, String webPathname, Path localPath) throws Exception {
		
		Path webSourcesFolderPath = localPath.resolve(relativeLocalPathname);
		Path configPath = webSourcesFolderPath.resolve(MODULE_CONFIG_PATHNAME);
		
		WebSources sources = (WebSources) context.deserialize(configPath.toFile());
		sources.build(ctx, webPathname, webSourcesFolderPath);
	}
}
