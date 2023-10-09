package com.s8.web.carbon.build.filters;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

import com.s8.io.xml.annotations.XML_SetAttribute;
import com.s8.io.xml.annotations.XML_SetElement;
import com.s8.io.xml.annotations.XML_Type;
import com.s8.web.carbon.assets.CachePolicy;
import com.s8.web.carbon.build.CarbonBuildContext;
import com.s8.web.carbon.build.filters.basics.BasicWebAssetFilter;


@XML_Type(name = "filter", sub= {
		BasicWebAssetFilter.class,
})
public abstract class WebAssetFilter {

	/**
	 * <h1>code: (0x5)</h1>
	 * <p>
	 * Indicates the size of the largest frame payload that the sender 
	 * is willing to receive, in octets. The initial value is 2^14 (16,384) 
	 * octets.
	 * </p>
	 */
	public final static int MAX_FRAME_SIZE = 16384;


	public CachePolicy cacheControl = CachePolicy.STABLE;

	@XML_SetAttribute(name = "cache")
	public void setCacheControl(CachePolicy cacheControl) {
		this.cacheControl = cacheControl;
	}

	public PathMatcher pathMatcher;

	@XML_SetElement(tag = "pattern")
	public void setPattern(String pattern) {
		pathMatcher = FileSystems.getDefault().getPathMatcher("glob:"+pattern);
	}

	public boolean isCapturing(Path path) {
		return pathMatcher.matches(path);
	}

	/**
	 * 
	 * @param context
	 * @param contextWebPathname
	 * @param relativeLocalPathname
	 * @throws IOException
	 */
	public abstract void capture(CarbonBuildContext context, 
			String contextWebPathname, 
			Path contextLocalPath);
}
