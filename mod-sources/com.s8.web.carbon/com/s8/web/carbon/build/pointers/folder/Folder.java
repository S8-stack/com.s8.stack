package com.s8.web.carbon.build.pointers.folder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_SetElement;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.WebAsset;
import com.s8.web.carbon.build.CarbonBuildContext;
import com.s8.web.carbon.build.pointers.WebAssetPointer;

@XML_Type(name = "folder")
public class Folder extends WebAssetPointer {
	
	private String relativePathname;
	
	@XML_SetAttribute(name = "path")
	public void setPathname(String pathname) {
		this.relativePathname = pathname;
	}
	
	
	private List<WebAssetPointer> builders;
	
	@XML_SetElement(tag = "builder")
	public void addBuilder(WebAssetPointer builder) {
		builders.add(builder);
	}
	
	
	public Folder() {
		super();
		builders = new ArrayList<>();
	}

	@Override
	public WebAsset build(CarbonBuildContext context, String webPathname, Path localPath) throws IOException {
		
		String folderWebPathname = webPathname.concat(relativePathname);
		Path folderLocalPath = localPath.resolve(relativePathname);
		
		for(WebAssetPointer builder : builders) {
			builder.build(context, folderWebPathname, folderLocalPath);
		}
		
		return null;
	}

}
