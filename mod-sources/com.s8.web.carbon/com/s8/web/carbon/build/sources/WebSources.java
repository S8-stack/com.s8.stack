package com.s8.web.carbon.build.sources;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.s8.io.xml.annotations.XML_SetElement;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.build.CarbonBuildContext;
import com.s8.web.carbon.build.filters.WebAssetFilter;
import com.s8.web.carbon.build.pointers.WebAssetPointer;
import com.s8.web.carbon.syntax.CarbonSyntax;

@XML_Type(name = "sources", root = true)
public class WebSources {


	/*
	private String sourcesRelativeLocalPathname;

	@XML_SetElement(tag = "root-local-path")
	public void setLocalPathname(String pathname) {
		this.sourcesRelativeLocalPathname = pathname;
	}
	*/

	private String sourcesRelativeWebPathname = CarbonSyntax.ROOT_WEB_PATHNAME;

	@XML_SetElement(tag = "root-web-path")
	public void setWebPathname(String pathname) {
		this.sourcesRelativeWebPathname = pathname;
	}


	/**
	 * assets
	 */
	private List<WebAssetFilter> filters;

	private List<WebAssetPointer> builders;


	/**
	 * 
	 * @param assetBuilder
	 */
	@XML_SetElement(tag = "filter")
	public void append(WebAssetFilter filter) {
		filters.add(filter);
	}


	/**
	 * 
	 * @param assetBuilder
	 */
	@XML_SetElement(tag = "builder")
	public void append(WebAssetPointer builder) {
		builders.add(builder);
	}

	/**
	 * 
	 */
	public WebSources() {
		super();
		filters = new ArrayList<WebAssetFilter>();
		builders = new ArrayList<WebAssetPointer>();
	}


	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public void build(CarbonBuildContext ctx, 
			String webPathname, Path localPath) throws Exception {


		String sourcesWebPathname = webPathname.concat(this.sourcesRelativeWebPathname);
		Path sourcesLocalPath = localPath;

		/* <builders> */
		
		for(WebAssetPointer builder : builders) {
			builder.build(ctx, sourcesWebPathname, sourcesLocalPath);
		}
		/* </builders> */

		// register directory and sub-directories

		
		Files.walkFileTree(sourcesLocalPath, new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs)
					throws IOException {
				ctx.getUpdater().register(path);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
				
				if(Files.isRegularFile(path)){
					Path relativePath = sourcesLocalPath.relativize(path);
					String fileWebPathname = sourcesWebPathname + relativePath.toString();
					for(WebAssetFilter filter : filters) {
						if(filter.isCapturing(relativePath)) {
							filter.capture(ctx, fileWebPathname, path);
						}
					}
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path path, IOException exception) throws IOException {
				if(ctx.isVerbose()) {
					System.out.println("Failed to load file: "+path);
				}
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exception) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});

		/* </filters> */

	}
}
